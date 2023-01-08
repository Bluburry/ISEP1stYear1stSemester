import java.util.Scanner;

public class aExG {
    static final Scanner read = new Scanner(System.in);
    public static void readNums(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = read.nextInt();
        }
    }
    public static void printReverse(int[] array) {
        for (int i = array.length - 1; i >= 0; i--) {
            System.out.println(array[i]);
        }
    }
    public static void main(String[] args) {
        int num = read.nextInt();
        int array[] = new int[num];
        readNums(array);
        printReverse(array);
    }
}
