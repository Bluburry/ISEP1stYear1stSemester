import java.util.Scanner;

public class ExK {
    public static void main(String[] args) {
        int num;
        boolean flagPrime;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        for (int i = 2; i <= num; i++) {
            flagPrime = true;
            for (int j = 2; j < i; j++) {
                if (i % j == 0) {
                    flagPrime = false;
                }
            }
            if (flagPrime == true) {
                System.out.println(i);
            }
        }
        read.close();
    }
}