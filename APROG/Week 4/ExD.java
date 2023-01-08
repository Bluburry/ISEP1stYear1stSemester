import java.util.Scanner;

public class ExD {
    public static void main(String[] args) {
        int num, mul, check;
        boolean flagOdd;
        Scanner ler = new Scanner(System.in);
        do {
            check = ler.nextInt();
            if (check > 0) {
                num = check;
                mul = 1;
                flagOdd = false;
                while (num > 0) {
                    if ((num % 10) % 2 != 0) {
                        mul *= num % 10;
                        flagOdd = true;
                    }
                    num /= 10;
                }
                if (flagOdd == true) {
                    System.out.println(mul);
                } else {
                    System.out.println("no odd digits");
                }
            }
        } while(check > 0);
        ler.close();
    }
}