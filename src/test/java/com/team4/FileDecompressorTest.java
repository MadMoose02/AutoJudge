package com.team4;

import org.junit.Test;
import static org.junit.Assert.*;

import java.io.File;
import java.util.ArrayList;

public class FileDecompressorTest {

    String resourcesPath = new String();

    public void setResourcesPath() {
        String osName = System.getProperty("os.name").toLowerCase();
        String path = System.getProperty("user.dir");
        path += (osName.contains("windows")) ? "\\src\\test\\resources\\" : "/src/test/resources/";
        this.resourcesPath = path;
    }
    
    @Test
    public void testFileExtraction() {
        this.setResourcesPath();
        String zipFilename = "Submissions.zip";
        ArrayList<File> extractedSubmissions = new ArrayList<File>();
        FileDecompressor fileDecompressor = new FileDecompressor(this.resourcesPath, zipFilename);

        System.out.println("Testing File Extraction...");
        System.out.println("File path: " + this.resourcesPath + zipFilename);
        
        try {
            extractedSubmissions = fileDecompressor.decompress();
        } catch (Exception e) { e.printStackTrace(); }
        System.out.println("Extracted " + extractedSubmissions.size() + " submissions.");
        assertEquals(3, extractedSubmissions.size());
    }
}
