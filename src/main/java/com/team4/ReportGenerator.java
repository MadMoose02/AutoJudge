package com.team4;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class ReportGenerator {
    
    // Attributes
    private String reportFilename;
    private String reportPath;
    private String studentName;
    private String studentID;
    private PDDocument reportDocument;
    private PDPageContentStream contentStream;

    /**
     * Default constructor
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

    public void addReportHeader(PDPage page, String studentName, String studentID) throws IOException {
        this.contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 14);
        this.contentStream.beginText();
        this.contentStream.newLineAtOffset(25, 725);
        this.contentStream.showText(studentName + " (" + studentID + ")");
    }

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
    
    public void generateReport() throws IOException {
        this.contentStream.endText();
        this.contentStream.close();
        this.reportDocument.save(this.reportPath);
        System.out.println("PDF saved to: " + this.reportPath);
        this.reportDocument.close();
    }
}
