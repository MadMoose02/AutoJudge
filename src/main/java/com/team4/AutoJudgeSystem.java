package com.team4;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeMap;

import com.team4.Evaluator.Evaluator;

public class AutoJudgeSystem implements AutoJudge {

    // Attributes
    private String resourcesPath;
    private String zippedSubmissionsFilename;
    private TreeMap<String, LinkedList<File>> submissions;
    private Evaluator evaluator;
    private SubmissionDecompressor submissionDecompressor;
    private double overallScore;


    /**
     * Default constructor - one argument
     * @param zippedSubmissionsFilename Name of the zipped submissions file
     */
    public AutoJudgeSystem(String zippedSubmissionsFilename) {
        this.setResourcesPath();
        this.zippedSubmissionsFilename = zippedSubmissionsFilename;
        this.submissions = new TreeMap<>();
        this.evaluator = new Evaluator();
        this.overallScore = 0.0;
    }

    /**
     * Overload constructor - two arguments
     * @param resourcesPath Absolute path to the resources directory containing the 
     * zipped submissions file
     * @param zippedSubmissionsFilename Name of the zipped submissions file
     */
    public AutoJudgeSystem(String resourcesPath, String zippedSubmissionsFilename) {
        this.setResourcesPath(resourcesPath);
        this.zippedSubmissionsFilename = zippedSubmissionsFilename;
        this.submissions = new TreeMap<>();
        this.evaluator = new Evaluator();
        this.overallScore = 0.0;
    }


    // Getters

    public String getResourcesPath() {
        return this.resourcesPath;
    }

    public TreeMap<String, LinkedList<File>> getSubmissions() {
        return this.submissions;
    }


    // Setters

    /**
     * Sets the default path of the resources directory
     */
    public void setResourcesPath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = System.getProperty("user.dir");
        path += (osName.contains("windows")) ? "\\src\\main\\resources\\" : "/src/main/resources/";
        this.resourcesPath = path;
    }

    /**
     * Sets the path of the resources directory to the supplied path
     * @param path Absolute path to the resources directory
     */
    public void setResourcesPath(String path) {
        if (!path.endsWith(File.separator)) path += File.separator;
        this.resourcesPath = path;
    }


    // Facade Methods

    @Override
    public void evaluateSubmissions() {
        this.submissionDecompressor = new SubmissionDecompressor(this.resourcesPath, this.zippedSubmissionsFilename);
        System.out.println("Evaluating submissions...");
        System.out.println("Unzipping submission files... (" + this.zippedSubmissionsFilename + ")");

        // Safely decompress submissions, else hard exit
        try { this.submissions = this.submissionDecompressor.decompress(); }
        catch (Exception e) { 
            e.printStackTrace();
            System.exit(1);
        }
        
        // Run evaluation on submissions using Evaluator
        for (int i = 0; i < this.submissions.size(); i++) {
            String submissionName = this.submissions.keySet().toArray()[i].toString();
            System.out.print("Evaluating " + (i + 1) + " of " + this.submissions.size() + " (");
            System.out.println(submissionName + ")");
            this.overallScore += this.evaluator.evaluate(this.submissions.get(submissionName));
        }

        System.out.println("Evaluation complete.");
    }


    @Override
    public void generatePDFReport() {
        System.out.println("Generating PDF report...");
        System.out.println("Report generated to ");
    }


    @Override
    public void displayEvaluationResults() {
        System.out.println("\n<--- Evaluation Breakdown --->");
        System.out.println("Overall score: " + this.overallScore);
        System.out.println("\n\n<--- Test Breakdown --->");
    }
}
