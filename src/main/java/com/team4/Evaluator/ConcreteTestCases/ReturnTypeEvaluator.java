package com.team4.Evaluator.ConcreteTestCases;

import java.io.File;
import java.util.Scanner;

import com.team4.Evaluator.TestCase.TestCase;

public class ReturnTypeEvaluator extends TestCase {

    private StringBuilder feedbackComments;
    private String evalMethodName;
    private String evalReturnType;

    public ReturnTypeEvaluator(String testName, File testFile, String[] parameters, String evalMethodName) {
        super(testName, testFile, parameters);
        this.feedbackComments = new StringBuilder();
        this.evalMethodName = evalMethodName;
        this.evalReturnType = (this.parameters != null) ? this.parameters.get(0) : null;
    }

    @Override
    public String getFeedbackComments() {
        return this.feedbackComments.toString();
    }

    private String locateMethod(){
        String line = " ";
        String found = " ";
        Scanner scan;
        try {
            scan = new Scanner(testFile);

            while(scan.hasNextLine()){
                line = scan.nextLine();
                if (!line.contains(evalMethodName)) continue;
                if (line.contains(evalMethodName)){
                   found = line ;
                }
                scan.close();
            }
            
        } catch (Exception e) {
            System.out.println("Unable to locate method: " + this.testFile.getName()); 
            e.printStackTrace(); 
        }
        return found;
    }

    @Override
    public boolean testCriteria() throws Exception {

       String found = locateMethod();
       Boolean flag = false;

        if (!(found.contains(evalReturnType))) {
            this.feedbackComments.append("\n- Method does not have correct return type");
            this.feedbackComments.append("\n");
        }
        if (found.contains(evalReturnType)){
            System.out.println("Return Type check: " + (found.contains(evalReturnType) ? "Passed" : "Failed"));
            flag = true;
        }
        
        return flag;
    }
}
