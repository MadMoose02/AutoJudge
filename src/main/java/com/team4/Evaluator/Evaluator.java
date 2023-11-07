package com.team4.Evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Evaluator {
    
    // Attributes
    private Collection<SyntaxEvaluator> evaluators;
    private double score;

    /**
     * Default constructor
     */
    public Evaluator() {
        this.evaluators = new ArrayList<SyntaxEvaluator>();
        this.evaluators.add(new HierarchyEvaluator());
        this.evaluators.add(new NamingConventionsEvaluator());
        this.score = 0.0;
    }

    /**
     * Overload constructor - one argument 
     * @param evaluators Collection of evaluators to be used
     */
    public Evaluator(Collection<SyntaxEvaluator> evaluators) {
        this.evaluators = evaluators;
        this.score = 0.0;
    }

    
    // Methods

    /**
     * Evaluates all the submissions with all the evaluators supplied (all by default)
     * and returns the overall score
     * @param submissions List of files to be evaluated
     * @return The overall score of the submissions
     */
    public double evaluate(LinkedList<File> submissions) {

        // For each submission
        for (File submission : submissions) {

            // Evaluate submission with each evaluator
            for (SyntaxEvaluator evaluator : this.evaluators) {
                this.score += evaluator.evaluate(submission);
            }
        }

        return (double) this.score / (double) submissions.size();
    }
}
