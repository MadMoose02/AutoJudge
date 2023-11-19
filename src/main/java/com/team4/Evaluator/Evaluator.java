package com.team4.Evaluator;

import java.io.File;
import java.util.ArrayList;

public class Evaluator implements SyntaxEvaluator {
    
    // Attributes

    /** A Collection of SyntaxEvaluator objects used to evaluate a submission File */
    private ArrayList<SyntaxEvaluator> evaluators = new ArrayList<>();

    /** The average score of the submission File after being evaluated by each SyntaxEvaluator */
    private double score;

    /**
     * Default constructor of an Evaluator composite class. <p>
     * Will create an instance of each type of
     * SyntaxEvaluator and store them into an internal Collection.
     */
    public Evaluator() {
        this.evaluators.add(new HierarchyEvaluator());
        // this.evaluators.add(new ConventionsEvaluator());
        this.evaluators.add(new BehaviourEvaluator());
        this.score = 0.0;
    }


    // Getters

    public String getFeedbackComments() {
        StringBuilder feedbackComments = new StringBuilder();
        for (SyntaxEvaluator evaluator : this.evaluators) {
            feedbackComments.append(
                "Evaluation Type: " + evaluator.getClass().getSimpleName() + "\n" +
                evaluator.getFeedbackComments() + "\n\n"
            );
        }
        return feedbackComments.toString();
    }


    // Methods

    /**
     * Evaluates the input File using all the SyntaxEvaluators stored in the internal Collection and
     * computes an average score based on all the test cases passed per SyntaxEvaluator
     * @param  javaDocument The File to be evaluated
     * @return A decimal percentage score between 0.0 and 100.0
     */
    @Override
    public double evaluate(File javaDocument) {
        for (SyntaxEvaluator evaluator : this.evaluators) {
            this.score += evaluator.evaluate(javaDocument);
        }
        return (this.score) / ((double) this.evaluators.size()) * 100.0;
    }
}
