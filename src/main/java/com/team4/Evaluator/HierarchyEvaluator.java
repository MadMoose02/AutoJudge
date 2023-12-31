package com.team4.Evaluator;

import java.io.File;

import com.team4.Evaluator.ConcreteTestCases.AssociationHierarchyEvaluator;
import com.team4.Evaluator.ConcreteTestCases.InheritanceHierarchyEvaluator;
import com.team4.Evaluator.TestCase.*;

public class HierarchyEvaluator implements SyntaxEvaluator {

    private double score;
    private AbstractTestCollection testCollection;
    private StringBuilder feedbackComments;

    public HierarchyEvaluator() {
        this.score = 0.0;
        this.testCollection = new TestCollection();
        this.feedbackComments = new StringBuilder();
    }

    // Getters
    
    @Override
    public String getFeedbackComments() { return this.feedbackComments.toString(); }


    // Methods

    @Override
    public double evaluate(File javaDocument) {
        String filename = javaDocument.getName().substring(0, javaDocument.getName().indexOf("."));

        if (filename.equals("LuggageManagementSystem")){
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Test for association with 'Flight' in '" + filename + "'", 
                    javaDocument, 
                    new String[]{"Flight"}
                )
            );
            
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Test for association with 'Passenger' in '" + filename + "'", 
                    javaDocument, 
                    new String[]{"Passenger"}
                )
            );

            this.testCollection.addTestCase(
                new InheritanceHierarchyEvaluator(
                    "Inheritance check for '" + filename + "'", 
                    javaDocument, 
                    new String[]{"extends"}
                )
            );
        }

        if (filename.equals("LuggageManifest")){
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Test for association with 'LuggageSlip' in '" + filename + "'", 
                    javaDocument, 
                    new String[]{"LuggageSlip"}
                )
            );

            this.testCollection.addTestCase(
                new InheritanceHierarchyEvaluator(
                    "Inheritance check for '" + filename + "'", 
                    javaDocument, 
                    new String[]{"extends"}
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
        return (double) this.score / (double) this.testCollection.size() * 100.0;
    }    
}
