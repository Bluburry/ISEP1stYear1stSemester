import java.util.Scanner;

public class ExC {
    public static void main (String[] args){
        int num, mul;
        boolean flagOdd;
        Scanner ler = new Scanner(System.in);
        num = ler.nextInt();
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
        ler.close();
    }
}
