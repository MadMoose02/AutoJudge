package com.team4.Evaluator;

import java.io.File;

public class NamingConventionsEvaluator implements SyntaxEvaluator {
    
    private double score;

    public NamingConventionsEvaluator() {
        this.score = 0.0;
    }

    @Override
    public double evaluate(File javaDocument) {
        return this.score;
    }
}
