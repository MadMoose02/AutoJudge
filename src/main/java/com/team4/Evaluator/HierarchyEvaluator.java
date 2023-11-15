package com.team4.Evaluator;

import java.io.File;
import com.team4.Evaluator.ConcreteTestCases.AssociationHierarchyEvaluator;
import com.team4.Evaluator.TestCase.*;

public class HierarchyEvaluator implements SyntaxEvaluator {

    private double score;
    private AbstractTestCollection testCollection;

    /**
     * Default constructor
     */
    public HierarchyEvaluator() {
        this.score = 0.0;
        this.testCollection = new TestCollection();
    }

    // Methods

    @Override
    public double evaluate(File javaDocument) {
        String filename = javaDocument.getName().substring(0, javaDocument.getName().indexOf("."));

        if (filename.equals("LuggageManagementSystem")){
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Association check for '" + javaDocument.getName() + "'", 
                    javaDocument, 
                    new String[]{"Flight"}
                )
            );
            
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Association check for '" + javaDocument.getName() + "'", 
                    javaDocument, 
                    new String[]{"Passenger"}
                )
            );
        }

        if (filename.equals("LuggageManifest")){
            this.testCollection.addTestCase(
                new AssociationHierarchyEvaluator(
                    "Association check for '" + javaDocument.getName() + "'", 
                    javaDocument, 
                    new String[]{"LuggageSlip"}
                )
            );
        }

        AbstractTestCollectionIterator iterator = this.testCollection.getIterator();
        while (iterator.hasNext()) {
            AbstractTestCase testCase = iterator.next();
            try { this.score = (testCase.runTest()) ? 1.0 : 0.0; } 
            catch (Exception e) { System.out.println("\nTEST CASE FAILED"); }
        }

        return (double) this.score / (double) this.testCollection.size() * 100.0;

    }
    
}
