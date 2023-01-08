import java.util.Scanner;
import java.lang.Math;

public class mExZ {
    public static int countFullTimePrimes(int num1, int num2) {
        int count = 0;
        if (num1 > num2) {
            int swap = num1;
            num1 = num2;
            num2 = swap;
        }
        for(int i = num1; i <= num2; i++) {
            if(numIsFullTimePrime(i)){
                System.out.println(i);
                count++;
            }
        }
        return count;
    }
    public static boolean numIsFullTimePrime(int num) {
        boolean flag;
        flag = numIsPrime(num);
        for(int i = shiftNum(num); i != num && flag == true; i = shiftNum(i)) {
            flag = numIsPrime(i);
        } 
        /* if(!numIsPrime(num))
            return false;
        for(int i = shiftNum(num); i != num; i = shiftNum(i)) {
            if(!numIsPrime(i))
                return false;
        } */
        return flag;
    }
    public static boolean numIsPrime(int num) {
        boolean flag = true;
        for(int i = 2; i < Math.sqrt(num) && flag == true; i++) {
            if(num % i == 0)
                flag = false;
        }
        /* for(int i = 2; i < Math.sqrt(num); i++) {
            if(num % i == 0)
                return false;
        } */
        return flag;
    }
    public static int shiftNum(int num) {
        return (int) ((num % 10) * Math.pow(10, numOfDigits(num) - 1) + num / 10);
    }
    public static int numOfDigits(int num) {
        int result = 1;
        while (num > 9) {
            num /= 10;
            result++;
        }
        return result;
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int num1 = read.nextInt();
        int num2 = read.nextInt();
        System.out.printf("(%d)%n", countFullTimePrimes(num1, num2));
        read.close();
        System.exit(0);
    }
}
