import java.util.Scanner;

public class mExD {
    public static int combinations(int m, int n) {
        return (factorial(m)/(factorial(n)*factorial(m-n)));
    }
    public static int permutations(int m, int n) {
        return (factorial(m)/(factorial(m-n)));
    }
    public static int factorial(int f) {
        int result = 1;
        for (int i = f; i > 1; i--) {
            result *= i;
        }
        /* tambem aceitavel, mas nao explicito
        for (; f > 1; f--) {
            result *= f;
        }
        */
        /*
        while (f > 1) {
            result *= f;
            f--;
        }
        */
        return result;
    }
    public static void main(String[] args) {
        int m, n;
        Scanner read = new Scanner(System.in);
        m = read.nextInt();
        n = read.nextInt();
        if (m >= n) {
            System.out.printf("C(%d,%d)=%d%n", m, n, combinations(m, n));
            System.out.printf("P(%d,%d)=%d%n", m, n, permutations(m, n));
        }
        read.close();
    }
}
