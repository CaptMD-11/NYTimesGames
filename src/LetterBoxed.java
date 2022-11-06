import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LetterBoxed {

    private File file;

    public LetterBoxed(String filePath) {
        file = new File(filePath);
    }

    public void getWordWithFilters(String letters) {

        ArrayList<String> res = new ArrayList<String>();
        boolean flag = false;

        try {
            Scanner scanner = new Scanner(file);
            String line = scanner.nextLine();
            while (scanner.hasNextLine()) { // apple
                for (int i = 0; i < letters.length(); i++) { // muf
                    if (line.indexOf(letters) != -1) {
                        flag = true;
                        continue;
                    } else {
                        flag = false;
                        break;
                    }
                }
                if (flag == true) {
                    // System.out.println(line);
                    res.add(line);
                    // break;
                }
                line = scanner.nextLine();
            } // end of while
            ArrayList<String> cleanOutput = removeDoubles(res);
            for (int i = 0; i < cleanOutput.size(); i++) {
                System.out.println(cleanOutput.get(i));
            }
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    // helper method
    public ArrayList<String> removeDoubles(ArrayList<String> input) {
        ArrayList<String> res = new ArrayList<String>();
        int count;
        for (int i = 0; i < input.size(); i++) {
            count = 0;
            for (int j = 0; j < input.get(i).length() - 1; j++) {
                if (input.get(i).substring(j, j + 1).equals(input.get(i).substring(j + 1, j + 2)))
                    count++;
            }
            if (count == 0) {
                res.add(input.get(i));
            }
        }
        return res;
    }
}