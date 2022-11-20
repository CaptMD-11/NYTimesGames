import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String[][] letterGrid = { { "c", "s", "v" }, { "e", "p", "o" }, { "i", "f", "n" }, { "r", "a", "d" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);
        ArrayList<String> res = obj.getValidWordsWithCross();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        System.out.println(obj.getWordsStartingWithAndContaining(res, "c", ""));

    }
}