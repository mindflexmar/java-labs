import java.util.ArrayList;
import java.util.List;

/**
 * Клас, що представляє речення: слова + розділові знаки.
 */
public class Sentence {
    private final List<Object> elements; // Word або Punctuation

    public Sentence() {
        this.elements = new ArrayList<>();
    }

    public void addWord(Word word) {
        elements.add(word);
    }

    public void addPunctuation(Punctuation punctuation) {
        elements.add(punctuation);
    }

    public List<Object> getElements() {
        return new ArrayList<>(elements);
    }

    public int getWordCount() {
        int count = 0;
        for (Object el : elements) {
            if (el instanceof Word) count++;
        }
        return count;
    }

    public Word getFirstWord() {
        for (Object el : elements) {
            if (el instanceof Word) return (Word) el;
        }
        return null;
    }

    public Word getLastWord() {
        for (int i = elements.size() - 1; i >= 0; i--) {
            Object el = elements.get(i);
            if (el instanceof Word) return (Word) el;
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Object el : elements) {
            sb.append(el.toString());
        }
        return sb.toString();
    }
}