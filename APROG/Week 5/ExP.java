import java.util.Scanner;

public class ExP {
    public static void main(String[] args) {
        int num, check1, check2, count1, count2;
        boolean flagRepeat;
        Scanner read = new Scanner(System.in);
        do {
            num = read.nextInt();
            flagRepeat = false;
            if (num > 10) {
                check1 = num;
                count1 = 1;
                while (check1 > 0 && flagRepeat != true) {
                    check2 = check1 / 10;
                    count2 = count1 + 1;
                    while (check2 > 0 && flagRepeat != true) {
                        if (check2 % 10 == check1 % 10) {
                            flagRepeat = true;
                            System.out.printf("%d : digit (%d) repeated in positions (%d) and (%d)%n", num, check1 % 10, count1, count2);
                        }
                        check2 /= 10;
                        count2++;
                    }
                    check1 /= 10;
                    count1++;
                }
            }
        } while(num >= 0);
        read.close();
    }
}
