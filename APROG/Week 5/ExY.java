import java.util.Scanner;;

public class ExY {
    public static void main(String[] args) {
        int num;
        boolean div = false;
        Scanner read = new Scanner(System.in);
        do {
            num = read.nextInt();
        } while (num < 0);
        for (int i = 3; i <= num; i += 3) {
            if (num % i == 0) {
                System.out.println(i);
                div = true;
            }
        }
        if (div == false) {
            System.out.println("without dividers multiples of 3");
        }
        read.close();
    }
}
