package com.team4;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ASCIIGenerator {

    private String text;
    private String artChar;
    private int width;
    private int height;
    
    ASCIIGenerator(String text) {
        this.text = text;
        this.artChar = "#";
        this.width = 70;
        this.height = 20;
    }

    ASCIIGenerator(String text, int width, int height) {
        this.text = text;
        this.artChar = "#";
        this.width = width;
        this.height = height;
    }

    ASCIIGenerator(String text, String artChar, int width, int height) {
        this.text = text;
        this.artChar = artChar;
        this.width = width;
        this.height = height;
    }

    public String getArtChar() {
        return this.artChar;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight() {
        return this.height;
    }

    public void drawString() {
        BufferedImage image = getImageIntegerMode();

        Graphics2D graphics2D = getGraphics2D(image.getGraphics());
        graphics2D.drawString(this.text, 6, ((int) (this.height * 0.67)));

        for (int y = 0; y < this.height; y++) {
            StringBuilder stringBuilder = new StringBuilder();

            for (int x = 0; x < this.width; x++) {
                stringBuilder.append(image.getRGB(x, y) == -16777216 ? " " : this.artChar);
            }

            if (stringBuilder.toString()
                .trim()
                .isEmpty()) {
                continue;
            }

            System.out.println(stringBuilder);
        }
    }

    private BufferedImage getImageIntegerMode() {
        return new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    private Graphics2D getGraphics2D(Graphics graphics) {
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }
}
