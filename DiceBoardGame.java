import java.util.*;

class DiceBoardGame {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        System.out.print("Enter the number of players: ");
        int numPlayers = scanner.nextInt();

        System.out.print("Enter the target score: ");
        int targetScore = scanner.nextInt();

        int[] scores = new int[numPlayers];
        boolean[] completed = new boolean[numPlayers];

        playGame(0, numPlayers, scores, completed, targetScore, random, scanner);

        System.out.println("Game Over!");
        scanner.close();
    }

    public static void playGame(int currentPlayer, int numPlayers, int[] scores, boolean[] completed,
                                int targetScore, Random random, Scanner scanner) {
        if (checkAllCompleted(completed)) {
            return;
        }

        if (completed[currentPlayer]) {
            playGame((currentPlayer + 1) % numPlayers, numPlayers, scores, completed, targetScore, random, scanner);
            return;
        }

        System.out.print("Player " + (currentPlayer + 1) + " - Press Enter to roll the dice: ");
        scanner.nextLine();

        int diceValue = random.nextInt(6) + 1;
        scores[currentPlayer] += diceValue;

        System.out.println("Player " + (currentPlayer + 1) + " rolled a " + diceValue);
        System.out.println("Current Score: " + scores[currentPlayer]);

        if (scores[currentPlayer] >= targetScore) {
            System.out.println("Player " + (currentPlayer + 1) + " has won!");
            completed[currentPlayer] = true;
        }

        System.out.println();

        playGame((currentPlayer + 1) % numPlayers, numPlayers, scores, completed, targetScore, random, scanner);
    }

    public static boolean checkAllCompleted(boolean[] completed) {
        for (boolean isCompleted : completed) {
            if (!isCompleted) {
                return false;
            }
        }
        return true;
    }
}
