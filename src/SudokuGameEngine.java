import java.util.Random;

public class SudokuGameEngine {

    public static void main(String[] args) {
        int min = 0;
        int max = 399;

        System.out.println(4 % 3);

        Random random = new Random();

        System.out.println(random.nextInt(max - min + 1) + min);
    }

}
