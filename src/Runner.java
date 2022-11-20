import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        String[][] letterGrid = { { "u", "m", "p" }, { "f", "i", "s" }, { "e", "c", "o" }, { "t", "a", "r" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);

        ArrayList<String> res = obj.getValidWordsWithCross();

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        // ArrayList<String> temp = new ArrayList<String>();

        // temp.add("star");
        // temp.add("tar");
        // temp.add("to");

        // System.out.println(obj.getWordsFromShortestToLongest(temp));

        // System.out.println(obj.getPangrams());

        // System.out.println(obj.getWordsStartingWith(res, "a"));

        // System.out.println(obj.getWordsStartingWithAndContaining(res, "s", "cf"));

    }
}