/**
 * Represents a Sudoku Puzzle, which contains the game grid, solution, and difficulty.
 *
 * @author Wade Roberts
 * @see SudokuGameEngine
 */
public class SudokuPuzzle {
    private final int[][] gameGrid;
    private String difficulty; // Can be "Easy", "Medium", or "Hard"
    private int numSolutions;
    private final int[][] backtrackingGrid;
    private final int[][] solutionGrid;

    /**
     * Constructor that requires the game string to initialize the object. Will not populate the solution array until
     * solvePuzzle is called.
     * @param gameString the string of length 81 with 0-9 characters representing the Sudoku game.
     * @throws PuzzleNotValidException if the game string is not valid in any way.
     */
    public SudokuPuzzle(String gameString) throws PuzzleNotValidException {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        difficulty = setDifficulty();
        numSolutions = 0;
        backtrackingGrid = gameGrid;
        solutionGrid = new int[9][9];
    }

    /**
     * Constructor that requires both the game string and a corresponding solution string to initialize the object.
     * solvePuzzle need not be called if the object is instantiated in this manner.
     * @param gameString the string of length 81 with 0-9 characters representing the Sudoku game.
     * @param SolutionString the string of length 81 with 0-9 characters representing the Sudoku solution.
     * @throws PuzzleNotValidException if the game string or solution string is not valid in any way.
     */
    public SudokuPuzzle(String gameString, String SolutionString) throws PuzzleNotValidException {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        difficulty = setDifficulty();
        numSolutions = 0;
        backtrackingGrid = gameGrid;
        solutionGrid = SudokuLibrary.convertStringToGrid(SolutionString);
    }

    /**
     * Sets the difficulty of the puzzle based on the amount of empty squares.
     * @return the difficulty ("Easy", "Medium", or "Hard")
     */
    private String setDifficulty() {
        int numEmptySquares = getNumberOfEmptySquares(gameGrid);

        if (numEmptySquares <= 46) {
            difficulty = "Easy";
        } else if (numEmptySquares <= 48) {
            difficulty = "Medium";
        } else {
            difficulty = "Hard";
        }

        return difficulty;
    }

    /**
     * @param gameGrid the Sudoku game grid.
     * @return the number of empty (zero) squares in the sudoku puzzle.
     */
    private int getNumberOfEmptySquares(int[][] gameGrid) {
        int count = 0;

        //noinspection ForLoopReplaceableByForEach
        for (int i = 0; i < gameGrid.length; i++) {
            for (int j = 0; j < gameGrid[0].length; j++) {
                if (gameGrid[i][j] == 0) {
                    count++;
                }
            }
        }

        return count;
    }

    /**
     * Copies the contents of one array into the other array (deep copy)
     * @param arrayToCopyFrom the array to copy from
     * @param arrayToCopyTo the array to copy into
     */
    private void copy2DArray(int[][] arrayToCopyFrom, int[][] arrayToCopyTo) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(arrayToCopyFrom[i], 0, arrayToCopyTo[i], 0, 9);
        }
    }

    /**
     * Recursively finds all possible solutions of the Sudoku puzzle. However, if more than one solution exists,
     * this will throw an Exception
     * @throws PuzzleNotValidException if more than one solution exists.
     */
    public void solvePuzzle() throws PuzzleNotValidException {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (backtrackingGrid[i][j] == 0) {

                    for (int k = 1; k < 10; k++) {

                        if (moveIsPossible(i, j, k)) {
                            backtrackingGrid[i][j] = k;

                            solvePuzzle();

                            backtrackingGrid[i][j] = 0;
                        }
                    }
                    // Getting to this point means that none of the moves were possible,
                    // so we go back and set it to zero in the stack level one prior and continue with the next
                    // possible number
                    return;
                }
            }
        }

        // Getting to this point means that we completed both outer loops, meaning that we filled up the grid. However,
        // this will return, so if there is more than one solution, we will continue searching, because we backtrack
        // and continue to test all possible solutions in the puzzle.
        copy2DArray(backtrackingGrid, solutionGrid);
        numSolutions++;

        if (numSolutions > 1) {
            throw new PuzzleNotValidException("ERROR: More than one solution exists for the given puzzle, " +
                    "which is not valid.");
        }
    }

    /**
     * Checks if a move is possible by row, column, and the square the cell is in.
     * @param row the row to check
     * @param col the column to check
     * @param number the number to check
     * @return true if the move is possible, false otherwise
     */
    private boolean moveIsPossible(int row, int col, int number) {
        for (int i = 0; i < 9; i++) {
            if (backtrackingGrid[row][i] == number) {
                return false;
            }
        }

        for (int i = 0; i < 9; i++) {
            if (backtrackingGrid[i][col] == number) {
                return false;
            }
        }

        int boxStartRow = row - row % 3;
        int boxStartCol = col - col % 3;

        for (int i = boxStartRow; i < boxStartRow + 3; i++) {
            for (int j = boxStartCol; j < boxStartCol + 3; j++) {
                if (backtrackingGrid[i][j] == number) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Prints the game grid.
     */
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

    /**
     * Prints the solution grid.
     */
    public void printSolutionGrid() {

        for (int i = 0; i < solutionGrid.length; i++) {
            for (int j = 0; j < solutionGrid[0].length; j++) {

                if (j == 3 || j == 6) {
                    System.out.print("   ");
                }

                if (solutionGrid[i][j] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + solutionGrid[i][j]);
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
    }

    /**
     * Getter for the difficulty field.
     * @return the difficulty of the puzzle.
     */
    public String getDifficulty() {
        return difficulty;
    }
}
