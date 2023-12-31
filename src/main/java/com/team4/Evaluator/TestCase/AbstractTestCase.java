package com.team4.Evaluator.TestCase;

/**
 * An interface that defines methods that can be used to test some particular
 * aspect of a File submission
 */
public interface AbstractTestCase {

    /**
     * Returns a String containing all the feedback comments generated by the AbstractTestCase after
     * the {@code runTest()} method is executed. Defaults to a blank String if called pre-maturely
     * @return A String containing feedback comments about the submission File that was tested
     */
    public String getFeedbackComments();

    /**
     * Tests some particular aspect of the File submission
     * @return {@code True} if the criteria being tested passed, {@code False} otherwise
     * @throws Exception Testing can fail if the submission File is corrupt, or an unforseen error
     * occurs during the evaluation process
     */
    public boolean testCriteria() throws Exception;

    /**
     * Template method that calls the {@code testCriteria()} method (class implementation) and returns 
     * the result
     * @return {@code True} if {@code testCriteria()} returns {@code True}, {@code False} otherwise
     * @throws Exception Testing can fail if the submission File is corrupt, or an unforseen error
     * occurs during the evaluation process
     */
    public boolean runTest() throws Exception;
}
