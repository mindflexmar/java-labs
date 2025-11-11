import java.util.Scanner;

/** Варіант 4.
 * C3 = 1 = String
 * C17 = 4 = В кожному реченні заданого тексту змінити місцями перше та останнє слово, не змінивши довжини речення.
 */
public class Lab2 {
    public static void main(String[] args) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(System.in);
            System.out.println("Введіть текст (завершіть порожнім рядком):");

            StringBuilder inputBuilder = new StringBuilder();
            String line;
            while (scanner.hasNextLine()) {
                line = scanner.nextLine();
                if (line.trim().isEmpty()) {
                    break;
                }
                inputBuilder.append(line).append("\n");
            }

            String text = inputBuilder.toString().trim();
            if (text.isEmpty()) {
                System.out.println("Помилка: Введений текст порожній.");
                return;
            }

            String result = swapFirstAndLastWordInSentences(text);
            System.out.println("\nРезультат:");
            System.out.println(result);

        } catch (Exception e) {
            System.err.println("Виникла неочікувана помилка: " + e.getMessage());
            e.printStackTrace();
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }

    public static String swapFirstAndLastWordInSentences(String text) {
        if (text == null) {
            throw new NullPointerException("Текст не може бути null");
        }

        StringBuilder result = new StringBuilder();
        int length = text.length();
        int i = 0;

        while (i < length) {
            int sentenceStart = i;

            // Знаходимо кінець речення: . ! ? + пробіл або кінець тексту
            int sentenceEnd = -1;
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
            String processed = swapFirstAndLastWord(sentence);
            result.append(processed);

            // Пропускаємо пробіли після розділового знака
            while (i < length && Character.isWhitespace(text.charAt(i))) {
                i++;
            }
        }

        return result.toString();
    }


    private static String swapFirstAndLastWord(String sentence) {
        if (sentence == null || sentence.trim().isEmpty()) {
            return sentence;
        }

        char[] chars = sentence.toCharArray();
        int len = chars.length;

        // Знаходимо початок першого слова
        int firstStart = 0;
        while (firstStart < len && !isWordChar(chars[firstStart])) {
            firstStart++;
        }
        if (firstStart == len) return sentence;

        int firstEnd = firstStart;
        while (firstEnd < len && isWordChar(chars[firstEnd])) {
            firstEnd++;
        }

        // Знаходимо кінець останнього слова
        int lastEnd = len - 1;
        while (lastEnd >= 0 && !isWordChar(chars[lastEnd])) {
            lastEnd--;
        }
        if (lastEnd < 0) return sentence;

        int lastStart = lastEnd;
        while (lastStart >= 0 && isWordChar(chars[lastStart])) {
            lastStart--;
        }
        lastStart++;

        // Якщо одне слово — не міняємо
        if (firstStart == lastStart && firstEnd == lastEnd + 1) {
            return sentence;
        }

        char[] result = chars.clone();

        int i = firstStart;
        int j = lastStart;
        while (i < firstEnd && j <= lastEnd) {
            result[i] = chars[j];
            result[j] = chars[firstStart + (j - lastStart)];
            i++;
            j++;
        }

        // Обробка різної довжини слів
        if (i < firstEnd) {
            for (int k = i; k < firstEnd; k++) {
                result[k] = chars[lastStart + (k - i)];
            }
        } else if (j <= lastEnd) {
            for (int k = j; k <= lastEnd; k++) {
                result[firstStart + (k - j)] = chars[k];
            }
        }

        return new String(result);
    }


    private static boolean isWordChar(char c) {
        return Character.isLetterOrDigit(c) || c == '\'';
    }
}