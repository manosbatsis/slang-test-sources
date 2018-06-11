/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.script;

import com.intellij.testFramework.TestDataPath;
import org.jetbrains.kotlin.test.JUnit3RunnerWithInners;
import org.jetbrains.kotlin.test.KotlinTestUtils;
import org.jetbrains.kotlin.test.TargetBackend;
import org.jetbrains.kotlin.test.TestMetadata;
import org.junit.runner.RunWith;

import java.io.File;
import java.util.regex.Pattern;

/** This class is generated by {@link org.jetbrains.kotlin.generators.tests.TestsPackage}. DO NOT MODIFY MANUALLY */
@SuppressWarnings("all")
@TestMetadata("idea/testData/script/definition/navigation")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class ScriptConfigurationNavigationTestGenerated extends AbstractScriptConfigurationNavigationTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
    }

    public void testAllFilesPresentInNavigation() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/script/definition/navigation"), Pattern.compile("^([^\\.]+)$"), TargetBackend.ANY, false);
    }

    @TestMetadata("conflictingModule")
    public void testConflictingModule() throws Exception {
        runTest("idea/testData/script/definition/navigation/conflictingModule/");
    }

    @TestMetadata("customBaseClass")
    public void testCustomBaseClass() throws Exception {
        runTest("idea/testData/script/definition/navigation/customBaseClass/");
    }

    @TestMetadata("javaLib")
    public void testJavaLib() throws Exception {
        runTest("idea/testData/script/definition/navigation/javaLib/");
    }

    @TestMetadata("javaLibWithSources")
    public void testJavaLibWithSources() throws Exception {
        runTest("idea/testData/script/definition/navigation/javaLibWithSources/");
    }

    @TestMetadata("kotlinLib")
    public void testKotlinLib() throws Exception {
        runTest("idea/testData/script/definition/navigation/kotlinLib/");
    }

    @TestMetadata("kotlinLibWithSources")
    public void testKotlinLibWithSources() throws Exception {
        runTest("idea/testData/script/definition/navigation/kotlinLibWithSources/");
    }

    @TestMetadata("stdlib")
    public void testStdlib() throws Exception {
        runTest("idea/testData/script/definition/navigation/stdlib/");
    }

    @TestMetadata("stdlibWithSources")
    public void testStdlibWithSources() throws Exception {
        runTest("idea/testData/script/definition/navigation/stdlibWithSources/");
    }
}