import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LetterBoxed {

    private File file;
    private String letters;
    private String[][] letterGrid;
    private String[] letterGridAsStrings;
    private String[] letterGridAsStringsAllCombos;

    public LetterBoxed(String filePath, String[][] inputLetterGrid) {
        file = new File(filePath);
        letterGrid = inputLetterGrid;
        letters = "";
        for (int i = 0; i < letterGrid.length; i++) {
            for (int j = 0; j < letterGrid[i].length; j++) {
                letters += letterGrid[i][j];
            }
        }
        letterGridAsStrings = new String[letterGrid.length];
        for (int i = 0; i < letterGridAsStrings.length; i++) {
            letterGridAsStrings[i] = arrayToString(letterGrid[i]);
        }
        letterGridAsStringsAllCombos = new String[letterGrid.length];
        for (int i = 0; i < letterGridAsStringsAllCombos.length; i++) {
            letterGridAsStringsAllCombos[i] = getAllPermsOfString(letterGrid[i]);
        }
    }

    /**
     * Returns a list that contains word that use up every letter of the Letter
     * Boxed grid.
     * 
     * @return a list that contains word that use up every letter of the Letter
     *         Boxed grid.
     */
    public ArrayList<String> getWordsThatHaveAllLetters() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allCrossWords = getValidWordsWithCross();
        ArrayList<String> allLettersAsList;
        ArrayList<String> res = new ArrayList<String>();
        int count;
        for (int i = 0; i < allCrossWords.size(); i++) {
            count = 0;
            allLettersAsList = getAllLettersAsList();
            for (int j = 0; j < allCrossWords.get(i).length(); j++) {
                if (allLettersAsList.indexOf(allCrossWords.get(i).substring(j, j + 1)) != -1) {
                    for (int k = 0; k < allLettersAsList.size(); k++) {
                        if (allLettersAsList.get(k).equals(allCrossWords.get(i).substring(j, j + 1)))
                            allLettersAsList.remove(k);
                    }
                }
            }
            if (allLettersAsList.size() == 0)
                res.add(allCrossWords.get(i));
        }
        return res;
    }

    /**
     * Returns a list of words that satisfy the cross-rule for Letter Boxed. In
     * other words, this list gets all the possible words you can input into the
     * game.
     * 
     * @return list of words that satisfy the cross-rule for Letter Boxed.
     */
    public ArrayList<String> getValidWordsWithCross() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allLetterWords = getWordsFeaturingAllowedLetters();
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < allLetterWords.size(); i++) {
            if (wordViolatesCrossRule(allLetterWords.get(i)) == false)
                res.add(allLetterWords.get(i));
        }
        return res;
    }

    /**
     * Returns the <strong>letters</strong> as a list, where each letter has its own
     * index.
     * 
     * @return the <strong>letters</strong> as a list, where each letter has its own
     *         index.
     */
    private ArrayList<String> getAllLettersAsList() {
        ArrayList<String> allLettersAsList = new ArrayList<String>();
        for (int i = 0; i < letters.length(); i++) {
            allLettersAsList.add(letters.substring(i, i + 1));
        }
        return allLettersAsList;
    }

    /**
     * Returns true if a word contains consecutive letters that are
     * on the same side of the Letter Boxed grid, otherwise false.
     * 
     * @param word , the input word.
     * @return true if <strong>word</strong> contains consecutive letters that are
     *         on the same side of the Letter Boxed grid, otherwise false.
     */
    private boolean wordViolatesCrossRule(String word) {
        for (int i = 0; i < letterGridAsStringsAllCombos.length; i++) {
            for (int j = 0; j < word.length() - 1; j++) {
                if (letterGridAsStringsAllCombos[i].indexOf(word.substring(j, j + 2)) != -1)
                    return true;
            }
        }
        return false;
    }

    /**
     * Returns all possible permutations of a <code>String[]</code> as a
     * <code>String</code>.
     * 
     * @param arr , the input array.
     * @return a <code>String</code> containing all the possible permutations of the
     *         letters in <strong>arr</strong>.
     */
    private String getAllPermsOfString(String[] arr) { // length of arr is 3
        String abc = arr[0] + arr[1] + arr[2];
        String acb = arr[0] + arr[2] + arr[1];
        String bac = arr[1] + arr[0] + arr[2];
        String bca = arr[1] + arr[2] + arr[0];
        String cab = arr[2] + arr[0] + arr[1];
        String cba = arr[2] + arr[1] + arr[0];
        return abc + acb + bac + bca + cab + cba;
    }

    /**
     * Returns the contents of an array as a <code>String</code>.
     * 
     * @param arr , the input array.
     * @return the contents of <strong>arr</strong> as a <code>String</code>.
     */
    private String arrayToString(String[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }

    /**
     * Returns a list that contains words containing the allowed letters.
     * 
     * @return a list that contains words containing the allowed letters.
     */
    private ArrayList<String> getWordsFeaturingAllowedLetters() { // lower case version of letters
        ArrayList<String> res = new ArrayList<String>();
        int count = 0;
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) {
                for (int i = 0; i < line.length(); i++) {
                    if (letters.indexOf(line.substring(i, i + 1)) == -1)
                        count++;
                }
                if (count == 0)
                    res.add(line);
                line = scanner.nextLine();
                count = 0;
            }
            return removeWordsWithConsecs(res);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    /**
     * Returns a list of words that each do not contain same adjacent letters.
     * 
     * @param input the input list of possible words that feature same adjacent
     *              letters.
     * @return a list of words that each do not contain same adjacent letters.
     */
    private ArrayList<String> removeWordsWithConsecs(ArrayList<String> input) {
        ArrayList<String> res = new ArrayList<String>();
        int count;
        for (int i = 0; i < input.size(); i++) {
            count = 0;
            for (int j = 0; j < input.get(i).length() - 1; j++) {
                if (input.get(i).substring(j, j + 1).equals(input.get(i).substring(j + 1, j + 2)))
                    count++;
            }
            if (count == 0)
                res.add(input.get(i));
        }
        return res;
    }
}