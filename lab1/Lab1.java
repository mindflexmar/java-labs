import java.util.Random;

/**
 * Лабораторна робота №1. Варіант 4.
 * Тема: Масиви в мові програмування Java.
 * Завдання:
 *   C5  = 4 = C = A × B (матричний добуток)
 *   C7  = 4 = long
 *   C11 = 4 = Сума найбільших в парних рядках + найменших в непарних
 *
 * @author Марія Зозуля
 * @group ІС-34
 */
public class Lab1 {

    private static final Random RANDOM = new Random();

    /**
     * Виконавчий метод.
     */
    public static void main(String[] args) {
        try {
            // --- Параметри матриць ---
            int rowsA = 3, colsA = 4;
            int rowsB = 4, colsB = 3;

            long[][] A = createMatrix(rowsA, colsA);
            long[][] B = createMatrix(rowsB, colsB);

            System.out.println("Матриця A (" + rowsA + "x" + colsA + "):");
            printMatrix(A);

            System.out.println("\nМатриця B (" + rowsB + "x" + colsB + "):");
            printMatrix(B);

            // --- C = A × B (матричний добуток) ---
            long[][] C = multiplyMatrices(A, B);
            System.out.println("\nРезультат C = A × B (" + C.length + "x" + C[0].length + "):");
            printMatrix(C);

            // --- Дія з матрицею C (C11=4) ---
            long result = calculateSpecialSum(C);
            System.out.println("\nСума: max(парні рядки) + min(непарні рядки) = " + result);

        } catch (IllegalArgumentException e) {
            System.err.println("Помилка розмірів матриць: " + e.getMessage());
        } catch (Exception e) {
            System.err.println("Невідома помилка: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * Створює матрицю розміром rows x cols з випадковими значеннями long.
     */
    private static long[][] createMatrix(int rows, int cols) {
        if (rows <= 0 || cols <= 0) {
            throw new IllegalArgumentException("Розміри матриці мають бути > 0");
        }
        long[][] matrix = new long[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                matrix[i][j] = RANDOM.nextLong(101) - 50; // [-50, 50]
            }
        }
        return matrix;
    }

    /**
     * Матричний добуток A × B.
     * @throws IllegalArgumentException якщо colsA != rowsB
     */
    private static long[][] multiplyMatrices(long[][] A, long[][] B) {
        int rowsA = A.length;
        int colsA = A[0].length;
        int colsB = B[0].length;

        if (colsA != B.length) {
            throw new IllegalArgumentException(
                    "Кількість стовпців A (" + colsA + ") != кількість рядків B (" + B.length + ")"
            );
        }

        long[][] C = new long[rowsA][colsB];
        for (int i = 0; i < rowsA; i++) {
            for (int j = 0; j < colsB; j++) {
                long sum = 0;
                for (int k = 0; k < colsA; k++) {
                    sum += A[i][k] * B[k][j];
                }
                C[i][j] = sum;
            }
        }
        return C;
    }

    /**
     * C11=4: Сума найбільших елементів в парних рядках + найменших в непарних.
     */
    private static long calculateSpecialSum(long[][] matrix) {
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0) {
            throw new IllegalArgumentException("Матриця порожня");
        }

        long sum = 0;
        for (int i = 0; i < matrix.length; i++) {
            long extreme = matrix[i][0];
            for (int j = 1; j < matrix[i].length; j++) {
                if (i % 2 == 0) { // парний рядок (0, 2, 4...)
                    if (matrix[i][j] > extreme) extreme = matrix[i][j];
                } else { // непарний рядок (1, 3, 5...)
                    if (matrix[i][j] < extreme) extreme = matrix[i][j];
                }
            }
            sum += extreme;
        }
        return sum;
    }

    /**
     * Виводить матрицю на екран.
     */
    private static void printMatrix(long[][] matrix) {
        for (long[] row : matrix) {
            for (int j = 0; j < row.length; j++) {
                System.out.printf("%6d", row[j]);
            }
            System.out.println();
        }
    }
}