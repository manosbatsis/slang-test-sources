# Guest boxes to use for vagrant-spec
GUEST_BOXES = {
  'hashicorp-vagrant/ubuntu-16.04' => '1.0.1',
  'hashicorp-vagrant/centos-7.4' => '1.0.2',
  'hashicorp-vagrant/windows-10' => '1.0.0',
  'spox/osx-10.12' => '0.0.1'
}

# Host boxes to run vagrant-spec
HOST_BOXES = {
  'hashicorp-vagrant/ubuntu-16.04' => '1.0.1',
  'hashicorp-vagrant/centos-7.4' => '1.0.2',
  'hashicorp-vagrant/windows-10' => '1.0.0',
  'spox/osx-10.12' => '0.0.1'
}

# Determine what providers to test
enabled_providers = ENV.fetch("VAGRANT_SPEC_PROVIDERS", "virtualbox").split(",")
# Set what boxes should be used
enabled_guests = ENV["VAGRANT_GUEST_BOXES"] ? ENV["VAGRANT_GUEST_BOXES"].split(",") : GUEST_BOXES.keys
enabled_hosts = ENV["VAGRANT_HOST_BOXES"] ? ENV["VAGRANT_HOST_BOXES"].split(",") : HOST_BOXES.keys

guest_boxes = Hash[GUEST_BOXES.find_all{|name, version| enabled_guests.include?(name)}.compact]
host_boxes = Hash[HOST_BOXES.find_all{|name, version| enabled_hosts.include?(name)}.compact]

# Grab vagrantcloud token, if available
vagrantcloud_token = ENV["VAGRANT_CLOUD_TOKEN"]

# Download copies of the guest boxes for testing if missing
enabled_providers.each do |provider_name|
  guest_boxes.each do |guest_box, box_version|
    box_owner, box_name = guest_box.split('/')
    box_path = File.join(File.dirname(__FILE__), "./boxes/#{guest_box.sub('/', '_')}.#{provider_name}.#{box_version}.box")
    if !File.exist?(box_path)
      $stderr.puts "Downloading guest box #{guest_box}"
      cmd = "curl -Lf -o #{box_path} https://app.vagrantup.com/#{box_owner}/boxes/#{box_name}/versions/#{box_version}/providers/#{provider_name}.box"
      if vagrantcloud_token
        cmd += "?access_token=#{vagrantcloud_token}"
      end
      result = system(cmd)
      if !result
        $stderr.puts
        $stderr.puts "ERROR: Failed to download guest box #{guest_box} for #{provider_name}!"
        exit 1
      end
    end
  end
end

Vagrant.configure(2) do |global_config|
  host_boxes.each do |box_name, box_version|
    platform = box_name.split('/').last.sub(/[^a-z]+$/, '')

    enabled_providers.each do |provider_name|
      global_config.vm.define("#{box_name.split('/').last}-#{provider_name}") do |config|
        config.vm.box = box_name
        config.vm.box_version = box_version
        config.vm.synced_folder '.', '/vagrant', disable: true
        config.vm.synced_folder '../../', '/vagrant'
        config.vm.provider :vmware_desktop do |vmware|
          vmware.vmx["memsize"] = ENV.fetch("VAGRANT_HOST_MEMORY", "2048")
          vmware.vmx['vhv.enable'] = 'TRUE'
          vmware.vmx['vhv.allow'] = 'TRUE'
        end
        if platform == "windows"
          config.vm.provision :shell, path: "./scripts/#{platform}-setup.#{provider_name}.ps1", run: "once"
        else
          config.vm.provision :shell, path: "./scripts/#{platform}-setup.#{provider_name}.sh", run: "once"
        end
        guest_boxes.each_with_index do |box_info, idx|
          guest_box, box_version = box_info
          spec_cmd_args = ENV["VAGRANT_SPEC_ARGS"]
          if idx != 0
            spec_cmd_args = "#{spec_cmd_args} --without-component cli/*".strip
          end
          if platform == "windows"
            config.vm.provision(
              :shell,
              path: "./scripts/#{platform}-run.#{provider_name}.ps1",
              keep_color: true,
              env: {
                "VAGRANT_SPEC_ARGS" => "--no-builtin #{spec_cmd_args}".strip,
                "VAGRANT_SPEC_BOX" => "c:/vagrant/#{guest_box.sub('/', '_')}.#{provider_name}.#{box_version}.box"
              }
            )
          else
            config.vm.provision(
              :shell,
              path: "./scripts/#{platform}-run.#{provider_name}.sh",
              keep_color: true,
              env: {
                "VAGRANT_SPEC_ARGS" => "--no-builtin #{spec_cmd_args}".strip,
                "VAGRANT_SPEC_BOX" => "/vagrant/test/vagrant-spec/boxes/#{guest_box.sub('/', '_')}.#{provider_name}.#{box_version}.box"
              }
            )
          end
        end
      end
    end
  end
end