import java.util.Scanner;

public class aExA {
    static final Scanner read = new Scanner(System.in);
    public static void readNums(int[] array, int num) {
        for (int i = 0; i < num; i++) {
            array[i] = read.nextInt();
        }
    }
    public static double avgNums(int[] array) {
        int sum = 0;
        for (int num : array) {
            sum += num;
        }
        /* for (int i = 0; i < array.length; i++) {
            sum += array[i];
        } */
        return (double) sum / array.length;
    }
    public static int numsUnderValue(int[] array, int value) {
        int count = 0;
        for (int num : array) {
            if (num < value)
                count++;
        }
        /* for (int i = 0; i < array.length; i++) {
            if (array[i] < value) 
                count++;
        } */
        return count;
    }
    public static void main(String[] args) {
        final int PASSSCORE = 10;
        int N = read.nextInt();
        int examScores[] = new int[N];
        readNums(examScores, N);
        System.out.printf("average=%.1f%n", avgNums(examScores));
        System.out.printf("failures=%d%n", numsUnderValue(examScores, PASSSCORE));
        System.exit(0);
    }
}
