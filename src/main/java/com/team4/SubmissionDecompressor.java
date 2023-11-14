package com.team4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.TreeMap;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class SubmissionDecompressor {

    // Attributes
    private static final int BUFFER_SIZE = 4096;
    private static final int MAX_FILES = 30;
    private static final String OS_ARCH = System.getProperty("os.name").toLowerCase();
    private TreeMap<String, File> extractedFiles = new TreeMap<>();
    private String compressedFilePath;
    private String decompressedFilePath;


    /**
    * Default constructor - one argument
    *  @param compressedFilePath Absolute path to the compressed file
    */
    public SubmissionDecompressor(String compressedFilePath) {
        this.compressedFilePath = compressedFilePath;
        this.decompressedFilePath = compressedFilePath.substring(
            0, 
            compressedFilePath.lastIndexOf(File.separator)
        );
    }

    /**
    * Overload constructor - two arguments
    * @param compressedFilePath Absolute path to the compressed file
    * @param decompressedFilePath Absolute path to the location to extract files to
    */
    public SubmissionDecompressor(String compressedFilePath, String decompressedFilePath) {
        this.compressedFilePath = compressedFilePath;
        this.decompressedFilePath = decompressedFilePath;
    }


    // Methods
    
    private static String getOSCompliantPath(String directoryPath) {
        if (OS_ARCH.contains("windows")) {
            return directoryPath.replace('/', '\\');
        }
        return directoryPath.replace('\\', '/');
    }

    
    private static void extractFile(ZipInputStream zipInputStream, String filePath) throws IOException {
        try (BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int bytesRead;

            while ((bytesRead = zipInputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, bytesRead);
            }
        }
    }


    public TreeMap<String, File> decompress() throws IOException {
        int numIterations = 0;

        // Create the destination directory if it doesn't exist
        FileInputStream fin = new FileInputStream(this.compressedFilePath);
        String zipFileName = this.compressedFilePath.substring(
            this.compressedFilePath.lastIndexOf(File.separator) + 1, 
            this.compressedFilePath.length() - 4
        );
        File expandedDir = new File(this.decompressedFilePath + File.separator + zipFileName);
        if (!expandedDir.exists()) expandedDir.mkdirs();

        try (ZipInputStream zipInputStream = new ZipInputStream(fin)) {

            // Get the zipped file list entry
            ZipEntry entry = zipInputStream.getNextEntry();

            // Loop through the zipped file list
            while (entry != null) {
                if (numIterations++ > MAX_FILES) {
                    throw new IOException("Too many files in zip file");
                }
                String entryName = getOSCompliantPath(entry.getName());
                String filePath = this.decompressedFilePath + File.separator + entryName;

                if (!entry.isDirectory()) {
                    extractFile(zipInputStream, filePath);
                } else {
                    File dir = new File(filePath);
                    dir.mkdirs();
                }
                
                zipInputStream.closeEntry();
                this.extractedFiles.put(entryName, new File(filePath));
                entry = zipInputStream.getNextEntry();
            }
        }
        fin.close();

        return this.extractedFiles;
    }
}