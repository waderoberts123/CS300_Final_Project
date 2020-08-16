import java.util.Arrays;

public class SudokuTester {

    public static void testSudokuLibraryReader() throws PuzzleNotValidException {

        String gameStringTest = "004300209005009001070060043006002087190007400050083000600000105003508690042910300";
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
    }

    public static void main(String[] args) throws PuzzleNotValidException {
        testSudokuLibraryReader();
    }

}
