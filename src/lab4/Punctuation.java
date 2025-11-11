/**
 * Клас, що представляє розділовий знак.
 */
public class Punctuation {
    private final char sign;

    public Punctuation(char sign) {
        this.sign = sign;
    }

    public char getSign() {
        return sign;
    }

    @Override
    public String toString() {
        return String.valueOf(sign);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Punctuation)) return false;
        Punctuation that = (Punctuation) o;
        return sign == that.sign;
    }

    @Override
    public int hashCode() {
        return Character.hashCode(sign);
    }
}