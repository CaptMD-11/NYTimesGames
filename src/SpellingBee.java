import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SpellingBee {

    private String mainLetter;
    private String otherLetters;
    private String allLetters;
    private File file;

    public SpellingBee(String filePath, String inputMainLetter, String inputOtherLetters) {
        mainLetter = inputMainLetter;
        otherLetters = inputOtherLetters;
        allLetters = mainLetter + otherLetters;
        file = new File(filePath);
    }

    public ArrayList<String> getPangrams() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> validWords = getValidWords();
        ArrayList<String> tempList;
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < validWords.size(); i++) {
            tempList = getAllLettersAsList();
            for (int j = 0; j < validWords.get(i).length(); j++) {
                if (tempList.indexOf(validWords.get(i).substring(j, j + 1)) != -1) {
                    for (int k = 0; k < tempList.size(); k++) {
                        if (tempList.get(k).equals(validWords.get(i).substring(j, j + 1)))
                            tempList.remove(k);
                    }
                }
            }
            if (tempList.size() == 0)
                res.add(validWords.get(i));
        }
        return res;
    }

    public ArrayList<String> getValidWords() { // IMPORTANT METHOD IN THE CLASS
        ArrayList<String> res = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            int count;
            while (scanner.hasNextLine()) {
                count = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (allLetters.indexOf(line.substring(i, i + 1)) == -1)
                        count++;
                }
                if (line.contains(mainLetter) == false)
                    count++;
                if (count == 0)
                    res.add(line);
                line = scanner.nextLine();
            }
            return res;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private ArrayList<String> getAllLettersAsList() { // HELPER METHOD
        ArrayList<String> res = new ArrayList<String>();
        for (int i = 0; i < allLetters.length(); i++) {
            res.add(allLetters.substring(i, i + 1));
        }
        return res;
    }

    public static void main(String[] args) {
        SpellingBee obj = new SpellingBee("words.txt", "c", "nabiem");
        System.out.println(obj.getValidWords());
        System.out.println(obj.getPangrams());
        System.out.println(obj.getAllLettersAsList());
    }

}