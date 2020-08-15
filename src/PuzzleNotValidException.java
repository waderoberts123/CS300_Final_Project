public class PuzzleNotValidException extends Exception {

    //Java requires that all subclasses of Exception have this field
    // which must hold a value of type long....I like 17...L stands for long
    private static final long serialVersionUID = 17L;

    public PuzzleNotValidException() {
        this("Puzzle has incorrect amount of numbers or more than one solution.");
    }

    public PuzzleNotValidException(String message) {
        super(message);
    }

}
