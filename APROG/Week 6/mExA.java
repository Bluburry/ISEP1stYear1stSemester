import java.util.Scanner;

public class mExA {
    static Scanner read = new Scanner(System.in);
    public static double averageNum(int max) {
        int n, sum = 0, count = 0;
        final int K = 5;
        do {
            n = read.nextInt();
            if (digitNum(n) < max && count < K) {
                sum += n;
                count++;
            }
        } while (digitNum(n) < max && count < K);
        if (count > 0) 
            return (double) sum/count;
        else
            return 0;
    }
    public static int digitNum(int num) {
        int result = 1;
        while (num > 9) {
            num /= 10;
            result++;
        }
        return result;
    }
    public static void main(String[] args) {
        int n;
        n = read.nextInt();
        System.out.printf("%.2f%n", averageNum(n));
        System. exit(0); 
    }
}
