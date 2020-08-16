import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;


public class SudokuLibrary {
    private final String filePath;
    private final ArrayList<SudokuPuzzle> gameLibrary;

    /**
     * The constructor for this class.
     * @param filePath the file path that contains the CSV file containing two columns, first column with game strings
     *                 and the secondf column with solution strings.
     */
    public SudokuLibrary(String filePath) {
        this.filePath = filePath;
        this.gameLibrary = new ArrayList<SudokuPuzzle>();
    }

    /**
     * Reads all puzzles from the library to populate the gameLibrary field.
     * @throws IOException if there is an error with opening the file.
     * @throws PuzzleNotValidException if there is a problem with any game or solution strings in the library file.
     */
    public void readLibrary() throws IOException, PuzzleNotValidException {
        List<String> fileLines = Files.readAllLines(Paths.get(filePath));
        String[] puzzleSolutionArray;
        String puzzle;
        String solution;

        //Process each line in the text file.
        for (String fileLine : fileLines) {
            puzzleSolutionArray = fileLine.split(",");
            puzzle = puzzleSolutionArray[0];
            solution = puzzleSolutionArray[1];

            gameLibrary.add(new SudokuPuzzle(puzzle, solution));
        }
    }

    /**
     * Gets a random puzzle for the user to play
     * @return the random puzzle
     */
    public SudokuPuzzle getRandomPuzzle() {
        int min = 0;
        int max = gameLibrary.size();

        Random random = new Random();
        int randomInt = random.nextInt(max - min + 1) + min;

        return gameLibrary.get(randomInt);
    }

    /**
     * Converts the game string to a 2D array.
     * @param sudokuGameNum the game string of the sudoku puzzle or solution
     * @return a 2d array representing the game or solution.
     * @throws PuzzleNotValidException if the input string is not valid in any way.
     */
    public static int[][] convertStringToGrid(String sudokuGameNum) throws PuzzleNotValidException {
        if (sudokuGameNum.length() != 81) {
            throw new PuzzleNotValidException("ERROR: The number of characters in the puzzle string is incorrect.");
        }

        int counter = 0;

        int[][] gameGrid = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameGrid[i][j] = Character.getNumericValue(sudokuGameNum.charAt(counter));

                if (gameGrid[i][j] < 0 || gameGrid[i][j] > 9) {
                    throw new PuzzleNotValidException("ERROR: Some of the characters in the puzzle string are not " +
                            "0-9 numbers.");
                }

                counter++;
            }
        }

        return gameGrid;
    }
}
