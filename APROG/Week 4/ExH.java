import java.util.Scanner;
import java.lang.Math;

public class ExH {
    public static void main(String[] args) {
        int num, div;
        boolean flagPrime = true;
        Scanner ler = new Scanner(System.in);
        num = ler.nextInt();
        if (num > 1) {
            div = 2;
            while (flagPrime == true && div <= Math.sqrt(num)) {
                if (num % div == 0) {
                    flagPrime = false;
                } else {
                    div++;
                }
            }
        } else {
            flagPrime = false;
        }
        if (flagPrime == true) {
            System.out.println("prime");
        } else {
            System.out.println("not prime");
        }
        ler.close();
    }
}
