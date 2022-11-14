import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        // System.out.println(NYTimesSpellingBee.swapLetters("null", 0, 1));

        String[][] letterGrid = { { "c", "s", "v" }, { "e", "p", "o" }, { "i", "f", "n" }, { "r", "a", "d" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);
        ArrayList<String> res = obj.getWordsFeaturingAllLettersWithCross();

        // System.out.println(res.size());

        for (int i = 0; i < res.size(); i++) {
            System.out.println(res.get(i));
        }

        // int num = (int) (Math.random() * (res.size()));

        // System.out.println(res.get(num));

        // System.out.println(obj.getWordsWithFilter("crates"));

        // NYTimesSpellingBee obj = new NYTimesSpellingBee();

        // System.out.println(obj.swapLetters("abcd", 0, 2));

        // System.out.println(obj.removeRepeats(obj.addIndividualWordPermutations("abcd")));

        // System.out.println(obj.addIndividualWordPermutations("abcd"));

        // System.out.println(obj.getPermutationsOfLettersWithRepeats("abcd"));

        // System.out.println(obj.swapLetters("null", 0, 1));

        // System.out.println(obj.wordExists("null"));
    }
}