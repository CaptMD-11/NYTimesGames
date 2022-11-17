import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        String[][] letterGrid = { { "r", "s", "h" }, { "y", "i", "a" }, { "d", "e", "l" }, { "w", "k", "b" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);

        ArrayList<String> res = obj.getValidWordsWithCross();

        // for (int i = 0; i < res.size(); i++) {
        // System.out.println(res.get(i));
        // }

        System.out.println(obj.getPangrams());

        System.out.println(obj.getWordsStartingWith(res, "s"));

        System.out.println(obj.getWordsStartingWithAndContaining(res, "r", "h"));

    }
}