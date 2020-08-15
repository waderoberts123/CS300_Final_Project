import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SudokuLibrary {
    private final String filePath;
    private ArrayList<int[][]> gameLibrary;

    public SudokuLibrary(String filePath) {
        this.filePath = filePath;
    }

    public void readLibrary() throws PuzzleNotValidException, FileNotFoundException {
        File infile = new File(filePath);
        Scanner scnr;

        scnr = new Scanner(infile);

        int ballotLine = 1;
        while (scnr.hasNext()) {
            String[] lineValues = scnr.nextLine().split(",");

            if (lineValues.length < 3) {
                throw new PuzzleNotValidException("WARNING: Ballot line has too few elements: " + ballotLine);
            }

//            // Strip off all the whitespace, and create and add the Office object to this.offices
//            Office newOffice = new Office(lineValues[0]);
//
//            for (int i = 1; i < lineValues.length; i++) {
//                lineValues[i] = lineValues[i].strip();
//                newOffice.addCandidate(lineValues[i]);
//            }
//
//            addOffice(newOffice);
//
//            ballotLine++;
        }
    }

    public static int[][] convertStringToGrid(String sudokuGameNum) {
        int counter = 0;

        int[][] gameGrid = new int[9][9];

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                gameGrid[i][j] = Character.getNumericValue(sudokuGameNum.charAt(counter));
                counter++;
            }
        }

        return gameGrid;
    }

}
