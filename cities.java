import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class cities {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner keyboard = new Scanner(System.in);

        System.out.println("1 or 2 players?");
        String players = keyboard.nextLine();

        String city;

        if (players.equals("1")) {
            Scanner scanner = new Scanner(new File("C:\\Users\\danie\\IdeaProjects\\untitled\\src\\Cities.txt"));
            List<String> cities = new ArrayList<>();

            while (scanner.hasNext()) {
                cities.add(scanner.nextLine());
            }

            Random rand = new Random();
            city = cities.get(rand.nextInt(cities.size()));
        }
        else {
            System.out.println("Player 1, please enter your word:");
            city = keyboard.nextLine();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            System.out.println("Ready for player 2! Good luck!");
        }

        //System.out.println(word);

        List<Character> playerGuesses = new ArrayList<>();

        Integer wrongCount = 0;

        while(true) {
            printHangedMan(wrongCount);

            if (wrongCount >= 6) {
                System.out.println("You lose!");
                System.out.println("The word was: " + city);
                break;
            }

            printWordState(city, playerGuesses);
            if (!getPlayerGuess(keyboard, city, playerGuesses)) {
                wrongCount++;
            }

            if(printWordState(city, playerGuesses)) {
                System.out.println("You win!");
                break;
            }

            System.out.println("Please enter your guess for the word:");
            if(keyboard.nextLine().equals(city)) {
                System.out.println("You win!");
                break;
            }
            else {
                System.out.println("Nope! Try again.");
            }
        }
    }

    private static void printHangedMan(Integer wrongCount) {
        System.out.println(" -------");
        System.out.println(" |     |");
        if (wrongCount >= 1) {
            System.out.println(" O");
        }

        if (wrongCount >= 2) {
            System.out.print("\\ ");
            if (wrongCount >= 3) {
                System.out.println("/");
            }
            else {
                System.out.println("");
            }
        }

        if (wrongCount >= 4) {
            System.out.println(" |");
        }

        if (wrongCount >= 5) {
            System.out.print("/ ");
            if (wrongCount >= 6) {
                System.out.println("\\");
            }
            else {
                System.out.println("");
            }
        }
        System.out.println("");
        System.out.println("");
    }

    private static boolean getPlayerGuess(Scanner keyboard, String city, List<Character> playerGuesses) {
        System.out.println("Please enter a letter:");
        String letterGuess = keyboard.nextLine();
        playerGuesses.add(letterGuess.charAt(0));

        return city.contains(letterGuess);
    }

    private static boolean printWordState(String city, List<Character> playerGuesses) {
        int correctCount = 0;
        for (int i = 0; i < city.length(); i++) {
            if (playerGuesses.contains(city.charAt(i))) {
                System.out.print(city.charAt(i));
                correctCount++;
            }
            else {
                System.out.print("_");
            }
        }
        System.out.println();

        return (city.length() == correctCount);
    }
}