package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class ReturnTypeEvaluator extends TestCase {

    private StringBuilder feedbackCommentsSB;
    private String evalMethodSignature;
    private String evalMethodName;
    private String evalReturnType;

    /**
     * Default constructor
     * @param testName The name of the test case
     * @param testFile The file to test
     * @param parameters The return type to check
     * @param evalMethodName The method to evaluate
     */
    public ReturnTypeEvaluator(String testName, File testFile, String[] parameters, String evalMethodName) {
        super(testName, testFile, parameters);
        this.feedbackCommentsSB = new StringBuilder();
        this.evalMethodName = evalMethodName;
    }


    // Methods

    @Override
    public String getFeedbackComments() {
        return this.feedbackCommentsSB.toString();
    }

    private boolean locateEvalMethod(){
        String line = "";

        try (Scanner scan = new Scanner(testFile)) {
            while (scan.hasNextLine()) {
                line = scan.nextLine().trim();

                // Too short, does not contain function name, does not contain opening parenthesis
                if (line.length() < this.evalMethodName.length()) continue;
                if (!line.contains(this.evalMethodName) || !line.contains("(")) continue;
                
                line = line.substring(0, line.indexOf("("));
                if (line.contains(this.parameters.get(0)) && line.contains(this.evalMethodName)) {
                    this.evalMethodSignature = line;
                    return true;
                }
            }
            this.feedbackCommentsSB.append("\n- Method '" + this.evalMethodName + "' not found in '" 
                + this.testFile.getName() + "'\n");
            
        } catch (Exception e) {
            e.printStackTrace(); 
        }

        return false;
    }

    private void locateEvalMethodReturnType() {
        String[] methodSignature = this.evalMethodSignature.replace("static", "").split(" ");
        if (methodSignature[0].trim().equals(this.testFile.getName().replace(".java", ""))) {
            this.evalReturnType = "void";
            return;
        }
        this.evalReturnType = methodSignature[1].trim();
    }

    @Override
    public boolean testCriteria() throws Exception {
        boolean status;
        
        if (!(status = locateEvalMethod())) {
            return status;
        }
        
        locateEvalMethodReturnType();
        
        this.feedbackCommentsSB.append("Return Type check: " + ((status) ? "Passed" : "Failed") + "\n");
        if (!(status = this.parameters.get(0).equals(evalReturnType))) {
            this.feedbackCommentsSB.append("\n- Method does not have correct return type\n");
            this.feedbackCommentsSB.append("  Expected: " + this.parameters.get(0) + "\n");
            this.feedbackCommentsSB.append("  Actual: " + this.evalReturnType + "\n");
        }
        return status;
    }
}
