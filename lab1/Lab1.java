//Варіант 5 С5=0, С7=5, С11=5

import java.util.Random;

public class Lab1 {

    public static void printMatrix(char[][] matrix){
        for(char[] row : matrix){
            for(char value: row){
                System.out.print(value+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        char a = '2';

        int rows = 5;
        int cols = 5;

        char[][] B = new char[rows][cols];
        char[][] C = new char[rows][cols];

        Random rand = new Random();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                B[i][j] = (char) (rand.nextInt(26) + 'a');
            }
        }
        System.out.println("Matrix B:");
        printMatrix(B);

        try {
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    C[i][j] = (char) (B[i][j] * a);
                }
            }
        } catch (Exception e) {
            System.out.println("Error during matrix C calculation:" + e.getMessage());
            return;
        }
        System.out.println("Matrix C:");
        printMatrix(C);

        int sum = 0;
        try {
            for (int i = 0; i < rows; i++) {
                if ((i + 1) % 2 == 1) {
                    char max = C[i][0];
                    for (int j = 1; j < cols; j++) {
                        if (C[i][j] > max) {
                            max = C[i][j];
                        }
                    }
                    sum += max;
                } else {
                    char min = C[i][0];
                    for (int j = 1; j < cols; j++) {
                        if (C[i][j] < min) {
                            min = C[i][j];
                        }
                    }
                    sum += min;
                }
            }
        } catch (Exception e) {
            System.out.println("Error during matrix C calculation:" + e.getMessage());
            return;
        }
        System.out.println("Matrix C calculation (max in odd rows + min in even rows): " + sum);
    }

}

