import java.util.Scanner;

/**
 * Coordinates the game flow:
 * 1. Initialize game
 * 2. Accept user guesses
 * 3. Validate guesses
 * 4. Stop when game ends
 * @author Krishna Ch
 * @version 2.0
 */
public class GuessingApp {

    public static final String CORRECT = "CORRECT";

    public static void main(String[] args) {

        System.out.println("Welcome to the Guessing App");
        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();

        Scanner sc = new Scanner(System.in);
        int attempts = 0;
        int hintCount = 0;

        /*
         * Game loop runs until the player exhausts the
         * maximum attempts
         */
        while (attempts < gameConfig.getMAX_ATTEMPTS()) {
            System.out.println("Enter your guess: ");
            int guess = sc.nextInt();
            attempts++;

            String result = GuessValidator.validateGuess(guess, gameConfig.getTargetNumber());

            System.out.println(result);

            /*
             * Stop the loop immediately
             * if the correct number is guessed.
             */
            if (CORRECT.equals(result)) {
                break;
            }

            /*
             * if the incorrect number is guessed give hint.
             */

            hintCount++;
            String hint = HintService.generateHint(gameConfig.getTargetNumber(),hintCount);
            System.out.println(hint);
        }
    }
}
