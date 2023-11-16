package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class AssociationHierarchyEvaluator extends TestCase {

    private String evalClassName;
    private StringBuilder feedbackCommentSB;
    private String failureMsg;
    private int numTestsPassed = 0;


    /**
     * Creates a test case to evaluate the association of a class.
     * @param testName Name of the test case
     * @param testFile File to be evaluated
     * @param parameters Expected parameters of the constructor
     */
    public AssociationHierarchyEvaluator(String testName, File testFile, String[] parameters) {
        super(testName, testFile, parameters);
        this.evalClassName = testFile.getName().substring(0, testFile.getName().indexOf("."));
        this.feedbackCommentSB = new StringBuilder();
    }


    // Methods

    @Override
    public String getFeedbackComments() {
        return this.feedbackCommentSB.toString();    
    }

    private boolean associationCheck() throws Exception {
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
                if (!line.contains(".") && line.contains("Flight") || line.contains("Passenger") && this.evalClassName.equals("LuggageManagementSystem")) {
                    scan.close();
                    return true;
                } else if (!line.contains(".") && line.contains("LuggageSlip") && this.evalClassName.equals("LuggageManifest")){
                    scan.close();
                    return true;
                } else{ continue; }
            }

        } catch (Exception e) {
            System.out.println("Unable to extract attributes from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }

        this.failureMsg = "No association found ";
        return false;
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;

        this.feedbackCommentSB.append("Association check: " + (status ? "Passed" : "Failed") + "\n");
        if (!(status = this.associationCheck())) {
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;
        return (this.numTestsPassed == 1);
    }
}
