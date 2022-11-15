import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        String[][] letterGrid = { { "r", "h", "t" }, { "u", "s", "o" }, { "i", "w", "c" }, { "m", "a", "d" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);

        ArrayList<String> res = obj.getValidWordsWithCross();

        // for (int i = 0; i < res.size(); i++) {
        // System.out.println(res.get(i));
        // }

        System.out.println(obj.getWordsStartingWithAndContaining(res, "h", "dmou"));

    }
}