import java.util.Scanner;
import java.lang.Math;

public class mExG {
    public static boolean armstrongNum(int num) {
        int value, count, sum = 0;
        count = digitNum(num);
        value = num;
        do {
            sum += Math.pow(value % 10, count);
            value /= 10;
        } while (value > 0);
        return sum == num;
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
        int num;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        for(int i = 0; i <= num; i++) {
            if (armstrongNum(i))
                System.out.println(i);
        }
        read.close();
        System. exit(0);
    }
}
