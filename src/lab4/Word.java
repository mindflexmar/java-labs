import java.util.Arrays;

/**
 * Клас, що представляє слово як масив літер.
 */
public class Word {
    private final Letter[] letters;

    public Word(Letter[] letters) {
        this.letters = letters.clone();
    }

    public Letter[] getLetters() {
        return letters.clone();
    }

    public int length() {
        return letters.length;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Letter letter : letters) {
            sb.append(letter.getValue());
        }
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Word)) return false;
        Word word = (Word) o;
        return Arrays.equals(letters, word.letters);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(letters);
    }
}