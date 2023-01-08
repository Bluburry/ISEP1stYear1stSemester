import java.util.Scanner;;

public class aExC {
    static final Scanner read = new Scanner(System.in);
    public static void readNums(int[] array, int num) {
        for (int i = 0; i < num; i++) {
            array[i] = read.nextInt();
        }
    }
    public static boolean checkArrayAscending(int[] array) {
        boolean flag = true;
        for (int i = 1; i < array.length && flag == true; i++) {
            if (array[i] <= array[i - 1])
                flag = false;
        }
        return flag;
    }
    public static void main(String[] args) {
        int num = read.nextInt();
        int array[] = new int[num];
        readNums(array, num);
        System.out.println("always ascending = " + checkArrayAscending(array));
    }
}
