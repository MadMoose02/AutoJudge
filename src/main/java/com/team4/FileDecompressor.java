package com.team4;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class FileDecompressor {

    // Attributes
    private static final int BUFFER_SIZE = 4096;
    private String resourcesPath;
    private String compressedFilePath;
    private String decompressedFilePath;

    /**
    * Default constructor - two arguments
    * @param resourcesPath Absolute path to the resources directory
    * @param compressedFilePath Absolute path to the compressed file
    */
    public FileDecompressor(String resourcesPath, 
                            String compressedFilename) {
        this.resourcesPath = resourcesPath + ((resourcesPath.endsWith(File.separator)) ? "" : File.separator);
        this.compressedFilePath = this.resourcesPath + compressedFilename;
        this.decompressedFilePath = this.resourcesPath + compressedFilename.split(".zip")[0];
    }

    /**
    * Overload constructor - three arguments
    * @param resourcesPath Absolute path to the resources directory
    * @param compressedFilePath Absolute path to the compressed file
    * @param decompressedFilePath Absolute path to the decompressed file
    */
    public FileDecompressor(String resourcesPath, 
                            String compressedFilename, 
                            String decompressedFilename) {
        this.resourcesPath = resourcesPath + ((resourcesPath.endsWith(File.separator)) ? "" : File.separator);
        this.compressedFilePath = this.resourcesPath + compressedFilename;
        this.decompressedFilePath = this.resourcesPath + decompressedFilename;
    }

    
    // Methods

    /**
     * Extracts a zip entry (file entry)
     * @param zipIn
     * @param filePath
     * @throws IOException
     */
    private void extractFile(ZipInputStream zipIn, String filePath) throws IOException {
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(filePath));
        byte[] bytesIn = new byte[BUFFER_SIZE];
        int read = 0;
        while ((read = zipIn.read(bytesIn)) != -1) {
            bos.write(bytesIn, 0, read);
        }
        bos.close();
    }

    /**
     * Extracts the zip file specified in compressedFilePath into decompressedFilePath
     * Referenced from https://www.codejava.net/java-se/file-io/programmatically-extract-a-zip-file-using-java
     * @throws IOException
     */
    public ArrayList<File> decompress() throws IOException {
        ArrayList<File> extractedFiles = new ArrayList<File>();
        File destDir = new File(this.decompressedFilePath);
        ZipInputStream zipIn = new ZipInputStream(new FileInputStream(this.compressedFilePath));
        ZipEntry entry = zipIn.getNextEntry();

        // Create output directory of extrated files if it doesn't exist
        if (!destDir.exists()) destDir.mkdir();

        // Iterate over sub-directory zip files in the main zip file
        while (entry != null) {
            System.out.println(entry.getName());
            entry = zipIn.getNextEntry();
        }


        // while (entry != null) {
        //     String filePath = this.decompressedFilePath + File.separator + entry.getName();
        //     if (!entry.isDirectory()) {
        //         // If entry is a file, extract it
        //         this.extractFile(zipIn, filePath);
        //         extractedFiles.add(new File(filePath));
        //     } else {
        //         // If entry is a directory, create the directory
        //         File dir = new File(filePath);
        //         dir.mkdir();
        //     }
        //     zipIn.closeEntry();
        //     entry = zipIn.getNextEntry();
        // }
        
        return extractedFiles;
    }

}
