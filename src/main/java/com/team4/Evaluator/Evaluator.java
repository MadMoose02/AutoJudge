package com.team4.Evaluator;

import java.io.File;
import java.util.ArrayList;

public class Evaluator implements SyntaxEvaluator {
    
    // Attributes
    private ArrayList<SyntaxEvaluator> evaluators = new ArrayList<>();
    private double score;

    /**
     * Default constructor
     */
    public Evaluator() {
        this.evaluators.add(new HierarchyEvaluator());
        this.evaluators.add(new ConventionsEvaluator());
        this.evaluators.add(new BehaviourEvaluator());
        this.score = 0.0;
    }


    // Methods

    @Override
    public double evaluate(File javaDocument) {
        for (SyntaxEvaluator evaluator : this.evaluators) {
            this.score += evaluator.evaluate(javaDocument);
        }
        return (this.score) / ((double) this.evaluators.size()) * 100.0;
    }
}
