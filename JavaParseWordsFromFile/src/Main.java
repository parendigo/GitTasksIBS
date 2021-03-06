import java.io.IOException;

public class Main {
    public static void main (String[] args) throws IOException {

        FileReader fileReader = new FileReader("fileExample.txt");

        // Выводим все слова с нумерацией
        System.out.println("Выводим все слова с нумерацией");
        fileReader.printWordsWithCount();
        System.out.println();

        // Печатаем частоту слова по значению в мапе
        System.out.println("Печатаем частоту слова по значению в мапе");
        fileReader.printWordFrequency("vvw");
        System.out.println();

        // Печатаем частоту слова по ключу (порядковому номеру)
        System.out.println("Печатаем частоту слова по ключу (порядковому номеру)");
        fileReader.printWordFrequency(2);
        System.out.println();

        // Печатаем количество всех слов
        System.out.println("Печатаем количество всех слов");
        fileReader.printCountOfWords();
        System.out.println();

        // Печатаем все слова, отсортированные с учетом регистра, без повторений с их частотой
        System.out.println("Печатаем все слова, отсортированные с учетом регистра, без повторений с их частотой");
        fileReader.printALLWordsWithFrequency();
        System.out.println();

        // Печатаем самые часто повторяющиеся слова
        System.out.println("Печатаем самые часто повторяющиеся слова");
        fileReader.printMostUsedWords();
        System.out.println();
    }
}
