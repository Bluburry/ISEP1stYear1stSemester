import java.util.Scanner;

public class ExB {
    public static void main (String[] args){
        int num, sum, check;
        Scanner ler = new Scanner(System.in);
        do {
            check = ler.nextInt();
            if (check > 0) {
                num = check;
                sum = 0;
                while (num > 0) {
                    if ((num % 10) % 2 == 0) {
                        sum += num % 10;
                    }
                    num /= 10;
                }
                System.out.println(sum);
            }
        } while(check > 0);
        ler.close();
    }
}
