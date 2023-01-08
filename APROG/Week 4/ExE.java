import java.util.Scanner;
import java.lang.Math;

public class ExE {
    public static void main(String[] args) {
        int numOct, numDec, count, check;
        Scanner ler = new Scanner(System.in);
        do {
            check = ler.nextInt();
            if (check > 0) {
                numOct = check;
                numDec = 0;
                count = 0;
                while (numOct > 0) {
                    numDec += (numOct % 10) * Math.pow(8, count);
                    count++;
                    numOct /= 10;
                }
                System.out.println(numDec);
            }
        } while(check > 0);
        ler.close();
    }
}
