import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LetterBoxed {

    private File file;

    public LetterBoxed(String filePath) {
        file = new File(filePath);
    }

    /**
     * Returns the words in the words.txt file that contain a filter set of letters
     * verbatim.
     * 
     * @param letters the input String which acts as the filter.
     * @return the words in the words.txt file that contain <strong>letters</strong>
     *         verbatim.
     */
    public ArrayList<String> getWordsWithFilter(String letters) {
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
                if (flag == true)
                    res.add(line);
                line = scanner.nextLine();
            } // end of while
            return removeDoubles(res);
        } catch (Exception e) {
            System.out.println(e);
        }
        return null; // test comment
    }

    /**
     * Returns a list of words that each do not contain same adjacent letters.
     * 
     * @param input the input list of possible words that feature same adjacent
     *              letters.
     * @return a list of words that each do not contain same adjacent letters.
     */
    private ArrayList<String> removeDoubles(ArrayList<String> input) {
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