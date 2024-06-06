package v2hangman;

import acm.program.*;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Hangman extends ConsoleProgram {


    public void run() {
        // TODO: write this method
        int wins = 0;
        int games = 1;
        int bestGame = 0;
        while (true) {
            intro();
            String word = getRandomWord("assets/dict.txt");
            //println(word);
            int temp = playOneGame(word);
            if (temp > bestGame) {
                bestGame = temp;
            }
            if (temp > 0) {
                wins++;
            }
            char opt = readLine("\n Do you want to play another game? (press y or n) ").toUpperCase().charAt(0);
            if (opt == 'Y') {
                games++;
            } else {
                stats(games, wins, bestGame);
                break;
            }
        }
    }

    // TODO: comment this method
    private void intro() {
        String msg =
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "Welcome to v2hangman.Hangman!\n" +
                "I will think of a random word.\n" +
                "You'll try to guess its letters.\n" +
                "Every time you guess a letter\n" +
                "that isn't in my word, a new body\n" +
                "part of the hanging man appears.\n" +
                "Good luck!\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n";
        println(msg);
    }


    // TODO: comment this method
    private int playOneGame(String secretWord) {

        /*
        Display a “hint” about the secret word on each turn; initially a string of dashes
        Ask the user to type a valid guess
        Figure out if a guess is correct (in secret word) or incorrect (not in secret word)
        Keep track of the number of guesses remaining
        Determine when the game has ended (when the user guesses all letters in the secret word or runs out of guesses)
        */

        secretWord.toUpperCase();
        String hint = "";
        String guess = "";
        boolean winCon = false;
        int guessCntr = 8;

        while (guessCntr > 0){

            displayHangman(guessCntr);
            hint = createHint(secretWord, guess);
            println("Secret Word: " + hint);
            println("Your Guesses: " + guess);
            println("Guesses left: " + guessCntr);
            char single = readGuess(guess);

            if(secretWord.indexOf(single) > -1){
                println("Correct!");
                guess += single;
                hint = createHint(secretWord, guess);
                if (hint.equalsIgnoreCase(secretWord)){
                    winCon = true;
                    break;
                }
            }
            else{
                println("Incorrect.");
                guess += single;
                guessCntr--;
            }

        }
        if (winCon) {
            println("You won! My secret message is: \"" + secretWord.toUpperCase() + "\"");
        } else {
            displayHangman(guessCntr);
            println("You lost! My secret message is: \"" + secretWord.toUpperCase() + "\"");
        }
        return guessCntr;
    }


    // TODO: comment this method
    private String createHint(String secretWord, String guessedLetters) {
        // TODO: write this method
        String[] word = secretWord.split("");
        String hint = "";

        for (int x = 0; x < secretWord.length(); x++){
            if(guessedLetters.contains(word[x])){
                hint += word[x];
            }
            else{
                hint += "-";
            }
        }

        return hint;
    }

    // TODO: comment this method
    private char readGuess(String guessedLetters) {
        while (true) {
            String guessChar = readLine("Guess? (only reads 1 letter) ").toUpperCase();

            // Check if the input is a single letter
            if (guessChar.length() != 1 || !Character.isLetter(guessChar.charAt(0))) {
                System.out.println("Invalid input. Please enter a single letter.");
                continue;
            }

            // Check if the letter has already been guessed
            if (guessedLetters.contains(guessChar)) {
                System.out.println("This letter is guessed already: guess again");
            } else {
                return guessChar.toUpperCase().charAt(0);
            }
        }
    }

    // TODO: comment this method
    private void displayHangman(int guessCount) {
        // TODO: write this method
        String fileName = "assets/display" + guessCount + ".txt";

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String disp;
            while ((disp = br.readLine()) != null) {
                println(disp);
            }
        } catch (IOException e) {
            println("Error reading file: " + e.getMessage());
        }
    }

    private void stats(int gamesCount, int gamesWon, int best) {
        DecimalFormat decfor = new DecimalFormat("0.00");

        double winPerc = ((double) gamesWon /gamesCount) * 100.0;

        println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@\n" +
                "Overall statistics\n" +
                "Games played: " + gamesCount + "\n" +
                "Games won: " + gamesWon +"\n" +
                "Win percent: " + decfor.format(winPerc) + "%\n" +
                "Best game: " + best + " guess(es) remaining\n" +
                "@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
    }

    // TODO: comment this method
    private String getRandomWord(String filename) {
        List<String> words = new ArrayList<>();
        Random random = new Random();

        // Read the words from the file and store them in a list
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String word;
            while ((word = br.readLine()) != null) {
                words.add(word.trim());
            }
        } catch (IOException e) {
            System.err.println("Error reading file: " + e.getMessage());
            return ""; // Return an empty string in case of error
        }

        // Return a randomly selected word from the list
        if (!words.isEmpty()) {
            int randomIndex = random.nextInt(words.size());
            return words.get(randomIndex);
        } else {
            return ""; // Return an empty string if the list is empty
        }
    }


    public void init() {
        canvas = new HangmanCanvas();
        add(canvas);
        canvas.reset();  // sample canvas method call

    }


    /* Solves NoClassDefFoundError */
    public static void main(String[] args) {
        new Hangman().start(args);
    }


    // private v2hangman.HangmanCanvas canvas;
    private HangmanCanvas canvas;
}
