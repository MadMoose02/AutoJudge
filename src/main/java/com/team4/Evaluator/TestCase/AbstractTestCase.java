package com.team4.Evaluator.TestCase;

public interface AbstractTestCase {
    public String getFeedbackComments();
    public boolean testCriteria() throws Exception;
    public boolean runTest() throws Exception;
}
