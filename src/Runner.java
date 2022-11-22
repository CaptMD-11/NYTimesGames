import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        String[][] letterGrid = { { "t", "h", "l" }, { "m", "i", "c" }, { "v", "r", "b" }, { "y", "o", "a" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);
        ArrayList<String> res = obj.getValidWordsWithCross();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        System.out.println(obj.getWordsStartingWithAndContaining(res, "i", "y"));

    }
}