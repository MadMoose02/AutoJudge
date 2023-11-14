package com.team4.Evaluator;

import java.io.File;

import com.team4.Evaluator.ConcreteTestCases.ConstructorBehaviourEvaluator;
import com.team4.Evaluator.TestCase.AbstractTestCase;
import com.team4.Evaluator.TestCase.AbstractTestCollectionIterator;
import com.team4.Evaluator.TestCase.TestCollection;

public class BehaviourEvaluator implements SyntaxEvaluator {
        
    private double score;
    private TestCollection testCollection;
    private String[] evalParams = null;
    private String[] instanceAttributes = null;
    private String[] classAttributes = null;

    public BehaviourEvaluator() {
        this.score = 0.0;
        this.testCollection = new TestCollection();
    }

    public BehaviourEvaluator(TestCollection testCollection) {
        this.score = 0.0;
        this.testCollection = testCollection;
    }

    private void setEvaluationConfig(File rubric) {
        if (rubric == null) return;

        if (rubric.getName().contains("Flight")) {
            this.evalParams = new String[]{
                "String flightNo", 
                "String destination", 
                "String origin", 
                "LocalDateTime flightDate"
            };
            this.classAttributes = new String[]{
                "LuggageManifest manifest"
            };
        }

        if (rubric.getName().contains("LuggageSlip")) {
            this.evalParams = new String[]{
                "Passenger p", 
                "Flight f"
            };
            this.instanceAttributes = new String[]{
                "Passenger owner",
                "String luggageSlipID",
                "String label"
            };
            this.classAttributes = new String[]{
                "static int luggageSlipIDCounter"
            };
        }

        if (rubric.getName().contains("Passenger")) {
            this.evalParams = new String[]{
                "String passportNumber",
                "String firstName",
                "String lastName",
                "String flightNo"
            };
        }

        if (rubric.getName().contains("LuggageManifest")) {
            this.instanceAttributes = new String[]{
                "ArrayList<LuggageSlip> slips"
            };
        }
    }

    @Override
    public double evaluate(File javaDocument) {
        
        // Evaluate the constructor behaviour
        if (!javaDocument.getName().contains("LuggageManagementSystem")) {
            this.setEvaluationConfig(javaDocument);
            this.testCollection.addTestCase(
                new ConstructorBehaviourEvaluator(
                    "Test " + javaDocument.getName() + " constructor behaviour",
                    javaDocument,
                    this.evalParams,
                    this.instanceAttributes,
                    this.classAttributes
                )
            );
        }

        // Evaluate all other method behaviours
        try {
            AbstractTestCollectionIterator iter = this.testCollection.getIterator();
            while (iter.hasNext()) {
                AbstractTestCase testCase = iter.next();
                this.score += (testCase.runTest()) ? 1.0 : 0.0;
                System.out.println(testCase.getFeedbackComments());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return this.score / (double) this.testCollection.size() * 100.0;
    }
    
}
