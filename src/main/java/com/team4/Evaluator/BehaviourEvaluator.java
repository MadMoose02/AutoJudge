package com.team4.Evaluator;

import java.io.File;

import com.team4.Evaluator.TestCase.AbstractTestCollection;
import com.team4.Evaluator.TestCase.TestCollection;

public class BehaviourEvaluator implements SyntaxEvaluator {
        
    private double score;
    private AbstractTestCollection testCollection;

    public BehaviourEvaluator() {
        this.score = 0.0;
        this.testCollection = new TestCollection();
    }

    public BehaviourEvaluator(AbstractTestCollection testCollection) {
        this.score = 0.0;
        this.testCollection = testCollection;
    }

    @Override
    public double evaluate(File javaDocument) {
        while (this.testCollection.next() != null) {
            this.testCollection.next().runTest();
        }
        return this.score;
    }
    
}
