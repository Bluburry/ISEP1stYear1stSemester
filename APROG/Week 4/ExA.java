import java.util.Scanner;

public class ExA {
    public static void main (String[] args){
        int num, sum;
        Scanner ler = new Scanner(System.in);
        num = ler.nextInt();
        sum = 0;
        while (num > 0) {
            if ((num % 10) % 2 == 0) {
                sum += num % 10;
            }
            num /= 10;
        }
        System.out.println(sum);
        ler.close();
    }
}