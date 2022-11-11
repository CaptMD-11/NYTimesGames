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
            letterGridAsStringsAllCombos[i] = getAllCombosOfString(letterGrid[i]);
        }
    }

    public ArrayList<String> getWordsThatHaveAllLetters() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allCrossWords = getWordsFeaturingAllLettersWithCross();
        ArrayList<String> res = new ArrayList<String>();
        int count;
        for (int i = 0; i < allCrossWords.size(); i++) {
            count = 0;
            for (int j = 0; j < allCrossWords.get(i).length(); j++) {
                if (letters.indexOf(allCrossWords.get(i).substring(j, j + 1)) == -1)
                    count++;
            }
            if (count == 0)
                res.add(allCrossWords.get(i));
        }
        return res;
    }

    public ArrayList<String> getWordsFeaturingAllLettersWithCross() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allLetterWords = getWordsFeaturingAllLetters();
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < allLetterWords.size(); i++) {
            if (wordViolatesCrossRule(allLetterWords.get(i)) == false)
                res.add(allLetterWords.get(i));
        }
        return res;
    }

    private boolean wordViolatesCrossRule(String word) {
        for (int i = 0; i < letterGridAsStringsAllCombos.length; i++) {
            for (int j = 0; j < word.length() - 1; j++) {
                if (letterGridAsStringsAllCombos[i].indexOf(word.substring(j, j + 2)) != -1)
                    return true;
            }
        }
        return false;
    }

    private String getAllCombosOfString(String[] arr) { // length of arr is 3
        String abc = arr[0] + arr[1] + arr[2];
        String acb = arr[0] + arr[2] + arr[1];
        String bac = arr[1] + arr[0] + arr[2];
        String bca = arr[1] + arr[2] + arr[0];
        String cab = arr[2] + arr[0] + arr[1];
        String cba = arr[2] + arr[1] + arr[0];
        return abc + acb + bac + bca + cab + cba;
    }

    private String arrayToString(String[] arr) {
        String res = "";
        for (int i = 0; i < arr.length; i++) {
            res += arr[i];
        }
        return res;
    }

    private ArrayList<String> getWordsFeaturingAllLetters() { // lower case version of letters
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