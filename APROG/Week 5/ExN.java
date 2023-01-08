import java.util.Scanner;

public class ExN {
    public static void main(String[] args) {
        int num, numExtra, helper;
        boolean flag;
        Scanner read = new Scanner(System.in);
        num = read.nextInt();
        while (num >= 0) {
            flag = true;
            numExtra = num;
            num = read.nextInt();
            if (num > numExtra && num > 0) {
                helper = num;
                while (helper > 0) {
                    if (helper % 10 <= (helper / 10) % 10) {
                        flag = false;
                    }
                    helper /= 10;
                }
            } else {
                flag = false;
            }
            if (flag == true) {
                System.out.println(num);
            }        
        }
        read.close();
    }
}
