package ngame;
import java.util.Scanner;
import java.util.Random;

public class NumberGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();
        
        int minRange = 1;
        int maxRange = 100;
        int maxAttempts = 10;
        
        int score = 0;
        boolean playAgain = true;
        
        while (playAgain) {
            int targetNumber = random.nextInt(maxRange - minRange + 1) + minRange;
            System.out.println("Welcome to the Number Guessing Game!");
            System.out.println("I have selected a number between " + minRange + " and " + maxRange + ".");
            
            int attempts = 0;
            boolean guessedCorrectly = false;
            
            while (attempts < maxAttempts) {
                System.out.print("Enter your guess: ");
                int userGuess = scanner.nextInt();
                
                if (userGuess == targetNumber) {
                    guessedCorrectly = true;
                    break;
                } else if (userGuess < targetNumber) {
                    System.out.println("Too low! Try again.");
                } else {
                    System.out.println("Too high! Try again.");
                }
                
                attempts++;
            }
            
            if (guessedCorrectly) {
                System.out.println("Congratulations! You guessed the correct number " + targetNumber + " in " + (attempts + 1) + " attempts.");
                score++;
            } else {
                System.out.println("Sorry, you've used all your attempts. The correct number was " + targetNumber + ".");
            }
            
            System.out.println("Your current score: " + score);
            
            System.out.print("Do you want to play again? (yes/no): ");
            String playAgainInput = scanner.next();
            playAgain = playAgainInput.equalsIgnoreCase("yes");
        }
        
        System.out.println("Thanks for playing! Your final score: " + score);
    }
}
