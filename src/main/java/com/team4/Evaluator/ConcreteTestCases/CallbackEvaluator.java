package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;

import com.team4.Evaluator.TestCase.TestCase;

public class CallbackEvaluator extends TestCase {

    private StringBuilder feedbackComments;
    private String evalMethodName;
    private String evalCallbackMethodName;

    public CallbackEvaluator(String testName, File testFile, String[] parameters, String evalMethodName) {
        super(testName, testFile, parameters);
        this.feedbackComments = new StringBuilder();
        this.evalMethodName = evalMethodName;
        this.evalCallbackMethodName = (this.parameters != null) ? this.parameters.get(0) : null;
    }

    @Override
    public String getFeedbackComments() {
        return this.feedbackComments.toString();
    }

    @Override
    public boolean testCriteria() throws Exception {

        // Sift through code, find code block with method name
        
        // Check that the method is called in the method body

        return true;
    }
    
}