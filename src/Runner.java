import java.util.ArrayList;

public class Runner {
    public static void main(String[] args) {
        // System.out.println(NYTimesSpellingBee.swapLetters("null", 0, 1));

        String[][] letterGrid = { { "n", "w", "r" }, { "a", "b", "i" }, { "d", "o", "c" }, { "t", "l", "e" } };

        LetterBoxed obj = new LetterBoxed("words.txt", letterGrid);
        ArrayList<String> res = obj.getWordsFeaturingAllLettersWithCross();

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