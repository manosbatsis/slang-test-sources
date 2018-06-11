/*
 * Copyright 2010-2018 JetBrains s.r.o. Use of this source code is governed by the Apache 2.0 license
 * that can be found in the license/LICENSE.txt file.
 */

package org.jetbrains.kotlin.idea.repl;

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
@TestMetadata("idea/testData/repl/completion")
@TestDataPath("$PROJECT_ROOT")
@RunWith(JUnit3RunnerWithInners.class)
public class IdeReplCompletionTestGenerated extends AbstractIdeReplCompletionTest {
    private void runTest(String testDataFilePath) throws Exception {
        KotlinTestUtils.runTest(this::doTest, TargetBackend.ANY, testDataFilePath);
    }

    public void testAllFilesPresentInCompletion() throws Exception {
        KotlinTestUtils.assertAllTestsPresentByMetadata(this.getClass(), new File("idea/testData/repl/completion"), Pattern.compile("^(.+)\\.kt$"), TargetBackend.ANY, true);
    }

    @TestMetadata("builtInMember.kt")
    public void testBuiltInMember() throws Exception {
        runTest("idea/testData/repl/completion/builtInMember.kt");
    }

    @TestMetadata("definedClass.kt")
    public void testDefinedClass() throws Exception {
        runTest("idea/testData/repl/completion/definedClass.kt");
    }

    @TestMetadata("definedClassMember.kt")
    public void testDefinedClassMember() throws Exception {
        runTest("idea/testData/repl/completion/definedClassMember.kt");
    }

    @TestMetadata("definedExtension.kt")
    public void testDefinedExtension() throws Exception {
        runTest("idea/testData/repl/completion/definedExtension.kt");
    }

    @TestMetadata("functions.kt")
    public void testFunctions() throws Exception {
        runTest("idea/testData/repl/completion/functions.kt");
    }

    @TestMetadata("stdlib.kt")
    public void testStdlib() throws Exception {
        runTest("idea/testData/repl/completion/stdlib.kt");
    }

    @TestMetadata("variables.kt")
    public void testVariables() throws Exception {
        runTest("idea/testData/repl/completion/variables.kt");
    }
}