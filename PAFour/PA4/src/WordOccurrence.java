import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;

public class WordOccurrence {
    public static HashMap<String, Integer> countWords(Scanner scnr) {
        HashMap<String, Integer> wordCount = new HashMap<String, Integer>();

        String line;
        while (scnr.hasNextLine()) {
            line = scnr.nextLine();
            // strip punctuation, and split line into words (do not worry if you do not understand)
            // we are creating one words array for each line
            String[] words = line.replaceAll("[^\\p{L}\\s']", " ")
                    .toLowerCase().split("\\s+");
            //loop over words array to update wordCount for each word
            //CODE HERE
            for(String word: words ){
               Integer value = wordCount.get(word);
               if (value == null){ wordCount.put(word, 1);}
               else{
                   value++;
                   wordCount.put(word, value);}
            }
        }
        return wordCount;
    }

    public static ArrayList<Pair<String, Integer>> sortWordCounts(HashMap<String, Integer> wordCount) {
        ArrayList<Pair<String, Integer>> wcList = new ArrayList<>();
        // copy (word,occurrence) pairs into ArrayList wcList
        for (String word : wordCount.keySet())
            wcList.add(new Pair<>(word, wordCount.get(word)));
        // the Class ComparingPairs class below should be created in another file: CommparingPairs.java
        Comparator<Pair<String, Integer>> comparePairs = new ComparingPairs();
        Collections.sort(wcList, comparePairs);
        return wcList;
    }

    public static void main(String[] args) {
        HashMap<String, Integer> wordCount;
        ArrayList<Pair<String, Integer>> wcList;
        Scanner input;
        if (args.length > 0) {
            try {
                input = new Scanner(new File(args[0]));
            } catch (FileNotFoundException e) {
                System.out.println("Cannot find file " + args[0]);
                System.out.println("Exiting program");
                input = new Scanner("");
                System.exit(0);
            }
        } else {
            input = new Scanner("Now is the time for all good men to come to the aid of the party." +
                    "How now brown cow? " + "Time flies like an arrow, but fruit flies like a banana." +
                    "To be or not to be, that is the question.");
        }
        //use countWords method to create HashTable
        wordCount = countWords(input);
        //use sortWordCounts to sort wordCount
        wcList = sortWordCounts(wordCount);
        //print the ordered pairs of wclist
        for (Pair<String, Integer> p : wcList) {
            System.out.println(p.getKey() + "\t" + p.getValue());
        }

    }
}
