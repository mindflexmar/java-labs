import java.util.*;

/**
 * Лабораторна робота №3. Варіант 4.
 * С4= Клас "Одяг", масив об'єктів, сортування, пошук.
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab3 {

    /**
     * Виконавчий метод.
     */
    public static void main(String[] args) {
        Clothing[] clothes = {
                new Clothing("Nike", "Футболка", "M", 899.99, "Чорний", true),
                new Clothing("Adidas", "Куртка", "L", 2999.50, "Синій", false),
                new Clothing("Zara", "Джинси", "S", 1499.00, "Чорний", true),
                new Clothing("H&M", "Светр", "XL", 799.00, "Сірий", true),
                new Clothing("Puma", "Шорти", "M", 649.99, "Білий", true),
                new Clothing("Nike", "Кросівки", "L", 3499.99, "Червоний", false)
        };

        System.out.println("Початковий масив:");
        printArray(clothes);

        Arrays.sort(clothes);
        System.out.println("\nВідсортовано за ціною (зростання):");
        printArray(clothes);

        Arrays.sort(clothes, Comparator.comparing(Clothing::getSize).reversed());
        System.out.println("\nВідсортовано за розміром (спадання):");
        printArray(clothes);

        Clothing target = new Clothing("Zara", "Джинси", "S", 1499.00, "Чорний", true);

        Clothing found = findClothing(clothes, target);
        if (found != null) {
            System.out.println("\nЗнайдено ідентичний об'єкт: " + found);
        } else {
            System.out.println("\nІдентичний об'єкт не знайдено.");
        }
    }

    /**
     * Виводить масив на екран.
     */
    private static void printArray(Clothing[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d. %s%n", i + 1, array[i]);
        }
    }

    /**
     * Шукає об'єкт, ідентичний заданому (за equals).
     */
    private static Clothing findClothing(Clothing[] array, Clothing target) {
        for (Clothing c : array) {
            if (c.equals(target)) {
                return c;
            }
        }
        return null;
    }
}