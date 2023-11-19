package com.team4;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

/**
 * A class for generating a PDF report from feedback comments for a student's submission.
 * The report is generated using the Apache PDFBox library.
 */
public class ReportGenerator {
    
    // Attributes

    /** The filename of the report */
    private String reportFilename;

    /** The absolute path to the report */
    private String reportPath;

    /** The name of the student whose report is being generated */
    private String studentName;

    /** The ID of the student whose report is being generated */
    private String studentID;

    /** The PDDocument object that represents the report */
    private PDDocument reportDocument;

    /** The input stream to which the report is being written */
    private PDPageContentStream contentStream;

    /**
     * Default constructor for a ReportGenerator object
     * @param reportDirectory The directory in which the report is to be saved
     * @param studentName     The name of the student
     * @param studentID       The ID of the student
     */
    public ReportGenerator(String reportDirectory, String studentName, String studentID) {
        this.reportFilename = new String(
            studentName.replace(" ", "_") + "_" + studentID + "_EvalReport" + ".pdf");
        this.reportPath = reportDirectory + File.separator + reportFilename;
        this.reportDocument = new PDDocument();
        this.studentName = studentName;
        this.studentID = studentID;

        File testFile = new File(reportPath);
        if (testFile.exists()) testFile.delete();
    }

    /**
     * Adds a report header to the given page with the provided student name and ID
     * @param  page         PDPage object to add the header to
     * @param  studentName  Name of the student
     * @param  studentID    ID of the student
     * @throws IOException  If there is an error while adding the header
     */
    public void addReportHeader(PDPage page, String studentName, String studentID) throws IOException {
        this.contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 14);
        this.contentStream.beginText();
        this.contentStream.newLineAtOffset(25, 725);
        this.contentStream.showText(studentName + " (" + studentID + ")");
    }

    /**
     * Adds an entry to the report using the given feedback comments
     * @param  feedbackComments The feedback comments to be added to the report
     * @throws IOException      Tf an I/O error occurs while adding the entry to the report
     */
    public void addEntryToReport(String feedbackComments) throws IOException {
        PDPage page = new PDPage();
        this.reportDocument.addPage(page);
        this.contentStream = new PDPageContentStream(this.reportDocument, page);
        this.addReportHeader(page, this.studentName, this.studentID);
        this.contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 10);
        this.contentStream.newLineAtOffset(0, -25);
        String[] lines = feedbackComments.split("\n");
        for (String line : lines) {
            this.contentStream.showText(line);
            this.contentStream.newLineAtOffset(0, -15);
        }
    }
    
    /**
     * Generates a report by closing the content stream, saving the report document,
     * and closing the report document.
     * @throws IOException If an I/O error occurs while saving the report document
     */
    public void generateReport() throws IOException {
        this.contentStream.endText();
        this.contentStream.close();
        this.reportDocument.save(this.reportPath);
        System.out.println("PDF saved to: " + this.reportPath);
        this.reportDocument.close();
    }
}
