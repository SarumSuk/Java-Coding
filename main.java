import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Queen {
    int row;
    int col;

    public Queen(int row, int col) {
        this.row = row;
        this.col = col;
    }
}

class Board {
    private int N;
    private List<Queen> queens;

    public Board(int N) {
        this.N = N;
        this.queens = new ArrayList<>();
    }

    public boolean isSafe(int row, int col) {
        for (Queen q : queens) {
            if (q.col == col || q.row == row || Math.abs(q.row - row) == Math.abs(q.col - col)) {
                return false;
            }
        }
        return true;
    }

    public void placeQueen(int row, int col) {
        queens.add(new Queen(row, col));
    }

    public void removeQueen(int row, int col) {
        queens.removeIf(q -> q.row == row && q.col == col);
    }

    public void solve() {
        solveNQueens(0);
    }

    private boolean solveNQueens(int row) {
        if (row == N) {
            printBoard();
            // return true; // Uncomment to find one solution
            return false; // Keep searching for all solutions
        }
        boolean foundSolution = false;
        for (int col = 0; col < N; col++) {
            if (isSafe(row, col)) {
                placeQueen(row, col);
                foundSolution = solveNQueens(row + 1) || foundSolution;
                removeQueen(row, col); // Backtrack
            }
        }
        return foundSolution;
    }

    public void printBoard() {
        char[][] board = new char[N][N];
        // Initialize the board with empty spaces
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = '.';
            }
        }
        // Place the queens on the board
        for (Queen q : queens) {
            board[q.row][q.col] = 'Q';
        }
        // Print the board
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}

public class main {
    public static void main(String[] args) {
        int N;
        try (Scanner scanner = new Scanner(System.in)) {
            System.out.print("Enter N (# of queens and size of board): ");
            N = scanner.nextInt();
        }
        Board board = new Board(N);
        board.solve();
    }
}