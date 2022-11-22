import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class Test {
    public File file;
    public String letters;
    public String lettersCopy;

    public Test(String fileName, String inputLetters) {
        file = new File(fileName);
        letters = inputLetters;
        lettersCopy = inputLetters;
    }

    public ArrayList<String> getPangram() {
        ArrayList<String> firstOutput = new ArrayList<String>();
        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            int count;
            while (scanner.hasNextLine()) {
                count = 0;
                for (int i = 0; i < line.length(); i++) {
                    if (letters.indexOf(line.substring(i, i + 1)) != -1)
                        count++;
                }
                if (count == letters.length())
                    firstOutput.add(line);
                line = scanner.nextLine();
            }
            ArrayList<String> secondOutput = new ArrayList<String>();
            int tempCount;
            for (int i = 0; i < firstOutput.size(); i++) {
                tempCount = 0;
                for (int j = 0; j < firstOutput.get(i).length(); j++) {
                    if (letters.indexOf(firstOutput.get(i).substring(j, j + 1)) == -1)
                        tempCount++;
                }
                if (tempCount == 0)
                    secondOutput.add(firstOutput.get(i));
            }
            ArrayList<String> thirdOutput = new ArrayList<String>();
            for (int i = 0; i < secondOutput.size(); i++) {
                letters = lettersCopy;
                for (int j = 0; j < secondOutput.get(i).length(); j++) {
                    if (letters.indexOf(secondOutput.get(i).substring(j, j + 1)) != -1
                            && isLetterRemoved(secondOutput.get(i).substring(j, j + 1)) == false)
                        removeLetter(secondOutput.get(i).substring(j, j + 1));
                }
                if (letters.length() == 0)
                    thirdOutput.add(secondOutput.get(i));
            }
            return thirdOutput;
        } catch (Exception e) {
            System.out.println(e);
        }
        return null;
    }

    private void removeLetter(String letter) {
        String res = "";
        for (int i = 0; i < letters.length(); i++) {
            if (letters.substring(i, i + 1).equals(letter) == false)
                res += letters.substring(i, i + 1);
        }
        letters = res;
    }

    private boolean isLetterRemoved(String letter) {
        for (int i = 0; i < letters.length(); i++) {
            if (letters.substring(i, i + 1).equals(letter))
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Test obj = new Test("words.txt", "dotbeyc");
        System.out.println(obj.getPangram());
    }

}