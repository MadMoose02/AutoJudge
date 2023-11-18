package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class CallbackEvaluator extends TestCase {

    private StringBuilder feedbackCommentSB;
    private String evalMethodName;
    private String methodBody;
    private String evalCallbackMethodName;

    /**
     * Default constructor
     * @param testName The name of the test case
     * @param testFile The file to test
     * @param parameters The method to check for callback
     * @param evalMethodName The method to evaluate
     */
    public CallbackEvaluator(String testName, File testFile, String[] parameters, String evalMethodName) {
        super(testName, testFile, parameters);
        this.feedbackCommentSB = new StringBuilder();
        this.evalMethodName = evalMethodName;
        this.evalCallbackMethodName = (this.parameters != null) ? this.parameters.get(0) : null;
    }


    // Methods

    /**
     * This method extracts the method body of the contructor from the submission file 
     * and trims each line into one  single String. 
     * @throws Exception Throws exception if unable to extract from file.
     */
    private void extractMethodBody() throws Exception {
        int iter = 0;
        String line = "";
        ArrayList<String> methodBodyLines = new ArrayList<>();
        Scanner scan;

        try {
            scan = new Scanner(this.testFile);

            // Skip lines until reached constructor
            while (scan.hasNextLine()) {
                if (++iter > MAX_LINES) break;
                line = scan.nextLine();
                if (!line.contains(this.evalMethodName)) continue;

                // Check if constructor is present
                if (line.contains(this.evalMethodName) && line.contains("(")) {
                    while (!line.contains("}")){
                        methodBodyLines.add(line);
                        line = scan.nextLine();
                    }
                    methodBodyLines.add(line);
                
                } else { continue; }

                // Concatenate method body lines
                for (String each : methodBodyLines) {
                    methodBody += each.trim();
                }
                scan.close();
                break;
            }

        } catch (Exception e) {
            System.out.println("Unable to extract method body from file: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }
    }



    @Override
    public boolean testCriteria() throws Exception {
        boolean status = false;
        extractMethodBody();

        status = methodBody.contains(evalCallbackMethodName);
        this.feedbackCommentSB.append("Method calls required function: " + ((status) ? "Passed" : "Failed") + "\n");

        if(!status)
            this.feedbackCommentSB.append("\n- Method '" + this.evalMethodName + "' does not call the required function: " 
                + evalCallbackMethodName + "\n");

        this.feedbackComments = this.feedbackCommentSB.toString();

        return status;
    }
}