// "Import" "true"
// LANGUAGE_VERSION: 1.3

package foo

open class Bar {
    companion object {
        class FromBarCompanion {
            fun foo() = 42
        }
    }
}

class Foo : Bar() {
    val a = <caret>FromBarCompanion::foo
}