import java.util.Scanner;

/**
 * MAIN CLASS
 *
 * Use Case 5: Game Result Storage
 *
 * This class coordinates the complete game flow
 * and persists the final result after completion
 *
 * Responsibilities:
 * - Initialize game configuration
 * - Accept user input
 * - Generate hints when applicable
 * - Storage game result at the end
 *
 * @author Krishna Ch
 * @version 5.0
 */
public class GuessingApp {

    public static final String CORRECT = "CORRECT";

    public static void main(String[] args) throws InvalidInputException {

        System.out.println("===========================");
        System.out.println("Welcome to the Guessing App");
        System.out.println("===========================\n");

        Scanner sc = new Scanner(System.in);

        /*
         * Player name is captured once
         * and stored along with game results.
         */
        System.out.print("Enter Player Name: ");
        String player = sc.nextLine();

        GameConfig gameConfig = new GameConfig();
        gameConfig.showRules();

        int attempts = 0;
        int hintCount = 0;

        /*
         * Tracks whether the player
         * Successfully guessed the number.
         */
        boolean win = false;

        /*
         * Game loop runs until the player exhausts the
         * maximum attempts
         */
        while (attempts < gameConfig.getMAX_ATTEMPTS()) {

            System.out.print("Enter your guess: ");

            int guess = ValidationService.validateInput(sc.nextLine());
            attempts++;

            String result =
                    GuessValidator.validateGuess(guess, gameConfig.getTargetNumber());

            System.out.println(result);

            /*
             * Stop the loop immediately
             * if the correct number is guessed.
             */
            if (CORRECT.equals(result)) {
                win = true;
                break;
            }

            /*
             * if the incorrect number is guessed give hint.
             */
            hintCount++;
            String hint =
                    HintService.generateHint(gameConfig.getTargetNumber(), hintCount);

            System.out.println(hint);
        }

        /* Final game result is persisted
         * after the game loop completes.
         */
        StorageService.saveResult(player, attempts, win);

        sc.close();
    }
}
