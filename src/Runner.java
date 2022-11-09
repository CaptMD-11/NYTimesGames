public class Runner {
    public static void main(String[] args) {
        // System.out.println(NYTimesSpellingBee.swapLetters("null", 0, 1));

        LetterBoxed obj = new LetterBoxed("words.txt");
        System.out.println(obj.getWordsFeaturingAllLetters("phsmbakilcrt"));

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