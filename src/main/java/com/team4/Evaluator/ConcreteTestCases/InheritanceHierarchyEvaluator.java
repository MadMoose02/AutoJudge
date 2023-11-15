package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class InheritanceHierarchyEvaluator extends TestCase {
    private StringBuilder feedbackCommentSB;
    private String failureMsg;
    private int numTestsPassed = 0;


    /**
     * Creates a test case to evaluate the association of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     */
    public InheritanceHierarchyEvaluator(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
        this.feedbackCommentSB = new StringBuilder();
    }


    // Methods

    @Override
    public String getFeedbackComments() {
        return this.feedbackCommentSB.toString();    
    }

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

                // Check if attributes are present
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

        if (!(status = this.inheritanceCheck())) {
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;
        System.out.println("Inheritance check: " + (status ? "Passed" : "Failed"));
        
        return (this.numTestsPassed == 1);
    }
}
