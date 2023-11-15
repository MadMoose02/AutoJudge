package com.team4.Evaluator.TestCase;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public abstract class TestCase implements AbstractTestCase {

    // Attributes
    protected final static int MAX_LINES = 200; 
    protected String testName;
    protected File testFile;
    protected ArrayList<String> parameters;
    protected ArrayList<String> feedbackComments;

    /**
     * Default constructor
     * @param testName The name of the test case
     * @param testFile The file to test
     * @param parameters The parameters to pass the test case
     */
    public TestCase(String testName, File testFile, String[] parameters) {
        this.testName = testName;
        this.testFile = testFile;
        this.parameters = (parameters != null) ? new ArrayList<>(List.of(parameters)) : new ArrayList<>();
        this.feedbackComments = new ArrayList<>();
    }

    // Abstract method
    public abstract boolean testCriteria() throws Exception;

    // Template method
    public final boolean runTest() throws Exception {
        System.out.println("* Running Test Case: " + this.testName);
        return this.testCriteria();
    }
}
