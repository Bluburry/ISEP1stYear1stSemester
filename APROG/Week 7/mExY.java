import java.util.Scanner;

public class mExY {
    public static int readPositiveValue(Scanner read) {
        int num;
        do {
            num = read.nextInt();
        } while(num < 1);
        return num;
    }
    public static void showTablesInRange(int num1, int num2) {
        if (num1 > num2) {
            int swap = num1;
            num1 = num2;
            num2 = swap;
        }
        for(int i = num1; i <= num2; i++) {
            showTableOfNumber(i);
        }
    }
    public static void showTableOfNumber(int num) {
        System.out.println("Multiplication table of " + num);
        for(int i = 1; i <= 10; i++) {
            System.out.printf("%d x %d = %d%n", num, i, num * i);
        }
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int num1 = readPositiveValue(read);
        int num2 = readPositiveValue(read);
        showTablesInRange(num1, num2);
        read.close();
        System.exit(0);
    }
}
