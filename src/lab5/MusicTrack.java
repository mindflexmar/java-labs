/**
 * Абстрактний клас музичного треку.
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public abstract class MusicTrack {
    protected final String title;
    protected final String artist;
    protected final int durationSeconds; // у секундах
    protected final MusicStyle style;

    public MusicTrack(String title, String artist, int durationSeconds, MusicStyle style) {
        if (title == null || title.trim().isEmpty()) {
            throw new IllegalArgumentException("Назва треку не може бути порожньою");
        }
        if (artist == null || artist.trim().isEmpty()) {
            throw new IllegalArgumentException("Виконавець не може бути порожнім");
        }
        if (durationSeconds <= 0) {
            throw new IllegalArgumentException("Тривалість має бути > 0");
        }
        this.title = title.trim();
        this.artist = artist.trim();
        this.durationSeconds = durationSeconds;
        this.style = style;
    }

    // --- Гетери ---
    public String getTitle() { return title; }
    public String getArtist() { return artist; }
    public int getDurationSeconds() { return durationSeconds; }
    public MusicStyle getStyle() { return style; }

    public String getFormattedDuration() {
        int mins = durationSeconds / 60;
        int secs = durationSeconds % 60;
        return String.format("%d:%02d", mins, secs);
    }

    @Override
    public String toString() {
        return String.format("%s - %s [%s, %s]", artist, title, style, getFormattedDuration());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MusicTrack)) return false;
        MusicTrack that = (MusicTrack) o;
        return durationSeconds == that.durationSeconds &&
                title.equals(that.title) &&
                artist.equals(that.artist) &&
                style == that.style;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + artist.hashCode();
        result = 31 * result + durationSeconds;
        result = 31 * result + style.hashCode();
        return result;
    }
}