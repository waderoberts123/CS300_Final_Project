public class SudokuPuzzle {
    private final int[][] gameGrid;
    private int[][] backtrackingGrid = new int[9][9];
    private int[][] solutionGrid = new int[9][9];
    private int difficulty;

    public SudokuPuzzle(String gameString) throws PuzzleNotValidException {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        copy2DArray(gameGrid, backtrackingGrid);
        if (solvePuzzle()) {
            copy2DArray(backtrackingGrid, solutionGrid);
        } else {
            throw new PuzzleNotValidException("Solution does not exist.");
        }
    }

    public SudokuPuzzle(int[][] gameGrid) {
        this.gameGrid = gameGrid;
        copy2DArray(this.gameGrid, backtrackingGrid);
        solvePuzzle();
        copy2DArray(backtrackingGrid, solutionGrid);
    }

    public SudokuPuzzle(String gameString, String SolutionString) {
        gameGrid = SudokuLibrary.convertStringToGrid(gameString);
        solutionGrid = SudokuLibrary.convertStringToGrid(SolutionString);
    }

    private void copy2DArray(int[][] arrayToCopyFrom, int[][] arrayToCopyTo) {
        for (int i = 0; i < 9; i++) {
            System.arraycopy(arrayToCopyFrom[i], 0, arrayToCopyTo[i], 0, 9);
        }
    }

    public boolean solvePuzzle() {

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {

                if (backtrackingGrid[i][j] == 0) {

                    for (int k = 1; k < 10; k++) {

                        if (moveIsPossible(i, j, k)) {
                            backtrackingGrid[i][j] = k;

                            solvePuzzle();

                            // If this is true, the game is won
                            if (!hasEmptyGridCells()) {
                                return true;
                            }

                            backtrackingGrid[i][j] = 0;
                        }
                    }
                    // No possible moves, backtrack
                    return false;
                }
            }
        }
        return false;
    }

    private boolean hasEmptyGridCells() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (backtrackingGrid[i][j] == 0) {
                    return true;
                }
            }
        }
        return false;
    }


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

    private void printBacktrackingGrid() {

        for (int i = 0; i < backtrackingGrid.length; i++) {
            for (int j = 0; j < backtrackingGrid[0].length; j++) {

                if (j == 3 || j == 6) {
                    System.out.print("   ");
                }

                if (backtrackingGrid[i][j] == 0) {
                    System.out.print(" .");
                } else {
                    System.out.print(" " + backtrackingGrid[i][j]);
                }
            }
            System.out.println();
            if (i == 2 || i == 5) {
                System.out.println();
            }
        }
    }

}
