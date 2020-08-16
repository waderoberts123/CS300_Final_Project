public class PuzzleNotValidException extends Exception {

    //Java requires that all subclasses of Exception have this field
    // which must hold a value of type long....I like 17...L stands for long
    private static final long serialVersionUID = 17L;

    /**
     * Default constructor for this Exception class.
     */
    public PuzzleNotValidException() {
        this("Puzzle has incorrect amount of numbers or more than one solution.");
    }

    /**
     * Constructor that allows the user to enter an error message
     * @param message the message to include with the exception
     */
    public PuzzleNotValidException(String message) {
        super(message);
    }

}
