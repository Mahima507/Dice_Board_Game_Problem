import java.util.Random;
import java.util.Scanner;

public class DiceBoardGame {
    private static int targetScore;
    private static int[] scores;
    private static boolean[] completedPlayers;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        System.out.print("Enter the target score: ");
        targetScore = scanner.nextInt();

        scores = new int[numPlayers];
        completedPlayers = new boolean[numPlayers];

        playGame();
        scanner.close();
        System.out.println(" Game Over! ");
    }

    public static void playGame() {
        Random random = new Random();
        int currentPlayer = 0;

        while (true) {
            int roll = random.nextInt(6) + 1; // Simulate dice roll
            scores[currentPlayer] += roll;
            System.out.println("Player " + (currentPlayer + 1) + " rolled a " + roll + ". Score: " + scores[currentPlayer]);

            if (scores[currentPlayer] >= targetScore) {
                System.out.println("Player " + (currentPlayer + 1) + " wins!");
                break;
            }

            currentPlayer = (currentPlayer + 1) % scores.length;

            if (isGameComplete()) {
                System.out.println("All players have completed their turns. No winner.");
                break;
            }
        }
    }

    public static boolean isGameComplete() {
        for (int i = 0; i < scores.length; i++) {
            if (!completedPlayers[i] && scores[i] < targetScore)
                return false;
            completedPlayers[i] = true;
        }
        return true;
    }
}
