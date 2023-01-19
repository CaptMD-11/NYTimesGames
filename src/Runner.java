import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        String[][] letterGrid = { { "x", "b", "m" }, { "i", "n", "a" }, { "o", "y", "l" }, { "t", "e", "c" } };

        LetterBoxed letterObj = new LetterBoxed(letterGrid);
        ArrayList<String> res = letterObj.getValidLetterBoxedWords();
        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        System.out.println(letterObj.getWordsStartingWithAndContaining(res, "l", ""));

        System.out.println(letterObj.getPangrams());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

        SpellingBee spellObj = new SpellingBee("f", "eidnog");
        System.out.println(spellObj.getValidSpellingBeeWords());
        System.out.println(spellObj.getPangrams());

        ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    }
}