package com.team4;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

import com.team4.Evaluator.Evaluator;

public class AutoJudgeSystem implements AutoJudge {

    // Attributes
    private String resourcesPath;
    private String zippedSubmissionsFilename;
    private String reportDirectory;
    private ReportGenerator reportGenerator;
    private HashMap<String, TreeMap<String, File>> submissions;
    private TreeMap<String, String> submissionFeedback;
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
        this.reportDirectory = this.resourcesPath + zippedSubmissionsFilename.substring(0, zippedSubmissionsFilename.indexOf(".")) + "_reports";
        this.submissionDecompressor = new SubmissionDecompressor(
            this.resourcesPath + File.separator + this.zippedSubmissionsFilename
        );
        this.submissions = new HashMap<>();
        this.submissionFeedback = new TreeMap<>();
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
        this.submissionDecompressor = new SubmissionDecompressor(
            this.resourcesPath + File.separator + this.zippedSubmissionsFilename
        );
        this.submissions = new HashMap<>();
        this.submissionFeedback = new TreeMap<>();
        this.overallScore = 0.0;
    }


    // Getters

    public String getResourcesPath() {
        return this.resourcesPath;
    }

    public HashMap<String, TreeMap<String, File>> getSubmissions() {
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


    // Methods

    private final void displayLaunchMessage() {
        ASCIIGenerator gen = new ASCIIGenerator("AutoJudge");
        gen.drawString();
        System.out.print("\n   v " + this.getClass().getPackage().getImplementationVersion());
        System.out.println(" by " + this.getClass().getPackage().getImplementationVendor());
        System.out.println(new String("   " + "=".repeat(gen.getWidth() - 6)));
        System.out.println();
    }

    private final void extractSubmissions() {
        TreeMap<String, File> submissionsFolder = new TreeMap<>();
        try { submissionsFolder = this.submissionDecompressor.decompress(); }
        catch (Exception e) { 
            System.out.println("\nUnable to decompress submissions zip folder: " + this.zippedSubmissionsFilename + "\n");
            System.exit(1);
        }

        // Put submission entries into submissions HashMap
        for (String submission : submissionsFolder.keySet()) {
            this.submissionDecompressor = new SubmissionDecompressor(
                this.resourcesPath + File.separator + submission
            );
            try { this.submissions.put(submission, this.submissionDecompressor.decompress()); }
            catch (Exception e) { 
                System.out.println("Unable to decompress submission: " + submission + "\n");
            }
        }

        System.out.println("Done. Found " + this.submissions.size() + " submissions\n");
    }

    private final void removeExtractedZipFiles() {
        for (String submission : this.submissions.keySet()) {
            File submissionFile = new File(this.resourcesPath + File.separator + submission);
            submissionFile.delete();
        }
    }

    @Override
    public void evaluateSubmissions() {
        displayLaunchMessage();
        System.out.println("<-- Submission Evaluation -->");
        System.out.println("Extracting submissions... (" + this.zippedSubmissionsFilename + ")");

        // Safely decompress submissions zip folder, else hard exit
        this.extractSubmissions();
        this.removeExtractedZipFiles();
        
        // Run evaluation on submissions using Evaluator
        for (String submissionName : this.submissions.keySet()) {
            TreeMap<String, File> submission = this.submissions.get(submissionName);
            System.out.println("=".repeat(80));
            System.out.println(" Evaluating: " + submissionName + " (" + submission.size() + " files)");
            System.out.println("=".repeat(80));
            
            // Run evaluation on each submission file
            StringBuilder submissionFeedbackComments = new StringBuilder();
            for (String submissionFilename : submission.keySet()) {
                this.evaluator = new Evaluator();
                File submissionFile = submission.get(submissionFilename);
                System.out.println("\n> Evaluating file: " + submissionFilename);
                double score = this.evaluator.evaluate(submissionFile);
                submissionFeedbackComments.append(
                    "File: " + submissionFilename + "\n" +
                    this.evaluator.getFeedbackComments() + "\n"
                );
                if (!submissionFilename.equals(submission.lastKey())) {
                    submissionFeedbackComments.append("-".repeat(80) + "\n");
                }
                this.overallScore += score;
            }
            this.submissionFeedback.put(submissionName, submissionFeedbackComments.toString());
            System.out.println("=".repeat(80) + "\n");    
        }
        System.out.println("Evaluation complete.");
    }


    @Override
    public void generatePDFReport() {
        File directory = new File(this.reportDirectory);
        if (!directory.exists()) directory.mkdir();
        for (String submissionName : this.submissionFeedback.keySet()) {
            int offset = this.zippedSubmissionsFilename.length() - 3;
            String studentName = submissionName.substring(offset, submissionName.length() - (offset + 5));
            String studentID = submissionName.split(studentName)[1].substring(1, 10);
            System.out.println("Generating report for: " + studentName + " (" + studentID + ")");
            this.reportGenerator = new ReportGenerator(this.reportDirectory, studentName, studentID);
            try {
                this.reportGenerator.addEntryToReport(this.submissionFeedback.get(submissionName));
                this.reportGenerator.generateReport();
            }
            catch (Exception e) {
                System.out.println("Unable to generate report for: " + studentName + " (" + studentID + ")");
                e.printStackTrace();
            }
        }
    }


    @Override
    public void displayEvaluationResults() {
        System.out.println("\n\n<--- Evaluation Breakdown --->");
        for (String submissionName : this.submissionFeedback.keySet()) {
            System.out.println("=".repeat(80));
            System.out.println(" Submission: " + submissionName);
            System.out.println("=".repeat(80));
            System.out.println(this.submissionFeedback.get(submissionName));
        }
        System.out.println("Overall score: " + this.overallScore + "%");
    }
}
