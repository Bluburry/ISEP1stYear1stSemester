import java.util.Scanner;

public class ExF {
    public static void main(String[] args) {
        int num, num2, checker;
        Scanner ler = new Scanner(System.in);
        do {
            num = ler.nextInt();
        } while (num < 0);
        num2 = num;
        checker = 0;
        while (num2 > 0) {
            checker = checker * 10 + num2 % 10;
            num2 /= 10;
        }
        if (num == checker){
            System.out.println("palindrome");
        } else {
            System.out.println("not palindrome");
        }
        ler.close();
    }
}
