import java.util.Scanner;

public class aExO {
    static final Scanner read = new Scanner(System.in);
    public static void readValues(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = read.nextInt();
    }
    public static int calcQuadrant(int[][] matrix, int startI, int endI, int startJ, int endJ) {
        int sum = 0;
        for (int i = startI; i < endI; i++)
            for (int j = startJ; j < endJ; j++)
                sum += matrix[i][j];
        return sum;
    }
    public static void printQuadrant(int[][] matrix) {
        int start;
        if (matrix.length % 2 == 0)
            start = matrix.length / 2;
        else
            start = matrix.length / 2 + 1;
        System.out.printf("[%d]", calcQuadrant(matrix, 0, matrix.length / 2, 0, matrix.length / 2));
        System.out.printf("[%d]\n", calcQuadrant(matrix, 0, matrix.length / 2, start, matrix.length));
        System.out.printf("[%d]", calcQuadrant(matrix, start, matrix.length, 0, matrix.length / 2));
        System.out.printf("[%d]\n", calcQuadrant(matrix, start, matrix.length, start, matrix.length));
        /* int quadSum = 0;
        for (int i = 0; i < matrix.length / 2; i++)
            for (int j = 0; j < matrix.length / 2; j++)
                quadSum += matrix[i][j];
        System.out.printf("[%d]", quadSum);
        quadSum = 0;
        for (int i = 0; i < matrix.length / 2; i++)
            for (int j = start; j < matrix.length; j++)
                quadSum += matrix[i][j];
        System.out.printf("[%d]\n", quadSum);
        quadSum = 0;
        for (int i =  start; i < matrix.length; i++)
            for (int j = 0; j < matrix.length / 2; j++)
                quadSum += matrix[i][j];
        System.out.printf("[%d]", quadSum);
        quadSum = 0;
        for (int i = start; i < matrix.length; i++)
            for (int j = start; j < matrix.length; j++)
                quadSum += matrix[i][j];
        System.out.printf("[%d]\n", quadSum); */
    }
    public static void main(String[] args) {
        int num = read.nextInt();
        int matrix[][] = new int[num][num];
        readValues(matrix);
        //System.out.println("resultados:");
        printQuadrant(matrix);
    }
}
