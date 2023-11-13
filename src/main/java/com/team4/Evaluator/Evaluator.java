package com.team4.Evaluator;

import java.io.File;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

public class Evaluator implements SyntaxEvaluator {
    
    // Attributes
    private Collection<SyntaxEvaluator> evaluators;
    private double score;

    /**
     * Default constructor
     */
    public Evaluator() {
        this.evaluators = new ArrayList<SyntaxEvaluator>();
        this.evaluators.add(new HierarchyEvaluator());
        this.evaluators.add(new ConventionsEvaluator());
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

    @Override
    public double evaluate(File javaDocument) {
        for (SyntaxEvaluator evaluator : this.evaluators) {
            this.score += evaluator.evaluate(javaDocument);
        }
        return this.score;
    }
}
