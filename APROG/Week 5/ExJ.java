import java.util.Scanner;

public class ExJ {
    public static void main (String[] args) {
        int numCount, num, numAlt, count, countDiv;
        double percent = - 1;
        Scanner read = new Scanner(System.in);
        numCount = read.nextInt();
        while (numCount > 0) {
            num = read.nextInt();
            numAlt = num;
            count = 0;
            countDiv = 0;
            while (numAlt > 0) {
                if ((numAlt % 10) != 0 && num % (numAlt % 10) == 0) 
                    countDiv++;
                count++;
                numAlt /= 10;
            }
            System.out.printf("%.2f%%%n", ((double) countDiv / count) * 100);
            if (percent < ((double) countDiv / count) * 100)
                percent = ((double) countDiv / count) * 100;
            numCount--;
        }
        if (percent > - 1)
            System.out.printf("(%.2f%%)%n", percent);
        read.close();
        System.exit(0);
    }
}
