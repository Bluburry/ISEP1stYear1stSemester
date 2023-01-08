import java.util.Scanner;
import java.lang.Math;

public class mExI {
    public static void checkFibo(int num) {
        final int maxNum = 20725;
        // devido a uma formula para encontrar um num de fibonacci utilizo esta variavel para definir o max de maneira a n/ ultrapassar o limite de int
        if (num < maxNum) {
            if (fiboRootCheck(5 * num * num + 4) || fiboRootCheck(5 * num * num - 4))
                System.out.println("is a Fibonacci number");
            else
                System.out.println("is not a Fibonacci number");
        }
        else {
            if (fiboRegCheck(num))
                System.out.println("is a Fibonacci number");
            else
                System.out.println("is not a Fibonacci number");
        }
    }
    public static boolean fiboRootCheck(int num) {
        int n = (int) Math.sqrt(num);
        return n * n == num;
    }
    public static boolean fiboRegCheck(int num) {
        int fiboNum = 0, helper1 = 1, helper2 = 0;
        do {
            fiboNum = helper1 + helper2;
            helper2 = helper1;
            helper1 = fiboNum;
        } while(fiboNum < num);
        return fiboNum == num;
    }
    public static void main(String[] args) {
        int num;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        checkFibo(num);
        read.close();
    }
}
