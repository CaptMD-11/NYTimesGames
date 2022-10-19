import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class NYTimesSpellingBee {
    private static ArrayList<String> wordPermutationsWithRepeats;
    private static ArrayList<String> wordPermutationsNoRepeats;

    public NYTimesSpellingBee() {
        wordPermutationsWithRepeats = new ArrayList<String>();
        wordPermutationsNoRepeats = new ArrayList<String>();
    }

    public static ArrayList<String> getPermutationsOfLettersNoRepeats(ArrayList<String> withRepeatsList) {
        wordPermutationsNoRepeats.add(withRepeatsList.get(0));
        for (int i = 1; i < withRepeatsList.size(); i++) {
            if (!isInList(wordPermutationsNoRepeats, withRepeatsList.get(i)))
                wordPermutationsNoRepeats.add(withRepeatsList.get(i));
        }
        return wordPermutationsNoRepeats;
    }

    private static boolean isInList(ArrayList<String> list, String item) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(item))
                return true;
        }
        return false;
    }

    public static ArrayList<String> getPermutationsOfLettersWithRepeats(String letters) {
        ArrayList<String> res = new ArrayList<String>();
        for (int j = 0; j < letters.length(); j++) {
            for (int k = j; k < letters.length(); k++) {
                res.add(swapLetters(letters, j, k));
            }
        }
        for (int i = res.size() - 1; i >= letters.length(); i--) {
            res.remove(i);
        }
        for (int i = 0; i < res.size(); i++) {
            addIndividualWordPermutations(res.get(i));
        }
        return wordPermutationsWithRepeats;
    }

    public static void addIndividualWordPermutations(String letters) {
        for (int j = 0; j < letters.length(); j++) {
            for (int k = j; k < letters.length(); k++) {
                wordPermutationsWithRepeats.add(swapLetters(letters, j, k));
            }
        }
    }

    // GOOD
    public static String swapLetters(String word, int index1, int index2) {
        ArrayList<String> wordToList = new ArrayList<String>();
        for (int i = 0; i < word.length(); i++) {
            wordToList.add(word.substring(i, i + 1));
        }
        String index1Char = word.substring(index1, index1 + 1);
        wordToList.set(index1, wordToList.get(index2));
        wordToList.set(index2, index1Char);
        String res = "";
        for (int i = 0; i < wordToList.size(); i++) {
            res += wordToList.get(i);
        }
        return res;
    }

    // GOOD
    public static boolean wordExists(String str) {
        File file = new File("words.txt");
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                if (line.equals(str))
                    return true;
                line = scanner.nextLine();
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return false;
    }
}
