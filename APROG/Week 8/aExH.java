import java.util.Scanner;

public class aExH {
    static final Scanner read = new Scanner(System.in);
    public static void readNums(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = read.nextInt();
        }
    }
    public static void leftRightMenu(int[] array) {
        String option = read.next();
        while (!option.equals("exit")) {
            switch (option) {
                case "left":
                    shiftLeft(array);
                    printArray(array);
                    break;
                case "right":
                    shiftRight(array);
                    printArray(array);
                    break;
                default:
                    break;
            }
            option = read.next();
        }
    }
    public static void shiftRight(int[] array) {
        int swap = array[array.length - 1];
        for (int i = array.length - 1; i > 0; i--) {
            array[i] = array[i - 1];
        }
        array[0] = swap;
    }
    public static void shiftLeft(int[] array) {
        int swap = array[0];
        for (int i = 0; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }
        array[array.length - 1] = swap;
    }
    public static void printArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("[%d]", array[i]);
        }
        System.out.printf("%n");
    }
    public static void main(String[] args) {
        int num = read.nextInt();
        int array[] = new int[num];
        readNums(array);
        leftRightMenu(array);
    }
}
