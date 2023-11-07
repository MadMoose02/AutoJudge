package com.team4;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.LinkedList;
import java.util.TreeMap;

public class SubmissionDecompressorTest {

    String resourcesPath = new String();

    public void setResourcesPath() {
        // String osName = System.getProperty("os.name").toLowerCase();
        // String path = System.getProperty("user.dir");
        // path += (osName.contains("windows")) ? "\\src\\test\\resources\\" : "/src/test/resources/";
        String path = "C:\\Users\\kesha\\OneDrive\\Desktop\\";
        this.resourcesPath = path;
    }
    
    @Test
    public void testFileExtraction() {
        System.out.println("Testing File Extraction...");

        this.setResourcesPath();
        String zipFilename = "Submissions.zip";
        SubmissionDecompressor sd = new SubmissionDecompressor();
        TreeMap<String, File> tm = new TreeMap<>();

        try {
            sd.decompress(new String(this.resourcesPath + zipFilename), "", tm);
        } catch (Exception e) { e.printStackTrace(); }

        System.out.println("\nExtracted " + tm.size() + " submissions.");
        assertEquals(3, tm.size(), 0);
    }
}
