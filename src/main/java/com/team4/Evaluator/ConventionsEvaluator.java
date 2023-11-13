package com.team4.Evaluator;

import java.io.File;

public class ConventionsEvaluator implements SyntaxEvaluator {
    
    private double score;
    private String filename;
    private String className;
    private String constructorName;
    private String methodName;
    private boolean correctSignature;

    public ConventionsEvaluator() {
        this.score = 0.0;
    }

    private boolean testClassName(String className) {
        return this.className.equals(className);
    }

    private boolean testConstructorName(String constructorName) {
        return this.constructorName.equals(constructorName);
    }

    @Override
    public double evaluate(File javaDocument) {
        return this.score;
    }
}
