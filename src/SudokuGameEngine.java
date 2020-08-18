import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class SudokuGameEngine {

    /**
     * The main driver code for this app
     * @param args args
     * @throws IOException if the file for the puzzle library fails to load.
     * @throws PuzzleNotValidException if loading puzzles into objects from the library fails.
     */
    public static void main(String[] args) throws IOException, PuzzleNotValidException {

        // Create the library of puzzles for users to select from
        SudokuLibrary sudokuLibrary = new SudokuLibrary("SudokuPuzzleLibrary.csv");
        // Throws an IO exception because program should stop if library is not loaded for any reason
        sudokuLibrary.readLibrary();

        Scanner kb = new Scanner(System.in);
        System.out.println("Welcome to the Sudoku App! Want a source of fun Sudoku puzzles to solve? Stuck on a hard " +
                "puzzle and lost your solution manual? Well you have come to the right place!");

        boolean done = false;
        int userInput;
        while (!done) {
            printMenu();

            try {
                System.out.println("Enter a menu option: ");
                userInput = kb.nextInt();

            } catch (InputMismatchException e) {
                // Clear the scanner
                kb.nextLine();
                System.out.println();
                System.out.println("Please enter an integer value.");
                continue;
            }

            switch (userInput) {
                case 1:
                    userRandomPuzzle(sudokuLibrary);
                    break;
                case 2:
                    findSolutionToUserPuzzle();
                    break;
                case 3:
                    done = true;
                    break;
                default:
                    System.out.println();
                    System.out.println("Please enter a valid option.");
                    System.out.println();
            }
        }
    }

    /**
     * Finds the solution to a user given puzzle and shows them what it is.
     * @throws PuzzleNotValidException if loading the example puzzle fails.
     */
    private static void findSolutionToUserPuzzle() throws PuzzleNotValidException {
        String gameString = "004300209005009001070060043006002087190007400050083000600000105003508690042910300";
        Scanner kb = new Scanner(System.in);
        String userInput;

        SudokuPuzzle examplePuzzle = new SudokuPuzzle(gameString);

        System.out.println();
        System.out.println();
        System.out.println("Please enter the puzzle you wish to be solved as a string, blank squares should be " +
                "entered as zeros.");
        System.out.println();
        System.out.println("For example, if I wanted to find the solution to the below puzzle:");

        examplePuzzle.printGameGrid();

        System.out.println("I would enter: " + gameString);

        System.out.println();
        System.out.println("Enter the puzzle string below:");
        userInput = kb.nextLine();

        SudokuPuzzle userPuzzle;
        try {
            userPuzzle = new SudokuPuzzle(userInput);
        } catch (PuzzleNotValidException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println();
        System.out.println("You entered:");
        userPuzzle.printGameGrid();

        try {
            userPuzzle.solvePuzzle();
        } catch (PuzzleNotValidException e) {
            System.out.println(e.getMessage());
            return;
        }

        System.out.println();
        System.out.println("The solution to this puzzle is:");
        userPuzzle.printSolutionGrid();

        System.out.println();
        System.out.println("Exiting back to the main menu!");
    }

    /**
     * Grabs a random puzzle from the library for a user to solve.
     * @param library the puzzle library
     */
    private static void userRandomPuzzle(SudokuLibrary library) {
        Scanner kb = new Scanner(System.in);

        SudokuPuzzle sudokuPuzzle = library.getRandomPuzzle();

        System.out.println("Your random puzzle is a " + sudokuPuzzle.getDifficulty() + " difficulty puzzle.");
        sudokuPuzzle.printGameGrid();

        System.out.println();
        System.out.println("Go ahead and try to solve the puzzle! When you are finished, " +
                "or if you are stuck, press any key and hit enter to view the solution.");

        kb.nextLine();

        System.out.println();
        System.out.println("The solution is:");
        sudokuPuzzle.printSolutionGrid();

        System.out.println();
        System.out.println("Exiting back to the main menu!");
    }

    /**
     * Prints the main menu
     */
    private static void printMenu() {
        System.out.println();
        System.out.println(" Please select from the following menu options:");
        System.out.println("\t1) Select a random puzzle to solve.");
        System.out.println("\t2) Find the solution to a puzzle.");
        System.out.println("\t3) Quit.");
        System.out.println();
    }

}
