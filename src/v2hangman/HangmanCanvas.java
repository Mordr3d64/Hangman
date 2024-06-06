package v2hangman;/*
 * File: v2hangman.HangmanCanvas.java
 * ---------------------
 * This class holds the graphics elements to the v2hangman.Hangman game.
 * Author: Cobalt - M.Cabatuan
 * Date modified: 06/11/2019
 */


import acm.graphics.GCanvas;
import acm.graphics.GLabel;

public class HangmanCanvas extends GCanvas {

    private static final int TEXT_HEIGHT = 20;   // you can modify this to suit your ascii art
    private static final int TEXT_X_OFFSET = 12;   // you can modify this to suit your ascii art
    private int textX;
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

        GLabel nextMessage = new GLabel("How does this work oml");
        textY += TEXT_HEIGHT;
        add(nextMessage,  textX , textY );

    }

    public void printText(String text){
        GLabel line = new GLabel(text);
        textY += TEXT_HEIGHT;
        add(line,  textX , textY );
    }

    /* Write your methods here */

}
