import java.util.Random;
import java.util.Scanner;

/**
 * TicTacToe
 * UC8 controls the continuous game loop and alternates
 * turns until the game ends.
 */
public class TicTacToe {

    static boolean isHumanTurn;
    static char humanSymbol;
    static char computerSymbol;
    static boolean gameOver = false;

    static char[][] board = new char[3][3];

    /**
     * Entry point of the program. Demonstrates the structure
     * of a continuous game loop.
     */
    public static void main(String[] args) {
        initializeBoard();
        tossAndAssignSymbols();
        displayTossResult();
        printBoard();

        Scanner scanner = new Scanner(System.in);

        while (!gameOver) {
            char currentSymbol = isHumanTurn ? humanSymbol : computerSymbol;

            if (isHumanTurn) {
                System.out.println("\nYour turn.");
                int slot, row, col;
                while (true) {
                    slot = getUserSlot(scanner);
                    row = getRowFromSlot(slot);
                    col = getColFromSlot(slot);
                    if (isValidMove(row, col)) {
                        placeMove(row, col, humanSymbol);
                        break;
                    }
                    System.out.println("Invalid move. Try again.");
                }
            } else {
                System.out.println("\nComputer's turn.");
                computerMove();
            }

            printBoard();

            if (checkWin(currentSymbol)) {
                System.out.println(isHumanTurn ? "You win!" : "Computer wins!");
                gameOver = true;
            } else if (isBoardFull()) {
                System.out.println("It's a draw!");
                gameOver = true;
            } else {
                isHumanTurn = !isHumanTurn; // switch turn
            }
        }
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

    /**
     * Checks if the given row and column are within bounds
     * and if the target cell is empty.
     * Input: Row, Column
     * Output: true if valid, false otherwise.
     */
    static boolean isValidMove(int row, int col) {
        if (row >= 0 && row <= 2 && col >= 0 && col <= 2) {
            return board[row][col] == '-';
        }
        return false;
    }

    /**
     * Updates the board by placing the given symbol at
     * the specified row and column.
     * Input: Row, Column, Symbol
     * Hint: Assume the move is already validated.
     */
    static void placeMove(int row, int col, char symbol) {
        board[row][col] = symbol;
    }

    /**
     * Generates random slot values until a valid move is found,
     * then places the computer symbol on the board.
     */
    static void computerMove() {
        Random random = new Random();
        int slot, row, col;
        while (true) {
            slot = random.nextInt(9) + 1; // 1 to 9
            row = getRowFromSlot(slot);
            col = getColFromSlot(slot);
            if (isValidMove(row, col)) {
                placeMove(row, col, computerSymbol);
                System.out.println("Computer chose slot: " + slot);
                break;
            }
        }
    }

    static boolean checkWin(char symbol) {
        for (int i = 0; i < 3; i++) {
            if ((board[i][0] == symbol && board[i][1] == symbol && board[i][2] == symbol) ||
                (board[0][i] == symbol && board[1][i] == symbol && board[2][i] == symbol)) {
                return true;
            }
        }
        if ((board[0][0] == symbol && board[1][1] == symbol && board[2][2] == symbol) ||
            (board[0][2] == symbol && board[1][1] == symbol && board[2][0] == symbol)) {
            return true;
        }
        return false;
    }

    static boolean isBoardFull() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (board[i][j] == '-') return false;
            }
        }
        return true;
    }
}
