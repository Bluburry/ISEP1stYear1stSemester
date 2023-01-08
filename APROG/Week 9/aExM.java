import java.util.Scanner;

public class aExM {
    static final int MAXNUM = 60;
    public static int readInputs(int[] array) {
        Scanner read = new Scanner(System.in);
        int increment = 0;
        do {
            array[increment] = read.nextInt();
        } while(array[increment++] >= 0);
        read.close();
        return increment - 1;
    }
    public static int countUniqueDigits(int num) {
        int count = 0;
        if (num == 0)
            count++;
        for (boolean flagUnique; num > 0; num /= 10) {
            flagUnique = true;
            for (int helper = num / 10; helper > 0 && flagUnique; helper /= 10) {
                if (num % 10 == helper % 10)
                    flagUnique = false;
            }
            if (flagUnique)
                count++;
        }
        return count;
    }
    public static void printWithNumDigits(int max, int[] array) {
        for (int i = 0; i < max; i++)
            System.out.printf("%d:%d\n", array[i], countUniqueDigits(array[i]));
    }
    public static void main(String[] args) {
        int array[] = new int[MAXNUM];
        int max = readInputs(array);
        //System.out.println("resultados:");
        printWithNumDigits(max, array);
    }
}
