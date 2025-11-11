/**
 * Ремікс (нащадок Song).
 */
public class Remix extends Song {
    private final String originalArtist;

    public Remix(String title, String artist, int durationSeconds, MusicStyle style,
                 boolean hasVocals, String originalArtist) {
        super(title, artist, durationSeconds, style, hasVocals);
        this.originalArtist = originalArtist;
    }

    public String getOriginalArtist() { return originalArtist; }

    @Override
    public String toString() {
        return super.toString() + " [оригінал: " + originalArtist + "]";
    }
}