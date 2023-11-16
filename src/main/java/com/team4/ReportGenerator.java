package com.team4;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;

public class ReportGenerator {

    public void generateReport(double overallScore) throws IOException {
        // Set the file path
        String filePath = "C:\\Users\\josiah tenia\\Desktop\\AutoJudge\\src\\test\\resources\\generatedTest.pdf";

        // Check and delete an existing file
        File testFile = new File(filePath);
        if (testFile.exists()) {
            testFile.delete();
        }

        // Create the PDF document
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        // Create the content stream
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        // Set the font and other styling
        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.TIMES_BOLD), 18);

        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 725);
            contentStream.showText("AutoJudge Sample Report");
            contentStream.newLineAtOffset(0, -25);

            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);
            contentStream.showText("This is a sample report generation");
            contentStream.newLineAtOffset(0, -25);

            // Add a section for overall score
            contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER_BOLD), 14);
            contentStream.newLineAtOffset(0, -25);
            contentStream.showText("Overall Score: " + overallScore + "%");

            // Your other report content...

            contentStream.endText();
            contentStream.close();
        } catch (Exception e) {
            System.out.println("Unable to write to PDF");
            e.printStackTrace();
        }

        // Save the document
        doc.save(filePath);
        System.out.println("PDF saved to: " + filePath);
        doc.close();
    }
}
