import java.util.Scanner;

public class ExG {
    public static void main(String[] args) {
        int num, count;
        Scanner ler = new Scanner(System.in);
        do {
            num = ler.nextInt();
        } while (num < 0);
        count = 0;
        for (int i = 1; i <= num; i++) {
            if (num % i == 0) {
                System.out.println(i);
                count++;
            }
        }
        System.out.println("(" + count + ")");
        ler.close();
    }
}
