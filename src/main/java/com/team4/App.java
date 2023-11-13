package com.team4;

/**
 * Driver function of the program
 */
public class App {

    public static void main(String[] args) {
        AutoJudge autoJudge = new AutoJudgeSystem("Submissions.zip");
        autoJudge.evaluateSubmissions();
        autoJudge.generatePDFReport();
        autoJudge.displayEvaluationResults();
        System.out.println("ghh");
        
    }
}
