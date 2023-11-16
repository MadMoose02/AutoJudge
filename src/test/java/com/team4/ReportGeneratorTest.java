package com.team4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;


public class ReportGeneratorTest {
    
    @Test
    public void testGeneratePDF() throws IOException {
        String testFilePath = System.getProperty("user.dir") + File.separator 
        + "src" + File.separator + "test" + File.separator + "resources";

        ReportGenerator gen = new ReportGenerator(
            testFilePath,
            "John Doe", 
            "123456789"
        );
        gen.addEntryToReport("This is a test feedback entry", 100.0);
        gen.generateReport();
    }
}
