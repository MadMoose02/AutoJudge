package com.team4;

import static org.junit.Assert.assertTrue;

import java.io.File;

import org.junit.Test;

import com.team4.Evaluator.ConcreteTestCases.*;
import com.team4.Evaluator.TestCase.*;

public class ConstructorBehaviourEvaluatorTest {

    private boolean status;
    
    @Test
    public void testFlightConstructorBehaviour() {
        System.out.println("\nTest Case: testFlightConstructorBehaviour");
        String testFilePath = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator + "test" + File.separator + "resources" 
            + File.separator + "Flight.java";
        File testFile = new File(testFilePath);
        
        // Test case for constructor of Flight.java
        AbstractTestCase testCase = new ConstructorBehaviourEvaluator(
            "Correct Flight constructor behaviour", 
            testFile, 
            new String[]{
                "String flightNo", 
                "String destination", 
                "String origin", 
                "LocalDateTime flightDate"
            },
            new String[]{
                "LuggageManifest manifest"
            }
            );
            
            try { status = testCase.runTest(); } catch (Exception e) { e.printStackTrace(); }
            System.out.println(testCase.getFeedbackComments());
            assertTrue(status);
        }
        
    @Test
    public void testLuggageSlipConstructorBehaviour() {
        System.out.println("\nTest Case: testLuggageSlipConstructorBehaviour");
        String testFilePath = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator + "test" + File.separator + "resources" 
            + File.separator + "LuggageSlip.java";
        File testFile = new File(testFilePath);

        // Test case for constructor of LuggageSlip.java
        AbstractTestCase testCase = new ConstructorBehaviourEvaluator(
            "Correct LuggageSlip constructor behaviour", 
            testFile, 
            new String[]{
                "Passenger p", 
                "Flight f"
            },
            new String[]{
                "Passenger owner",
                "String luggageSlipID",
                "String label"
            },
            new String[]{
                "static int luggageSlipIDCounter"
            }
        );
        
        try { status = testCase.runTest(); } catch (Exception e) { e.printStackTrace(); }
        testCase.getFeedbackComments();
        assertTrue(status);
    }

    @Test
    public void testPassengerConstructorBehaviour() {
        System.out.println("\nTest Case: testPassengerConstructorBehaviour");
        String testFilePath = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator + "test" + File.separator + "resources" 
            + File.separator + "Passenger.java";
        File testFile = new File(testFilePath);
        
        // Test case for constructor of Flight.java
        AbstractTestCase testCase = new ConstructorBehaviourEvaluator(
            "Correct Flight constructor behaviour", 
            testFile, 
            new String[]{
                "String passportNumber",
                "String firstName",
                "String lastName",
                "String flightNo"
            }
        );
            
        try { status = testCase.runTest(); } catch (Exception e) { e.printStackTrace(); }
        testCase.getFeedbackComments();
        assertTrue(status);
    }
}
