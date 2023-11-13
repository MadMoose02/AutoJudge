package com.team4;

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
    private String resourcesPath;


    // /**
    // * Default constructor - two arguments
    // * @param resourcesPath Absolute path to the resources directory
    // * @param compressedFilePath Absolute path to the compressed file
    // */
    // public SubmissionDecompressor(String resourcesPath, String compressedFilename) {
    //     this.resourcesPath = resourcesPath + ((resourcesPath.endsWith(File.separator)) ? "" : File.separator);
    //     this.compressedFilePath = this.resourcesPath + compressedFilename;
    //     this.decompressedFilePath = this.resourcesPath + compressedFilename.split(".zip")[0];
    // }

    // /**
    // * Overload constructor - three arguments
    // * @param resourcesPath Absolute path to the resources directory
    // * @param compressedFilePath Absolute path to the compressed file
    // * @param decompressedFilePath Absolute path to the decompressed file
    // */
    // public SubmissionDecompressor(String resourcesPath, 
    //                         String compressedFilename, 
    //                         String decompressedFilename) {
    //     this.resourcesPath = resourcesPath + ((resourcesPath.endsWith(File.separator)) ? "" : File.separator);
    //     this.compressedFilePath = this.resourcesPath + compressedFilename;
    //     this.decompressedFilePath = this.resourcesPath + decompressedFilename;
    // }


    // Methods

    public void decompress(String filePath, String parentPath, TreeMap<String, File> tm) throws Exception {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(filePath))) {
            ZipEntry entry;
            while ((entry = zipInputStream.getNextEntry()) != null) {
                if (!entry.isDirectory()) {
                    String name = entry.getName().split("/")[1];
                    String fullPath = parentPath + File.separator + name;
                    File extractedFile = new File(fullPath);
                    FileOutputStream fileOutputStream = new FileOutputStream(extractedFile);
                    byte[] buffer = new byte[BUFFER_SIZE];
                    int length;
                    while ((length = zipInputStream.read(buffer)) > 0) {
                        fileOutputStream.write(buffer, 0, length);
                    }
                    fileOutputStream.close();
                    if (name.endsWith(".java")) tm.put(fullPath, extractedFile);
                    if (name.toLowerCase().endsWith(".zip")) {
                        decompress(fullPath, extractedFile.getParent(), tm);
                    }
                }
                zipInputStream.closeEntry();
            }
        } catch (IOException e) { 
            throw new Exception("Error decompressing file: " + filePath);
        }
        return;
    }
}