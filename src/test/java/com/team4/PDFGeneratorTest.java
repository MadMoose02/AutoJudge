package com.team4;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.Standard14Fonts;


public class PDFGeneratorTest {
    
    @Test
    public void testGeneratePDF() throws IOException {
        String testFilePath = System.getProperty("user.dir") + File.separator 
            + "src" + File.separator + "test" + File.separator + "resources" 
            + File.separator + "TestReport.pdf";
        PDDocument doc = new PDDocument();
        PDPage page = new PDPage();
        doc.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(doc, page);

        contentStream.setFont(new PDType1Font(Standard14Fonts.FontName.COURIER), 12);

        try {
            contentStream.beginText();
            contentStream.newLineAtOffset(25, 725);
            contentStream.showText("AutoJudge Sample Report");
            contentStream.newLineAtOffset(0, -25);
            contentStream.showText("This is a sample report generation");
            contentStream.endText();
            contentStream.close();
        } catch (Exception e) {
            System.out.println("Unable to write to PDF");
            e.printStackTrace();
        }

        // Save the document
        doc.save(testFilePath);
        doc.close();
    }
}
