package com.team4;

import java.io.File;

import org.junit.Test;

import com.team4.Evaluator.ConcreteTestCases.AssociationHierarchyEvaluator;

public class AssociationHierarchyEvaluatorTest {
    
    @Test
    public void testAssociationHierarchyEvaluator() {
        String testFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + 
            "test" + File.separator + "resources" + File.separator + "LuggageManagementSystem.java";
        File testFile = new File(testFilePath);
        AssociationHierarchyEvaluator test = new AssociationHierarchyEvaluator(
            "test", 
            testFile,
            new String[]{"Flight"}
        );

        AssociationHierarchyEvaluator test1 = new AssociationHierarchyEvaluator(
            "test1", 
            testFile,
            new String[]{"Passenger"}
        );

        try { 
            test.runTest(); 
            System.out.println(test.getFeedbackComments());

            test1.runTest(); 
            System.out.println(test1.getFeedbackComments());
        } catch (Exception e) { e.printStackTrace(); }
        
    }
}
