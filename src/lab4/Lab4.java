/**
 * Лабораторна робота №4. Варіант 4.
 * Завдання: Переробити ЛР №2 на класи Letter, Word, Punctuation, Sentence, Text.
 * Дія: Змінити місцями перше та останнє слово в кожному реченні.
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab4 {

    public static void main(String[] args) {
        try {
            String input = """
                    Hello   world!\tHow\tare you? This is a test.
                    Java is   great!  Fine, thanks.
                    """;

            System.out.println("Вхідний текст:");
            System.out.println(input);
            System.out.println();

            Text text = new Text(input);
            System.out.println("Після нормалізації пробілів:");
            System.out.println(text);
            System.out.println();

            processText(text);

            System.out.println("Результат:");
            System.out.println(text);

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Виконує дію з ЛР №2: міняє місцями перше та останнє слово в кожному реченні.
     */
    private static void processText(Text text) {
        for (Sentence sentence : text.getSentences()) {
            Word first = sentence.getFirstWord();
            Word last = sentence.getLastWord();

            if (first == null || last == null || first.equals(last)) {
                continue; // одне слово або порожнє
            }

            // Знаходимо індекси в elements
            java.util.List<Object> elements = sentence.getElements();
            int firstIdx = -1, lastIdx = -1;
            for (int i = 0; i < elements.size(); i++) {
                if (elements.get(i) == first) firstIdx = i;
                if (elements.get(i) == last) lastIdx = i;
            }

            if (firstIdx == -1 || lastIdx == -1) continue;

            // Міняємо місцями
            elements.set(firstIdx, last);
            elements.set(lastIdx, first);
        }
    }
}