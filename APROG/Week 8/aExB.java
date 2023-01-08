import java.util.Scanner;

public class aExB {
    static final Scanner read = new Scanner(System.in);
    public static int readNums(int[] array) {
        int count, num = read.nextInt();
        for (count = 0; num > 0; count++) {
            array[count] = num;
            num = read.nextInt();
        }
        return count;
    }
    public static int lowestNumInArray(int[] array, int num) {
        int lowest = array[0];
        for (int i = 1; i < num; i++) {
            if (lowest > array[i])
                lowest = array[i];
        }
        return lowest;
    }
    public static int numOfOccurencesOfValInArray(int[] array, int num, int val) {
        int count = 0;
        for (int i = 0; i < num; i++) {
            if (array[i] == val)
                count++;
        }
        return count;
    }
    public static void main(String[] args) {
        int array[] = new int[10];
        int numInserted = readNums(array);
        if (numInserted > 0) {
            int lowest = lowestNumInArray(array, numInserted);
            System.out.println("min=" + lowest);
            System.out.println("occurrences=" + numOfOccurencesOfValInArray(array, numInserted, lowest));
        }
    }
}