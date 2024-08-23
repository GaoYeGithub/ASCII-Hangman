import java.io.Console;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the ASCII Version of Hangman!");

        Console c = System.console();
        Scanner s = new Scanner(System.in);
        char[] letters;

        String prompt = "Please enter a secret word: ";
        if(c != null) {
            letters = c.readPassword(prompt);
            for(int i=0; i<letters.length; i++) {
                letters[i] = Character.toUpperCase(letters[i]);
            }
        } else {
            System.out.println("For best results, please run this from the command line.");
            System.out.print(prompt);
            letters = s.nextLine().trim().toUpperCase().toCharArray();
            for(int i=0; i<10000; i++) System.out.println();
        }

        Gallows g = new Gallows();
        boolean containsGuess = false;
        boolean victory = true;
        char[] puzzle = new char[letters.length];
        int counter = 0;
        int gamesPlayed = 1;
        boolean gameDone = false;

        for(int i = 0; i < puzzle.length; i++) {
            puzzle[i] = '_';
        }

        System.out.println("");
        System.out.print(g.toString());

        while (victory) {
            if (gameDone) {
                gamesPlayed++;
                prompt = "Please enter a secret word: ";
                if(c != null) {
                    letters = c.readPassword(prompt);
                    for(int i=0; i<letters.length; i++) {
                        letters[i] = Character.toUpperCase(letters[i]);
                    }
                } else {
                    System.out.println("For best results, please run this from the command line.");
                    System.out.print(prompt);
                    letters = s.nextLine().trim().toUpperCase().toCharArray();
                    for(int i=0; i<10000; i++) System.out.println();
                }

                puzzle = new char[letters.length];
                for(int i = 0; i < puzzle.length; i++) {
                    puzzle[i] = '_';
                }
                g = new Gallows();
                System.out.println(g.toString());
            }
            gameDone = false;
            System.out.print("Puzzle to solve: ");
            for (int i = 0; i < puzzle.length; i++) {
                System.out.print(puzzle[i] + " ");
            }
            System.out.println("");
            System.out.print("Please guess a letter: ");
            char guess = s.nextLine().toUpperCase().charAt(0);

            System.out.println(guess);

            containsGuess = false;
            for (int i = 0; i < letters.length; i++) {
                if (letters[i] == guess) {
                    containsGuess = true;
                    for (int j = 0; j < letters.length; j++) {
                        if (letters[j] == guess) {
                            puzzle[j] = guess;
                        }
                    }
                    break;
                }
            }
            if (!containsGuess) {
                System.out.println("");
                g.hang();
                System.out.print(g.toString());
                System.out.println(g.getClue());
            }

            counter = 0;
            for (int i = 0; i < puzzle.length; i++) {
                if(puzzle[i] != '_') {
                    counter++;
                }
                if (counter == puzzle.length) {
                    victory = true;
                    if (gamesPlayed % 2 == 0) {
                        System.out.println("Player 2 got it. Player 1, you turn next!");
                    } else {
                        System.out.println("Player 1 got it. Player 2, your turn next!");
                    }
                    gameDone = true;
                }
            }
            if (!g.isAlive()) {
                victory = false;
                break;
            }
        }

        if (gamesPlayed % 2 == 1) {
            System.out.println("Player 1 wins in " + gamesPlayed + " games!");
        } else {
            System.out.println("Player 2 wins in " + gamesPlayed + " games!");
        }
    }
}