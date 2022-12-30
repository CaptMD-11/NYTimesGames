import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String[][] letterGrid = { { "t", "o", "u" }, { "f", "a", "z" }, { "e", "n", "d" }, { "g", "r", "i" } };

        LetterBoxed letterObj = new LetterBoxed(letterGrid);
        ArrayList<String> res = letterObj.getValidLetterBoxedWords();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        // System.out.println(obj.getWordsStartingWithAndContaining(res, "c", "rf"));

        System.out.println(letterObj.getPangrams());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SpellingBee spellObj = new SpellingBee("f", "eidnog");
        System.out.println(spellObj.getValidWords());
        System.out.println(spellObj.getPangrams());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}