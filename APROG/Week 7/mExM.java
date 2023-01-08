import java.util.Scanner;

public class mExM {
    public static boolean uniqueDigit(int num) {
        boolean flagUnique = true;
        //int numCheck;
        while(num != 0 && flagUnique == true) {
            flagUnique = compareDigits(num, num % 10);
            /* numCheck = num / 10;
            while(numCheck != 0 && flagUnique == true) {
                if(numCheck % 10 == num % 10)
                    flagUnique = false;
                numCheck /= 10;
            } */
            num /= 10;
        }
        return flagUnique;
    }
    public static boolean compareDigits(int num, int numCompare) {
        boolean flag = true;
        num /= 10;
        while(num != 0 && flag == true) {
            if(num % 10 == numCompare)
                flag = false;
            num /= 10;
        }
        return flag;
    }
    public static void main(String[] args) {
        Scanner read = new Scanner(System.in);
        int countTotal = 0, countPositive = 0, countUnique = 0, num = read.nextInt();
        while(num != 0) {
            if(uniqueDigit(num)) {
                System.out.println(num);
                countUnique++;
            }
            if(num > 0)
                countPositive++;
            countTotal++;
            num = read.nextInt();
        }
        if(countTotal > 0) {
            System.out.printf("negatives: %.2f%%%n", (double) (countTotal - countPositive) / countTotal * 100);
            System.out.printf("positives: %.2f%%%n", (double) countPositive / countTotal * 100);
            System.out.printf("with unique digits: %.2f%%%n", (double) countUnique / countTotal * 100);
        }
        read.close();
        System.exit(0);
    }
}