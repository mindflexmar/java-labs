import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Варіант 4.
 * C3 = 1 = String
 * C17 = 4 = У кожному реченні заданого тексту змінити місцями
 * перше та останнє слово, не змінюючи довжини речення.
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab2 {

    public static void main(String[] args) {
        Scanner scanner = null;

        try {
            scanner = new Scanner(System.in);
            System.out.println("Введіть текст (завершіть порожнім рядком):");

            StringBuilder inputBuilder = new StringBuilder();
            String line = "";

            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                inputBuilder.append(line).append("\n");
            }

            String text = inputBuilder.toString().trim();

            if (text.isEmpty()) {
                System.out.println("Помилка: введений текст порожній.");
                return;
            }

            String result = swapFirstAndLastWordInSentences(text);
            System.out.println("\nРезультат:");
            System.out.println(result);

        } catch (InputMismatchException e) {
            System.err.println("Помилка вводу: невірний формат даних.");
        } catch (NoSuchElementException e) {
            System.err.println("Помилка: неочікуваний кінець вводу.");
        } catch (IllegalArgumentException e) {
            System.err.println("Помилка обробки тексту: " + e.getMessage());
        } catch (NullPointerException e) {
            System.err.println("Помилка: текст не може бути null.");
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    /**
     * Замінює перше та останнє слово у кожному реченні тексту.
     *
     * @param text вихідний текст
     * @return змінений текст
     */
    public static String swapFirstAndLastWordInSentences(String text) {
        if (text == null) {
            throw new NullPointerException("Текст не може бути null.");
        }
        if (text.trim().isEmpty()) {
            throw new IllegalArgumentException("Текст не може бути порожнім.");
        }

        StringBuilder result = new StringBuilder();
        int length = text.length();
        int i = 0;

        while (i < length) {
            int sentenceStart = i;
            int sentenceEnd = -1;

            // Знаходимо кінець речення (. ! ?)
            while (i < length) {
                char c = text.charAt(i);
                if ((c == '.' || c == '!' || c == '?') &&
                        (i + 1 == length || Character.isWhitespace(text.charAt(i + 1)))) {
                    sentenceEnd = i + 1;
                    break;
                }
                i++;
            }

            if (sentenceEnd == -1) {
                sentenceEnd = length;
            }

            String sentence = text.substring(sentenceStart, sentenceEnd);
            String processedSentence;

            try {
                processedSentence = swapFirstAndLastWord(sentence);
            } catch (Exception e) {
                processedSentence = sentence; // Якщо помилка – лишаємо речення без змін
            }

            result.append(processedSentence);

            // Пропускаємо пробіли після кінця речення
            while (i < length && Character.isWhitespace(text.charAt(i))) {
                result.append(text.charAt(i));
                i++;
            }
        }

        return result.toString();
    }

    /**
     * Міняє місцями перше і останнє слово в одному реченні.
     *
     * @param sentence речення
     * @return речення з поміняними словами
     */
    private static String swapFirstAndLastWord(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return sentence;
        }

        String[] words = sentence.trim().split("\\s+");
        if (words.length < 2) {
            return sentence; // тільки одне слово — нічого не міняємо
        }

        // Міняємо місцями перше та останнє слово
        String temp = words[0];
        words[0] = words[words.length - 1];
        words[words.length - 1] = temp;

        // Відновлюємо розділові знаки в кінці
        String punctuation = "";
        if (sentence.matches(".*[.!?]$")) {
            punctuation = sentence.substring(sentence.length() - 1);
        }

        return String.join(" ", words) + punctuation;
    }
}
