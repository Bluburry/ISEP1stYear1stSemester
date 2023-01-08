import java.util.Scanner;

public class ExI {
    public static void main(String[] args) {
        int num, big, small, mult, count;
        Scanner ler = new Scanner(System.in);
        do {
            num = ler.nextInt();
        } while (num < 1);
        big = num;
        do {
            num = ler.nextInt();
        } while (num < 1);
        if (num > big) {
            small = big;
            big = num;
        } else {
            small = num;
        }
        mult = big;
        count = 2;
        while (mult % big != 0 || mult % small != 0) {
            mult *= count;
        }
        System.out.println(mult);
        ler.close();
    }
}
