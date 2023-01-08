import java.util.Scanner;

public class mExH {
    public static String findPalindrome() {
        int num, count = 0, threshold = 5;
        boolean pal = false;
        Scanner read = new Scanner(System.in);
        do {
            num = read.nextInt();
            pal = checkPalindrome(num);
            count++;
        } while(count < threshold && pal == false);
        read.close();
        if (pal != false) 
            return "palindrome";
        else
            return "attempts exceeded";
    }
    public static boolean checkPalindrome(int num) {
        int val, sum;
        sum = num % 10;
        val = num / 10;
        while(val > 0) {
            sum *= 10;
            sum += val % 10;
            val /= 10;
        }
        return sum == num;
    }
    public static void main(String[] args) {
        System.out.println(findPalindrome());
        System. exit(0);
    }
}
