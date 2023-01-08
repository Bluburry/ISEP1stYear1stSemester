import java.util.Scanner;

public class aExL {
    static final int MAXNUM = 30;
    public static int readInputs(int[] array) {
        Scanner read = new Scanner(System.in);
        int increment = 0;
        do {
            array[increment] = read.nextInt();
        } while(array[increment++] >= 0);
        read.close();
        return increment - 1;
    }
    public static void printBiggestNeighbours(int max, int[] array) {
        for (int i = 1; i < max - 1; i++)
            if (array[i] > array[i - 1] && array[i] > array[i + 1]) 
                System.out.printf("%d\n", array[i]);
    }
    public static void main(String[] args) {
        int array[] = new int[MAXNUM];
        int numInserted = readInputs(array);
        //System.out.println("resultados:");
        printBiggestNeighbours(numInserted, array);
    }
}
