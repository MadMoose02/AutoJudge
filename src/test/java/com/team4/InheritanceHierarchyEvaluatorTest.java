package com.team4;

import java.io.File;

import org.junit.Test;
import com.team4.Evaluator.ConcreteTestCases.InheritanceHierarchyEvaluator;

public class InheritanceHierarchyEvaluatorTest {
    @Test
    public void testInheritanceHierarchyEvaluatorTest() {
        String testFilePath = System.getProperty("user.dir") + File.separator + "src" + File.separator + 
            "test" + File.separator + "resources" + File.separator + "LuggageManagementSystem.java";
        File testFile = new File(testFilePath);
        InheritanceHierarchyEvaluator test = new InheritanceHierarchyEvaluator(
            "test", 
            testFile,
            new String[]{"extends"}
        );

        try {test.runTest();} catch (Exception e) { e.printStackTrace(); }
        System.out.println(test.getFeedbackComments());
    }
}
