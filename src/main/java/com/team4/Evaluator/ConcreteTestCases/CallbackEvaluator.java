package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class CallbackEvaluator extends TestCase {

    private StringBuilder feedbackComments;
    private String evalMethodName;
    private String methodBody;
    private String evalCallbackMethodName;

    public CallbackEvaluator(String testName, File testFile, String[] parameters, String evalMethodName) {
        super(testName, testFile, parameters);
        this.feedbackComments = new StringBuilder();
        this.evalMethodName = evalMethodName;
        this.evalCallbackMethodName = (this.parameters != null) ? this.parameters.get(0) : null;
    }

    @Override
    public String getFeedbackComments() {
        return this.feedbackComments.toString();
    }

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
        this.feedbackComments.append("Method calls required function: " + ((status) ? "Passed" : "Failed") + "\n");

        if(!status)
            this.feedbackComments.append("\n- Method '" + this.evalMethodName + "' does not call the required function: " 
                + evalCallbackMethodName + "\n");

        return status;
    }
}