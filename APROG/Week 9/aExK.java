import java.util.Scanner;

public class aExK {
    static final Scanner read = new Scanner(System.in);
    public static void readValues(int[] array) {
        for (int i = 0; i < array.length; i++) 
            array[i] = read.nextInt();
    }
    public static void printWithNoDupes(int[] array) {
        boolean flagPrint;
        for (int i = 0; i < array.length; i++) {
            flagPrint = true;
            for (int j = 0; j < i && flagPrint; j++) {
                if (array[j] == array[i])
                    flagPrint = false;
            }
            if (flagPrint)
                System.out.printf("%d\n", array[i]);
        }
    }
    public static void main(String[] args) {
        int N = read.nextInt();
        int array[] = new int[N];
        readValues(array);
        //System.out.println("resultados:");
        printWithNoDupes(array);
    }
}
