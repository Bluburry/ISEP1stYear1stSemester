import java.util.Scanner;

public class ExL {
    public static void main(String[] args) {
        int num, sum, numToCheck = 5;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        for (int i = 0; i < num; i++) {
            do {
                numToCheck++;
                sum = 0;
                for (int j = 1; j <= (numToCheck / 2); j++) {
                    if (numToCheck % j == 0) {
                        sum += j;
                    }
                }
            } while (sum != numToCheck);
            System.out.println(sum);
        }
        read.close();
    }
}