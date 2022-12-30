/*
 * This is LetterBoxed, a Java library made by Vignesh Nydhruva, that computes helps to solve the Letter Boxed puzzle by The New York Times. 
    Copyright (C) 2022  Vignesh Nydhruva

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

// VERSION 1.0 READY 

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LetterBoxed {

    private URL url;
    private String letters;
    private String[][] letterGrid;
    private String[] letterGridAsStrings;
    private String[] letterGridAsStringsAllCombos;

    public LetterBoxed(String[][] inputLetterGrid) {
        try {
            url = new URL("https://raw.githubusercontent.com/dwyl/english-words/master/words.txt");
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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

    public LetterBoxed(String urlPath, String[][] inputLetterGrid) {
        try {
            url = new URL(urlPath);
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
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
     * Returns a list of valid words for LetterBoxed when given the desired starting
     * letter and other unused letters.
     * <p>
     * Enter an empty list for the second input <code>String</code> if you want a
     * list of words when given only a starting letter.
     * 
     * @param list         the word list.
     * @param startLetter  the starting letter.
     * @param otherLetters the other unused letters in the Letter Boxed grid.
     * @return the list of valid words for LetterBoxed when given
     *         <strong>startLetter</strong> and <strong>otherLetters</strong>.
     */
    public ArrayList<String> getWordsStartingWithAndContaining(ArrayList<String> list, String startLetter,
            String otherLetters) { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> res = new ArrayList<String>();
        int count;
        for (int i = 0; i < list.size(); i++) {
            count = 0;
            for (int j = 0; j < otherLetters.length(); j++) {
                if (list.get(i).contains(otherLetters.substring(j, j + 1)))
                    count++;
            }
            if (count == otherLetters.length())
                res.add(list.get(i));
        }
        for (int i = res.size() - 1; i > -1; i--) {
            if (res.get(i).substring(0, 1).equals(startLetter) == false)
                res.remove(i);
        }
        return res;
    }

    /**
     * Returns a list that contains words that use up every letter (pangrams) of the
     * Letter
     * Boxed grid.
     * 
     * @return a list that contains word that use up every letter (pangrams) of the
     *         Letter
     *         Boxed grid.
     */
    public ArrayList<String> getPangrams() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allCrossWords = getValidLetterBoxedWords();
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
     * <p>
     * The output list is sorted from shortest words to longest words.
     * 
     * @return list of words that satisfy the cross-rule for Letter Boxed.
     */
    public ArrayList<String> getValidLetterBoxedWords() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> allLetterWords = getWordsFeaturingAllowedLetters();
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < allLetterWords.size(); i++) {
            if (wordViolatesCrossRule(allLetterWords.get(i)) == false)
                res.add(allLetterWords.get(i));
        }
        return getWordsFromShortestToLongest(res);
    }

    /**
     * Returns a sorted (in terms of word length) list of words from shortest words
     * to longest words.
     * <p>
     * This method utlizes the bubble sort algorithm.
     * 
     * @param list the unsorted list of words.
     * @return a sorted list of words based on <strong>list</strong>.
     */
    private ArrayList<String> getWordsFromShortestToLongest(ArrayList<String> list) {
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        while (isInShortestToLongestOrder(arr) == false) {
            for (int i = 0; i < arr.length - 1; i++) {
                if (arr[i].length() > arr[i + 1].length()) {
                    String temp = arr[i];
                    arr[i] = arr[i + 1];
                    arr[i + 1] = temp;
                }
            }
        }
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            res.add(arr[i]);
        }
        return res;
    }

    /**
     * Returns true if an array contains words in shortest to longest order, and
     * false otherwise.
     * 
     * @param arr an array of words.
     * @return true if <strong>arr</strong> contains words in shortest to longest
     *         order, and
     *         false otherwise.
     */
    private boolean isInShortestToLongestOrder(String[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].length() > arr[i + 1].length())
                return false;
        }
        return true;
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
     * @param word the input word.
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
     * @param arr the input array.
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
     * @param arr the input array.
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
            Scanner scanner = new Scanner(url.openStream());
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