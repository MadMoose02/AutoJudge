package com.team4.Evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;

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

    public double runEvaluators(ArrayList<File> submissions) {

        // For each submission
        for (File submission : submissions) {

            // Evaluate submission with each evaluator
            for (SyntaxEvaluator evaluator : this.evaluators) {
                this.score += evaluator.evaluate(submission);
            }
        }

        return this.score;
    }
}
