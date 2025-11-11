import java.util.*;

/**
 * Клас альбому — масив треків.
 */
public class Album {
    private final List<MusicTrack> tracks;
    private final String albumName;

    public Album(String albumName) {
        this.albumName = albumName;
        this.tracks = new ArrayList<>();
    }

    public void addTrack(MusicTrack track) {
        if (track == null) throw new IllegalArgumentException("Трек не може бути null");
        tracks.add(track);
    }

    public List<MusicTrack> getTracks() {
        return new ArrayList<>(tracks);
    }

    /** Порахувати тривалість альбому */
    public int getTotalDuration() {
        return tracks.stream().mapToInt(MusicTrack::getDurationSeconds).sum();
    }

    public String getFormattedTotalDuration() {
        int total = getTotalDuration();
        int hours = total / 3600;
        int mins = (total % 3600) / 60;
        int secs = total % 60;
        return String.format("%d:%02d:%02d", hours, mins, secs);
    }

    /** Перестановка за стилем */
    public void sortByStyle() {
        tracks.sort(Comparator.comparing(MusicTrack::getStyle));
    }

    /** Знайти трек у діапазоні довжини */
    public List<MusicTrack> findByDurationRange(int minSec, int maxSec) {
        if (minSec > maxSec) throw new IllegalArgumentException("minSec > maxSec");
        return tracks.stream()
                .filter(t -> t.getDurationSeconds() >= minSec && t.getDurationSeconds() <= maxSec)
                .toList();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Альбом: ").append(albumName).append("\n");
        sb.append("Тривалість: ").append(getFormattedTotalDuration()).append("\n");
        sb.append("Треки:\n");
        for (int i = 0; i < tracks.size(); i++) {
            sb.append(String.format("%2d. %s%n", i + 1, tracks.get(i)));
        }
        return sb.toString();
    }
}