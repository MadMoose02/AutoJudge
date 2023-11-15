package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class AssociationHierarchyEvaluator extends TestCase {

    private String attributes;
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

    private void extractAttributes() throws Exception {
        int iter = 0;
        String line = "";
        ArrayList<String> attributeLines = new ArrayList<>();
        Scanner scan;

        try {
            scan = new Scanner(this.testFile);

            // Skip lines until reached class
            while (scan.hasNextLine()) {
                if (++iter > MAX_LINES) break;
                line = scan.nextLine();
                if (!line.contains(this.evalClassName)) continue;

                // Check if attributes are present
                if (line.contains("class") && line.contains(this.evalClassName) && line.contains("{")) {
                    while (!line.contains("(")){
                        attributeLines.add(line);
                        line = scan.nextLine();
                    }
                
                } else { continue; }

                // Concatenate attribute lines
                for (String each : attributeLines) {
                    this.attributes += each.trim();
                }
                scan.close();
                break;
            }

        } catch (Exception e) {
            System.out.println("Unable to extract attributes from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }
    }


    private boolean checkAssociation() {
        String[] attributeLines = this.attributes.split(";");
        ArrayList<String> attributes = new ArrayList<>(List.of(attributeLines));
        ArrayList<String> foundAttributeTypes = new ArrayList<>();
        
        for (String each : attributes) {
            if (each.contains("Flight") || each.contains("Passenger") && this.evalClassName.equals("LuggageManagementSystem")) {
                foundAttributeTypes.add(each);
            }
            if (each.contains("LuggageSlip") && this.evalClassName.equals("LuggageManifest")) {
                foundAttributeTypes.add(each);
            }
        }
        
        if (foundAttributeTypes.isEmpty()) {
            this.failureMsg = "\n  No association found: ";
            return false;
        }
        else
            return true;
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;
        extractAttributes();

        if (!(status = this.checkAssociation())) {
            this.feedbackCommentSB.append(this.failureMsg);
            this.feedbackCommentSB.append("\n");
        }
        if (status) this.numTestsPassed++;
        System.out.println("Association check: " + (status ? "Passed" : "Failed"));
        
        return (this.numTestsPassed == 1);
    }
}
