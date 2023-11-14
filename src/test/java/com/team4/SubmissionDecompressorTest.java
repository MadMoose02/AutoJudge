package com.team4;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.TreeMap;

public class SubmissionDecompressorTest {

    String resourcesPath = new String();

    public void setResourcesPath() {
        // String osName = System.getProperty("os.name").toLowerCase();
        // String path = System.getProperty("user.dir");
        // path += (osName.contains("windows")) ? "\\src\\test\\resources\\" : "/src/test/resources/";
        String path = "C:\\Users\\kesha\\OneDrive\\Desktop";
        this.resourcesPath = path;
    }
    
    @Test
    public void testExtractSubmissionFolder() {
        System.out.println("\nTesting Submissions folder extraction...");

        this.setResourcesPath();
        String zipFilename = "Submissions.zip";
        SubmissionDecompressor sd = new SubmissionDecompressor(this.resourcesPath + File.separator + zipFilename);
        TreeMap<String, File> submissions = new TreeMap<>();
        try { submissions = sd.decompress(); } catch (Exception e) { e.printStackTrace(); }
        System.out.println("\nExtracted " + submissions.size() + " submissions.");
        assertEquals(3, submissions.size(), 0);
    }

    @Test
    public void testExtractSingleSubmission() {
        System.out.println("\nTesting single submission extraction...");

        this.setResourcesPath();
        String zipFilename = "Submissions.zip";
        SubmissionDecompressor sd = new SubmissionDecompressor(this.resourcesPath + File.separator + zipFilename);
        TreeMap<String, File> submissions = new TreeMap<>();
        try { submissions = sd.decompress(); } catch (Exception e) { e.printStackTrace(); }

        // Use first submission for test case
        String submissionName = submissions.firstKey();
        String submissionPath = submissions.get(submissionName).getAbsolutePath();
        sd = new SubmissionDecompressor(submissionPath);
        TreeMap<String, File> submission = new TreeMap<>();
        try { submission = sd.decompress(); } catch (Exception e) { e.printStackTrace(); }
        
        System.out.println("\nExtracted " + submission.size() + " files from " + submissionName);
    }
}
