import java.util.Arrays;

public class SudokuPuzzle {
    private final int[][] gameGrid;
    private int[][] backtrackingGrid;
    private int[][] solutionGrid;
    private int difficulty;

    public SudokuPuzzle(String gameString) {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        // TODO add the solve method here: solutionGrid =
    }

    public SudokuPuzzle(int[][] gameGrid) {
        this.gameGrid = gameGrid;
    }

    public SudokuPuzzle(String gameString, String SolutionString) {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        solutionGrid = SudokuLibrary.convertStringToGrid(SolutionString);
    }

    public void solvePuzzle() {

    }

    public boolean moveIsPossible(int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (gameGrid[row][i] == number) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (gameGrid[i][col] == number) {
                return false;
            }
        }

        // TODO: Finish
        return false;

    }

    public void printGameGrid() {

        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {

                if (j == 3 || j == 6) {
                    System.out.print("   ");
                }

                if (gameGrid[i][j] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + gameGrid[i][j]);
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
    }
}
