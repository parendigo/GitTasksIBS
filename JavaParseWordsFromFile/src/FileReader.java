import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

public class FileReader {

    // первая мапа куда все слова сохраняем
    private Map<Integer, String> words = new TreeMap<Integer, String>();

    // вторая мапа с сортировкой, без повторов, с частотой повторения слов
    private Map<String, Integer> wordFrequency = new TreeMap<String, Integer>(new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            int cmp = o1.toLowerCase().compareTo(o2.toLowerCase());
            if (cmp != 0) return cmp;

            return o1.compareTo(o2);
        }
    });

    // Кол-во слов
    private int count = 0;

    public FileReader(String file) throws IOException {
        try {
            File myObj = new File(file);
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                findWords(data);
            }
            myReader.close();
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        countEveryWordFrequency();
    }

    private void findWords (String data) {
        String[] words = data.split("[^a-zA-Z]");
        for (String a : words) {
            if (a.length() > 0) {
                this.words.put(count, a);
                count++;
            }
        }
    }
    public void printWordsWithCount () {
        this.words.forEach((a, b) -> System.out.printf("word is: %s, count is: %d\n ", b, a));
    }

    public void printWordFrequency (String word) {
        System.out.println(Collections.frequency(new ArrayList<String>(this.words.values()), word));
    }

    public void printWordFrequency (int count) {
        String word = this.words.get(count);
        System.out.println(Collections.frequency(new ArrayList<String>(this.words.values()), word));
    }

    // считаем частоту и заливаем во вторую мапу wordsFrequency
    private void countEveryWordFrequency () {
        for (Map.Entry<Integer, String> entry : this.words.entrySet()) {
            this.wordFrequency.put(entry.getValue(), Collections.frequency(new ArrayList<String>(this.words.values()), entry.getValue()));
        }
    }

    public void printALLWordsWithFrequency () {
        this.wordFrequency.forEach((a, b) -> System.out.printf("word: %s, frequency: %d\n", a, b));
    }

    public void printMostUsedWords () {
        int max = 0;
        for (Map.Entry<String, Integer> entry : this.wordFrequency.entrySet()) {
            if (entry.getValue() > max) {
                max = entry.getValue();
            }
        }
        for (Map.Entry<String, Integer> entry : this.wordFrequency.entrySet()) {
            if (entry.getValue() == 9)
                System.out.printf("Most used word: %s, count of usages: %d\n", entry.getKey(), entry.getValue());
        }
    }

    public void printCountOfWords () {
        System.out.println(this.count);
    }
}
