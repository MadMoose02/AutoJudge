package com.team4;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.team4.Evaluator.ConcreteTestCases.ReturnTypeEvaluator;
import com.team4.Evaluator.TestCase.AbstractTestCase;

public class ReturnTypeEvaluatorTest {

    private boolean status = false;
    
    @Test
    public void testStringReturnType() {
        System.out.println("\nTest Case: testStringReturnType");
        String testFilePath = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator + "test" + File.separator + "resources" 
            + File.separator + "Flight.java";
        File testFile = new File(testFilePath);
        
        // Test case for constructor of Flight.java
        AbstractTestCase testCase = new ReturnTypeEvaluator(
            "Test correct return type for toString method",
            testFile,
            new String[] {"String"},
            "toString"
        );
            
        try { status = testCase.runTest(); } catch (Exception e) { e.printStackTrace(); }
        System.out.println(testCase.getFeedbackComments());
        assertTrue(status);
    }
}
