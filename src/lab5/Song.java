/**
 * Пісня (нащадок MusicTrack).
 */
public class Song extends MusicTrack {
    private final boolean hasVocals;

    public Song(String title, String artist, int durationSeconds, MusicStyle style, boolean hasVocals) {
        super(title, artist, durationSeconds, style);
        this.hasVocals = hasVocals;
    }

    public boolean hasVocals() { return hasVocals; }

    @Override
    public String toString() {
        return super.toString() + (hasVocals ? " [вокал]" : " [інструментал]");
    }
}