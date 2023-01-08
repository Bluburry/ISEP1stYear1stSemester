import java.util.Scanner;

public class mExE {
    static Scanner read = new Scanner(System.in);
    public static String getPair(int repeat) {
        int pair1, pair2, count, print1 = 0, print2 = 0, countMax = 0;
        for (int i = 0; i < repeat; i++) {
            pair1 = read.nextInt();
            pair2 = read.nextInt();
            count = prepPairs(pair1, pair2);
            if (countMax <= count) {
                countMax = count;
                print1 = pair1;
                print2 = pair2;
            }
            
        }
        if (countMax > 0)
            return print1 + "/" + print2;
        else
            return "no results";
    }
    public static int prepPairs(int num1, int num2) {
        int big, small;
        if (num1 > num2) {
            big = num1;
            small = num2;
        }
        else {
            big = num2;
            small = num1;
        }
        for (int i = 0; i < (digitNum(big) - digitNum(small)); i++) {
            big /= 10;
        }
        return numOfCommon(big, small);
    }
    public static int digitNum(int num) {
        int result = 1;
        while (num > 9) {
            num /= 10;
            result++;
        }
        return result;
    }
    public static int numOfCommon(int num1, int num2) {
        int count = 0;
        do {
            if (num1 % 10 == num2 % 10)
                count++;
            num1 /= 10;
            num2 /= 10;
        } while (num1 > 0 && num2 > 0);
        return count;
    }
    public static void main(String[] args) {
        int numPair;
        numPair = read.nextInt();
        System.out.println(getPair(numPair));
        System. exit(0);
    }
}
