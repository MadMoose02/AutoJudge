package com.team4.Evaluator.TestCase;

import java.io.File;

public abstract class TestCase implements AbstractTestCase {

    protected String testName;
    protected File testFile;

    public TestCase(String testName, File testFile) {
        this.testName = testName;
        this.testFile = testFile;
    }

    // Abstract method
    public abstract boolean testCriteria();

    // Template method
    public final boolean runTest() {
        return this.testCriteria();
    }
}
