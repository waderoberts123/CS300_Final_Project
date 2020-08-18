import java.io.IOException;

public class SudokuTester {

    public static void main(String[] args) throws PuzzleNotValidException {
        testSudokuLibraryReader();
        testSudokuPuzzle();
    }

    public static void testSudokuLibraryReader() throws PuzzleNotValidException {
        // Test static methods
        String gameStringTest = "800070050090123040004900002480000020901300760067010308300000085520040006100006009";
        int[][] gameGridActual = SudokuLibrary.convertStringToGrid(gameStringTest);
        int[][] gameGridExpected = {
                {8, 0, 0, 0, 7, 0, 0, 5, 0},
                {0, 9, 0, 1, 2, 3, 0, 4, 0},
                {0, 0, 4, 9, 0, 0, 0, 0, 2},
                {4, 8, 0, 0, 0, 0, 0, 2, 0},
                {9, 0, 1, 3, 0, 0, 7, 6, 0},
                {0, 6, 7, 0, 1, 0, 3, 0, 8},
                {3, 0, 0, 0, 0, 0, 0, 8, 5},
                {5, 2, 0, 0, 4, 0, 0, 0, 6},
                {1, 0, 0, 0, 0, 6, 0, 0, 9}
        };

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                assert gameGridActual[i][j] == gameGridExpected[i][j];
            }
        }

        // Test instantiating the class
        SudokuLibrary sudokuLibrary = new SudokuLibrary("SudokuPuzzleLibrary.csv");

        try {
            sudokuLibrary.readLibrary();
        } catch (PuzzleNotValidException e) {
            System.out.println("ERROR: One of the puzzles or solutions in the library is not valid.");
        }
        catch (IOException e) {
            System.out.println("ERROR: An error occurred while reading the library, the file is not correct.");
        }

        assert sudokuLibrary.getRandomPuzzle() != null;
    }

    private static void testSudokuPuzzle() throws PuzzleNotValidException {
        String testGameString = "800070050090123040004900002480000020901300760067010308300000085520040006100006009";
        String testSolutionString = "812674953695123847734958612483769521951382764267415398376291485529847136148536279";

        // Test instantiating the class with incorrect parameters
        try {
            SudokuPuzzle testPuzzle = new SudokuPuzzle("FOO BAR");
            System.out.println("SudokuPuzzle should have thrown an PuzzleNotValid Exception");
            assert false;
        } catch (PuzzleNotValidException e) {
            // this is what should happen
        }

        try {
            SudokuPuzzle testPuzzle = new SudokuPuzzle(
                    "8ASD70050090123040004900002480000020901300760067010308300000085520040006100006009"
            );
            System.out.println("SudokuPuzzle should have thrown an PuzzleNotValid Exception for non-numeric characters");
            assert false;
        } catch (PuzzleNotValidException e) {
            // this is what should happen
        }


        // Test instantiation with just the game string
        SudokuPuzzle sudokuPuzzle = new SudokuPuzzle(testGameString);
        sudokuPuzzle.solvePuzzle();

        assert sudokuPuzzle.getDifficulty().equals("Medium");

        // Need to visually inspect the print statement to make sure the output matches what the game grid should be
        System.out.println("Please inspect the grid to make sure it is printing correctly: The string for the " +
                "grid is: 800070050090123040004900002480000020901300760067010308300000085520040006100006009");
        sudokuPuzzle.printGameGrid();
        System.out.println();

        // Need to visually inspect the print statement to make sure the output matches what the solution should be
        System.out.println("Please inspect the solution to make sure it is printing correctly: The string for the " +
                "solution is: 812674953695123847734958612483769521951382764267415398376291485529847136148536279");
        sudokuPuzzle.printSolutionGrid();
        System.out.println();

        SudokuPuzzle sudokuPuzzle1 = new SudokuPuzzle(testGameString, testSolutionString);

        assert sudokuPuzzle1.getDifficulty().equals("Medium");

        // Need to visually inspect the print statement to make sure the output matches what the solution should be
        System.out.println("Please inspect the solution to make sure it is printing correctly: The string for the " +
                "solution is: 812674953695123847734958612483769521951382764267415398376291485529847136148536279");
        sudokuPuzzle.printSolutionGrid();
    }
}
