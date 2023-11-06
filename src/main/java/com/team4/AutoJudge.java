package com.team4;

public interface AutoJudge {
    
    /**
     * Evaluating all of the required features of the Java classes based on an
     * assignment specification (such as class/method/attribute naming conventions
     * and types, behaviour and functionality of methods, expected abstractions
     * and inheritance hierarchies)
     */
    void evaluateSubmissions();

    /**
     * Produces a PDF file that contains a list of all of the tests that the Java 
     * classes passed/failed together with helpful & corrective feedback for the 
     * failed tests. This report file is added to the same location as the student 
     * submission files
     */
    void generatePDFReport();

    /**
     * Calculates an overall score for the submission using the assignment rubric
     * and prints a breakdown of the marks per test for each class.
     */
    void displayEvaluationResults();
}