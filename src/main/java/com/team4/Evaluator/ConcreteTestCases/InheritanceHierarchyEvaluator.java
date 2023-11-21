package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

/**
 * A concrete subclass of the TestCase class that evaluates the inheritance of a class.
 * It contains a constructor which initialises attributes based on given parameters.
 * The constructor also initializes an attribute as a StringBuilder.
 * This class has a unique method which determines whether or not one class is inheriting from another. 
 * It overrides a class that was inherited by TestCase. 
 */
public class InheritanceHierarchyEvaluator extends TestCase {
    /** Feedback comments generated for the submission File by the StringBuilder */
    private StringBuilder feedbackCommentSB;
    
    /** Failure message that is displayed if the test case fails */
    private String failureMsg;
    
    /** Number of test cases passed */
    private int numTestsPassed = 0;


    /**
     * Creates a test case to evaluate the inheritance of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     */
    public InheritanceHierarchyEvaluator(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
        this.feedbackCommentSB = new StringBuilder();
    }


    // Methods
    //Produces feedback comments specific to the outcome of the inheritance check
    @Override
    public String getFeedbackComments() {
        return this.feedbackCommentSB.toString();    
    }

    /**
     * Checks if the specified file contains a class declaration with inheritance. 
     * The method reads the file until it finds a class declaration and 
     * checks if it extends another class.
     * @return {@code true} if the class in the file extends another class, {@code false} otherwise.
     * @throws Exception if an error occurs during file reading.
     * The detailed error information is printed to the console.
     */
    private boolean inheritanceCheck() throws Exception {
        int iter = 0;
        String line = "";
        Scanner scan;

        try {
            scan = new Scanner(this.testFile);

            // Skip lines until reached class
            while (scan.hasNextLine()) {
                if (++iter > MAX_LINES) break;
                line = scan.nextLine();

                // Check if class declaration is found and 
                // whether or not it contains the keyword "extends"
                if (line.contains("class") && line.contains("extends")) {
                    scan.close();
                    return true;
                } else if (line.contains("class")){ 
                    scan.close();
                    this.failureMsg = "No inheritance found ";
                    return false;
                } else{ continue; }
            }

        } catch (Exception e) {
            System.out.println("Unable to extract attributes from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }

        this.failureMsg = "No inheritance found ";
        return false;
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;

        //Feedback comments produced based on result of the inheritance check
        if (!(status = this.inheritanceCheck())) {
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;
        System.out.println("Inheritance check: " + (status ? "Passed" : "Failed"));
        
        return (this.numTestsPassed == 1);
    }
}
