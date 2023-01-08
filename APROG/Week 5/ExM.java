import java.util.Scanner;

public class ExM {
    public static void main(String[] args) {
        int num, fiboSum, fibo1 = 0, fibo2 = 1;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        if (num >= 2) {
            System.out.println("0");
            System.out.println("1");
            for (int i = 2; i < num ; i++) {
                fiboSum = fibo1 + fibo2;
                System.out.println(fiboSum);
                fibo1 = fibo2;
                fibo2 = fiboSum;
            }
        } else if (num == 1) {
            System.out.println("0");
        }
        read.close();
    }
}