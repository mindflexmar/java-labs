/**
 * Лабораторна робота №5. Варіант 4.
 * Тема: Наслідування та поліморфізм.
 * Завдання: Ієрархія музичних композицій, альбом, сортування, пошук.
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab5 {

    public static void main(String[] args) {
        try {
            Album album = new Album("Greatest Hits 2025");

            // Додаємо треки (поліморфізм)
            album.addTrack(new Song("Bohemian Rhapsody", "Queen", 355, MusicStyle.ROCK, true));
            album.addTrack(new Instrumental("Clair de Lune", "Debussy", 300, MusicStyle.CLASSICAL, "фортепіано"));
            album.addTrack(new Song("Billie Jean", "Michael Jackson", 294, MusicStyle.POP, true));
            album.addTrack(new Remix("Uptown Funk Remix", "Mark Ronson", 270, MusicStyle.POP, true, "Bruno Mars"));
            album.addTrack(new Song("Stairway to Heaven", "Led Zeppelin", 482, MusicStyle.ROCK, true));
            album.addTrack(new Instrumental("Take Five", "Dave Brubeck", 323, MusicStyle.JAZZ, "саксофон"));

            System.out.println("=== Початковий альбом ===");
            System.out.println(album);

            // Сортування за стилем
            album.sortByStyle();
            System.out.println("\n=== Після сортування за стилем ===");
            System.out.println(album);

            // Пошук у діапазоні 4:00 - 5:00 (240–300 сек)
            var found = album.findByDurationRange(240, 300);
            System.out.println("\n=== Знайдено у діапазоні 4:00–5:00 ===");
            found.forEach(t -> System.out.println(" - " + t));

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}