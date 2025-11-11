import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Клас, що представляє текст як масив речень.
 * Замінює послідовність пробілів/табуляцій одним пробілом.
 */
public class Text {
    private final List<Sentence> sentences;

    public Text(String input) {
        this.sentences = new ArrayList<>();
        parseText(normalizeWhitespace(input));
    }

    private String normalizeWhitespace(String input) {
        return input.replaceAll("[\\s\\t]+", " ").trim();
    }

    private void parseText(String text) {
        String[] sentenceStrings = text.split("(?<=[.!?])\\s+");
        for (String s : sentenceStrings) {
            if (s.trim().isEmpty()) continue;
            Sentence sentence = parseSentence(s);
            sentences.add(sentence);
        }
    }

    private Sentence parseSentence(String s) {
        Sentence sentence = new Sentence();
        String[] parts = s.split("(?<=\\p{Punct})|(?=\\p{Punct})|\\s+");

        for (String part : parts) {
            if (part.isEmpty()) continue;
            if (part.matches("\\p{Punct}+")) {
                for (char c : part.toCharArray()) {
                    sentence.addPunctuation(new Punctuation(c));
                }
            } else if (part.matches("\\s+")) {
                // пропускаємо (вже нормалізовано)
            } else {
                Letter[] letters = part.chars()
                        .mapToObj(c -> new Letter((char) c))
                        .toArray(Letter[]::new);
                sentence.addWord(new Word(letters));
            }
        }
        return sentence;
    }

    public List<Sentence> getSentences() {
        return new ArrayList<>(sentences);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < sentences.size(); i++) {
            sb.append(sentences.get(i));
            if (i < sentences.size() - 1) sb.append(" ");
        }
        return sb.toString();
    }
}