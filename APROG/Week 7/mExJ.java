import java.util.Scanner;

public class mExJ {
    public static int tripDiv(int num) {
        int count = 0, numDiv = num / 3;
        if (num % 3 != 0)
            numDiv++;
        for (int i = num - 2; i >= numDiv; i--) {
            count += tripSum(i, num);
        }
        return count;
    }
    public static int tripSum(int numStar, int numMax) {
        int count = 0, num = numMax - numStar;
        for (int i = 1; i <= num / 2; i++) {
            if (num - i <= numStar) {
                System.out.printf("%d + %d + %d%n", numStar, num - i, i);
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int num;
        num = read.nextInt();
        System.out.println("triples=" + tripDiv(num));
        read.close();
        System.exit(0);
    }
}
