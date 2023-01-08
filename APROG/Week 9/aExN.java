import java.util.Scanner;

public class aExN {
    static final Scanner read = new Scanner(System.in);
    public static void readValues(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = read.nextInt();
    }
    public static void printDiagonal(int[][] matrix) {
        for (int j = matrix.length - 1; j >= 0; j--) {
            for (int i = 0, j2 = j; j2 < matrix.length; i++, j2++)
                System.out.printf("[%d]", matrix[i][j2]);
            System.out.printf("\n");
        }
        for (int i =  1; i < matrix.length; i++) {
            for (int i2 = i, j = 0; i2 < matrix.length; i2++, j++)
                System.out.printf("[%d]", matrix[i2][j]);
            System.out.printf("\n");
        }
    }
    public static void main(String[] args) {
        int num = read.nextInt();
        int matrix[][] = new int[num][num];
        readValues(matrix);
        //System.out.println("resultados:");
        printDiagonal(matrix);
    }
}