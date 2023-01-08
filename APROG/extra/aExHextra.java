import java.util.Scanner;

public class aExHextra {
    static final Scanner read = new Scanner(System.in);
    public static void readNums(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = read.nextInt();
        }
    }
    public static void leftRightMenu(int[] array) {
        String option = read.next();
        int numOfshifts;
        while (!option.equals("exit")) {
            switch (option) {
                case "left":
                    numOfshifts = read.nextInt();
                    shiftLeft(array, numOfshifts);
                    printArray(array);
                    break;
                case "right":
                    numOfshifts = read.nextInt();
                    shiftRight(array, numOfshifts);
                    printArray(array);
                    break;
                default:
                    break;
            }
            option = read.next();
        }
    }
    public static void shiftRight(int[] array, int numOfshifts) {
        if (numOfshifts % array.length > array.length / 2) {
            shiftLeft(array, (array.length - (numOfshifts % array.length)));
        }
        else if (numOfshifts % array.length > 0) {
            int arraySwap[] = new int[numOfshifts % array.length];
            for (int i = 0; i < numOfshifts % array.length; i++) {
                arraySwap[i] = array[array.length - i - 1];
            }
            for (int i = array.length - 1; i >= numOfshifts % array.length; i--) {
                array[i] = array[i - numOfshifts % array.length];
            }
            for (int i = 0; i < numOfshifts % array.length; i++) {
                array[i] = arraySwap[numOfshifts % array.length - i - 1];
            }
        }
    }
    public static void shiftLeft(int[] array, int numOfshifts) {
        if (numOfshifts % array.length > array.length / 2) {
            shiftRight(array, (array.length - (numOfshifts % array.length)));
        }
        else if (numOfshifts % array.length > 0) {
            int arraySwap[] = new int[numOfshifts % array.length];
            for (int i = 0; i < numOfshifts % array.length; i++) {
                arraySwap[i] = array[i];
            }
            for (int i = 0; i < array.length - numOfshifts % array.length; i++) {
                array[i] = array[i + numOfshifts % array.length];
            }
            for (int i = array.length - 1, j = numOfshifts % array.length - 1; j >= 0; i--, j--) {
                array[i] = arraySwap[j];
            }
        }
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
