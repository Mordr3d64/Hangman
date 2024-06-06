package v3hangman;
/*
 * File: HangmanCanvas.java
 * ---------------------
 * This class holds the graphics elements to the Hangman game.
 * Author: Cobalt - M.Cabatuan
 * Date modified: 06/11/2019
 */


import acm.graphics.GCanvas;
import acm.graphics.GImage;
import acm.graphics.GLabel;

import java.io.File;

public class V3HangmanCanvas extends GCanvas {

    private static final int TEXT_HEIGHT = 15;   // you can modify this to suit your ascii art
    private static final int TEXT_X_OFFSET = 1;   // you can modify this to suit your ascii art
    private int textX = 0;
    private int textY;


    /**
     * Resets the display so that only the hangman scaffold appears
     */
    public void reset() {
        // Sample graphics object
        GLabel testMessage = new GLabel("Hello LBYCPEI!");
        textX = TEXT_X_OFFSET;
        textY = TEXT_HEIGHT;
        add(testMessage,  textX , textY);

        GLabel nextMessage = new GLabel("This is the next line!");
        textY += TEXT_HEIGHT;
        add(nextMessage,  textX , textY );

        printText("Custom println()");

        printText("Custom println()");
    }


    public void printText(String text){
        GLabel line = new GLabel(text);
        line.setFont("Monospaced-plain-12");
        textY += TEXT_HEIGHT;
        add(line,  0 , textY );

    }

    public void println(String data){
        printText(data);
    }
    public void dispImg(File src) {
        GImage img = new GImage(String.valueOf(src));
        img.setBounds(0,0,400,500);
        add(img);
    }
    public void clear() {
        textX = 0;
        textY = 0;
        removeAll();
    }

    /* Write your methods here */
}