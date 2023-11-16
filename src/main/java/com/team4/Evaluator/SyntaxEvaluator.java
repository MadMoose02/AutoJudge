package com.team4.Evaluator;

import java.io.File;

public interface SyntaxEvaluator {
    
    /**
     * Evaluates the syntax of the given java document and returns a percentage
     * score of how many test cases were passed 
     * @param javaDocumentString
     * @return Score between 0.0 and 100.0
     */
    double evaluate(File javaDocument);


    /**
     * Returns the feedback comments for the evaluation
     */
    String getFeedbackComments();
}
