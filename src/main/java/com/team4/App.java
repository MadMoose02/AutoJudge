package com.team4;

/**
 * Driver function of the program
 */
public class App {

    public static void main(String[] args) {
        JudgeSystem autoJudge = new JudgeSystem("Submissions.zip");
        autoJudge.evaluateSubmissions();
    }
}
