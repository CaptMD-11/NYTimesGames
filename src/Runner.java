import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String[][] letterGrid = { { "t", "o", "p" }, { "f", "a", "c" }, { "e", "n", "d" }, { "g", "r", "i" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);
        ArrayList<String> res = obj.getValidWordsWithCross();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        System.out.println(obj.getWordsStartingWithAndContaining(res, "c", "rf"));

        // System.out.println(obj.getPangrams());

    }
}