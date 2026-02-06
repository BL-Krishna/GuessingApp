import java.util.Scanner;

/**
 * MAIN CLASS
 +
 * Use Case 6: Game Restart & Exit
 * This class coordinates the complete game lifecycle,
 * allowing the player to replay or exit gracefully.
 * Responsibilities:
 * Start a new game session
 * Execute the guessing flow
 * Persist game results
 * Restart or exit based on user choice
 *
 * @author Krishna Ch
 * @version 6.0
 */
public class GuessingApp {

    public static final String CORRECT = "CORRECT";

    public static void main(String[] args) throws InvalidInputException {
        Scanner sc = new Scanner(System.in);
        boolean restart;

        System.out.println("===========================");
        System.out.println("Welcome to the Guessing App");
        System.out.println("===========================\n");

        /* outer loop controls whether
         * a new game session should start.
         */
    do {
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
         * Inner loop handles the guessing
         * logic for a single game session.
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

        /*
         * Player decides whether to
         * restart the game or exit.
         */
        restart = GameController.restartGame(sc);
      }while (restart);
    }
}
