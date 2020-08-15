import java.util.Random;

public class SudokuGameEngine {

    public static void main(String[] args) {
        int min = 0;
        int max = 399;

//        Random random = new Random();
//        System.out.println(random.nextInt(max - min + 1) + min);

        SudokuPuzzle sudokuPuzzle = null;
        try {
            sudokuPuzzle = new SudokuPuzzle("004300209005009001070060043006002087190007400050083000600000105003508690042910300");
        } catch (PuzzleNotValidException e) {
            e.printStackTrace();
        }
    }

}
