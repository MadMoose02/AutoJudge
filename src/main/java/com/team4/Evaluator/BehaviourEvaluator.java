package com.team4.Evaluator;

import java.io.File;

import com.team4.Evaluator.ConcreteTestCases.CallbackEvaluator;
import com.team4.Evaluator.ConcreteTestCases.ConstructorBehaviourEvaluator;
import com.team4.Evaluator.ConcreteTestCases.ReturnTypeEvaluator;
import com.team4.Evaluator.TestCase.AbstractTestCase;
import com.team4.Evaluator.TestCase.AbstractTestCollectionIterator;
import com.team4.Evaluator.TestCase.TestCollection;

public class BehaviourEvaluator implements SyntaxEvaluator {
        
    private double score;
    private TestCollection testCollection;
    private StringBuilder feedbackComments;
    private String[] evalParams = null;
    private String[] instanceAttributes = null;
    private String[] classAttributes = null;

    /**
     * Default constructor
     */
    public BehaviourEvaluator() {
        this.score = 0.0;
        this.testCollection = new TestCollection();
        this.feedbackComments = new StringBuilder();
    }


    // Getters

    @Override
    public String getFeedbackComments() { return this.feedbackComments.toString(); }


    // Methods

    private void setEvaluationConfig(File rubric) {
        if (rubric == null) return;

        if (rubric.getName().contains("Flight")) {
            this.evalParams = new String[]{
                "String flightNo", 
                "String destination", 
                "String origin", 
                "LocalDateTime flightDate"
            };
            this.instanceAttributes = new String[]{
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
        String filename = javaDocument.getName().substring(0, javaDocument.getName().indexOf("."));

        // Evaluate the constructor behaviour
        if (!filename.contains("LuggageManagementSystem")) {
            this.setEvaluationConfig(javaDocument);
            this.testCollection.addTestCase(
                new ConstructorBehaviourEvaluator(
                    "Test '" + filename + "' constructor behaviour",
                    javaDocument,
                    this.evalParams,
                    this.instanceAttributes,
                    this.classAttributes
                )
            );
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'String' return type of 'toString' in '" + filename + "'",
                    javaDocument,
                    new String[]{"String"},
                    "toString"
                )
            );
        }

        if (filename.equals("Passenger")) {
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'void' return type of 'assignRandomCabinClass' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"void"},
                    "assignRandomCabinClass"
                )
            );
        }

        if (filename.equals("LuggageSlip")) {
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'boolean' return type of 'hasOwner' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"boolean"},
                    "hasOwner"
                )
            );
        }

        if (filename.equals("LuggageManifest")) {
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'String' return type of 'addLuggage' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"String"},
                    "addLuggage"
                )
            );
            
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'double' return type of 'getExcessLuggageCost' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"double"},
                    "removeLuggage"
                )
            );
        }

        if (filename.equals("Flight")) {
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'String' return type of 'checkInLuggage' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"String"},
                    "checkInLuggage"
                )
            );
            
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'String' return type of 'printLuggageManifest' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"String"},
                    "printLuggageManifest"
                )
            );
            
            this.testCollection.addTestCase(
                new ReturnTypeEvaluator(
                    "Check for 'int' return type of 'getAllowedLuggage' in " + filename + "'" , 
                    javaDocument,
                    new String[] {"int"},
                    "getAllowedLuggage"
                )
            );
        }

        // Evaluate the test cases
        AbstractTestCollectionIterator iterator = this.testCollection.getIterator();
        while (iterator.hasNext()) {
            AbstractTestCase testCase = iterator.next();
            try { 
                this.score = (testCase.runTest()) ? 1.0 : 0.0;
                this.feedbackComments.append(testCase.getFeedbackComments()); 
            } catch (Exception e) { System.out.println("\nTEST CASE FAILED"); }
        }
        return this.score / (double) this.testCollection.size() * 100.0;
    }
}
