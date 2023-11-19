package com.team4;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * A class for generating ASCII art from text
 */
public class ASCIIGenerator {

    // Attributes

    /** The text to be converted to ASCII art */
    private String text;

    /** The character to be used to render the ASCII art */
    private String artChar;

    /** The width of the ASCII art */
    private int width;

    /** The height of the ASCII art */
    private int height;
    
    /**
     * Default constructor for ASCIIGenerator
     * @param text The text to be converted to ASCII art
     */
    ASCIIGenerator(String text) {
        this.text = text;
        this.artChar = "#";
        this.width = 70;
        this.height = 20;
    }

    /**
     * Constructor for ASCIIGenerator with width and height
     * @param text   The text to be converted to ASCII art
     * @param width  The width of the ASCII art
     * @param height The height of the ASCII art
     */
    ASCIIGenerator(String text, int width, int height) {
        this.text = text;
        this.artChar = "#";
        this.width = width;
        this.height = height;
    }

    /**
     * Constructor for ASCIIGenerator with specified artChar and dimensions
     * @param text    The text to be converted to ASCII art
     * @param artChar The character to be used to render the ASCII art
     * @param width   The width of the ASCII art
     * @param height  The height of the ASCII art
     */
    ASCIIGenerator(String text, String artChar, int width, int height) {
        this.text = text;
        this.artChar = artChar;
        this.width = width;
        this.height = height;
    }


    // Getters

    public String getArtChar() { return this.artChar;}
    public int getWidth() { return this.width;}
    public int getHeight() { return this.height;}


    // Methods

    /**
     * Draws the ASCII art to the console
     */
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

    /**
     * Returns a BufferedImage object with an integer color model
     * @return  The created BufferedImage object
     */
    private BufferedImage getImageIntegerMode() {
        return new BufferedImage(this.width, this.height, BufferedImage.TYPE_INT_RGB);
    }

    /**
     * Returns a Graphics2D object based on the given Graphics object.
     * @param  graphics The Graphics object to convert
     * @return The converted Graphics2D object
     */
    private Graphics2D getGraphics2D(Graphics graphics) {
        graphics.setFont(new Font(Font.SANS_SERIF, Font.PLAIN, 12));

        Graphics2D graphics2D = (Graphics2D) graphics;
        graphics2D.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);

        return graphics2D;
    }
}
