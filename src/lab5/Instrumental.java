/**
 * Інструментальна композиція.
 */
public class Instrumental extends MusicTrack {
    private final String mainInstrument;

    public Instrumental(String title, String artist, int durationSeconds, MusicStyle style, String mainInstrument) {
        super(title, artist, durationSeconds, style);
        this.mainInstrument = mainInstrument;
    }

    public String getMainInstrument() { return mainInstrument; }

    @Override
    public String toString() {
        return super.toString() + " [інструмент: " + mainInstrument + "]";
    }
}