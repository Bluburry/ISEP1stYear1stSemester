import java.util.Scanner;

public class aExD {
    static final Scanner read = new Scanner(System.in);
    public static void readGrades(int[] array) {
        for (int i = 0; i < array.length; i++) {
            array[i] = read.nextInt();
        }
    }
    public static void findGradeFrequency(int[] array) {
        int frequency[] = new int[21];
        for (int grades : array) {
            frequency[grades]++;
        }
        /* for (int i = 0; i < array.length; i++) {
            frequency[array[i]]++;
        } */
        displayValues(frequency);
    }
    public static void displayValues(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.printf("%d %d%n", i, array[i]);
        }
    }
    public static void main(String[] args) {
        int numStudents = read.nextInt();
        int grades[] = new int[numStudents];
        readGrades(grades);
        findGradeFrequency(grades);
    }
}