package com.team4;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.HashMap;
import java.util.TreeMap;

public class SubmissionDecompressorTest {

    String resourcesPath = new String();
    HashMap<String, TreeMap<String, File>> submissions = new HashMap<>();

    public void setResourcesPath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = System.getProperty("user.dir");
        path += (osName.contains("windows")) ? "\\src\\test\\resources" : "/src/test/resources";
        this.resourcesPath = path;
    }

    @Test
    public void testExtractAllSubmissions() {
        System.out.println("\nTesting all submissions extraction...");

        this.setResourcesPath();
        String zipFilename = "Submissions.zip";
        File testFolder = new File(this.resourcesPath + File.separator + zipFilename.split(".zip")[0]);
        if (testFolder.exists()) {
            for (File file : testFolder.listFiles()) {
                file.delete();
            }
            testFolder.delete();
        }

        SubmissionDecompressor sd = new SubmissionDecompressor(this.resourcesPath + File.separator + zipFilename);
        TreeMap<String, File> submissions = new TreeMap<>();
        try { submissions = sd.decompress(); } catch (Exception e) { e.printStackTrace(); }

        for (String submissionName : submissions.keySet()) {
            String submissionPath = submissions.get(submissionName).getAbsolutePath();
            sd = new SubmissionDecompressor(submissionPath);
            TreeMap<String, File> submission = new TreeMap<>();
            try { submission = sd.decompress(); } catch (Exception e) { e.printStackTrace(); }
            this.submissions.put(submissionName, submission);
            System.out.println("Extracted " + submission.size() + " files from " + submissionName);
        }

        assertEquals(5, this.submissions.size());
        for (String submissionName : this.submissions.keySet()) {
            assertEquals(20, this.submissions.get(submissionName).size(), 15);
        }
    }
}
