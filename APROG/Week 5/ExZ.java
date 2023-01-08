import java.util.Scanner;

public class ExZ {
    public static void main(String[] args) {
        int num, count = 0, countEven = 0, odd = 0;
        Scanner read = new Scanner(System.in);
        do {
            num = read.nextInt();
        } while(num < 0);
        while (num > 0) {
            if ((num % 10) % 2 == 0) {
                countEven++;
            }
            else if (num % 10 > odd) {
                odd = num % 10;
            }
            count++;
            num /= 10;
        }
        System.out.printf("%.2f%%%n", (float) countEven / count * 100);
        if (odd == 0) {
            System.out.println("no odd digits");
        }
        else {
            System.out.println(odd);
        }
        read.close();
    }
    
}
