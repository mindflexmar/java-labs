import java.util.*;

/**
 * Лабораторна робота №6. Варіант 4.
 * Тема: Робота з колекціями.
 * C2=0=List
 * C3=1
 * Завдання: Власна List на основі однозв’язного списку.
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab6 {

    public static void main(String[] args) {
        try {
            // 1. Порожній конструктор
            LinkedMusicList<MusicTrack> list1 = new LinkedMusicList<>();
            System.out.println("1. Порожня колекція: " + list1);

            // 2. Конструктор з одним елементом
            Song song = new Song("Imagine", "John Lennon", 183, MusicStyle.ROCK, true);
            LinkedMusicList<MusicTrack> list2 = new LinkedMusicList<>(song);
            System.out.println("2. З одним елементом: " + list2);

            // 3. Конструктор зі стандартної колекції
            List<MusicTrack> standardList = Arrays.asList(
                    new Instrumental("Vivaldi Four Seasons", "Vivaldi", 600, MusicStyle.CLASSICAL, "скрипка"),
                    new Remix("Shape of You Remix", "Ed Sheeran", 230, MusicStyle.POP, true, "Ed Sheeran"),
                    new Song("Smells Like Teen Spirit", "Nirvana", 301, MusicStyle.ROCK, true)
            );
            LinkedMusicList<MusicTrack> list3 = new LinkedMusicList<>(standardList);
            System.out.println("3. Зі стандартної колекції: " + list3);

            // Демонстрація методів
            list3.add(1, song);
            System.out.println("Після add(1, song): " + list3);

            list3.remove(0);
            System.out.println("Після remove(0): " + list3);

            System.out.println("Містить Imagine: " + list3.contains(song));
            System.out.println("Розмір: " + list3.size());

        } catch (Exception e) {
            System.err.println("Помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }
}