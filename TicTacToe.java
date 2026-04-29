import java.util.Scanner;

/**
 * TicTacToe
 * UC4 converts a user-entered slot number (1-9) into corresponding
 * row and column indices of a 2D array.
 */
public class TicTacToe {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;

    static char[][] board = new char[3][3];

    /**
     * Entry point of the program. Reads slot input and prints it back
     * to verify correct user input handling.
     */
    public static void main(String[] args) {
        initializeBoard();
        printBoard();
        tossAndAssignSymbols();
        displayTossResult();

        Scanner scanner = new Scanner(System.in);
        int slot = getUserSlot(scanner);
        System.out.println("Slot entered: " + slot);

        System.out.println("Row: " + getRowFromSlot(slot));
        System.out.println("Column: " + getColFromSlot(slot));
    }

    static void tossAndAssignSymbols() {
        if (Math.random() < 0.5) {
            isHumanTurn = true;
            humanSymbol = 'X';
            computerSymbol = 'O';
        } else {
            isHumanTurn = false;
            humanSymbol = 'O';
            computerSymbol = 'X';
        }
    }

    static void displayTossResult() {
        if (isHumanTurn) {
            System.out.println("You won the toss! You are '" + humanSymbol + "'. Computer is '" + computerSymbol + "'.");
        } else {
            System.out.println("Computer won the toss! Computer is '" + computerSymbol + "'. You are '" + humanSymbol + "'.");
        }
    }

    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = '-';
            }
        }
    }

    public static void printBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    /**
     * Reads an integer slot value from the user.
     * Input: Scanner object
     * Output: Slot number (1-9)
     * Hint: Validation will be added in later use cases.
     */
    static int getUserSlot(Scanner scanner) {
        System.out.print("Enter slot (1-9): ");
        return scanner.nextInt();
    }

    /**
     * Converts slot number into row index using zero-based indexing.
     * Input: Slot number (1-9)
     * Output: Row index (0-2)
     */
    static int getRowFromSlot(int slot) {
        return (slot - 1) / 3;
    }

    /**
     * Converts slot number into column index using modulo operation.
     * Input: Slot number (1-9)
     * Output: Column index (0-2)
     */
    static int getColFromSlot(int slot) {
        return (slot - 1) % 3;
    }
}
