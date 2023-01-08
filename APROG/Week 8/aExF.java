import java.util.Scanner;

public class aExF {
    static final Scanner read = new Scanner(System.in);
    public static void readRates(double[][] array) {
        for (int i = 1; i < array.length; i++) {
            array[i][0] = read.nextDouble();
        }
    }
    public static void calcRates(double[][] array) {
        for (int i = 1; i < array.length; i++) {
            array[i][1] = array[i - 1][1] + array[i - 1][1] * array[i][0];
        }
    }
    public static void main(String[] args) {
        double finance[][] = new double[7][2];
        readRates(finance);
        finance[0][1] = read.nextDouble();
        calcRates(finance);
        System.out.printf("final value=%.2f%n", finance[6][1]);
    }
}
