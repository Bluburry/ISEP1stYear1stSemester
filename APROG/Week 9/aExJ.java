import java.util.Scanner;

public class aExJ {
    static final int MAXNUM = 50;
    public static int readNamesAndSalaries(String[] names, int[] salaries) {
        Scanner read = new Scanner(System.in);
        int increment = 0;
        names[increment] = read.nextLine();
        while (!names[increment].equals("END")) {
            salaries[increment] = read.nextInt();
            read.nextLine();
            increment++;
            names[increment] = read.nextLine();
        }
        read.close();
        return increment;
    }
    public static void orderSalaries(String[] names, int[] salaries, int num) {
        String swapName;
        int swapSalary = 0;
        for (int i = 1; i < num; i++) {
            for (int j = 0; j < i; j++) {
                if (salaries[j] < salaries[i] || (salaries[j] == salaries[i] && names[j].compareTo(names[i]) > 0)) {
                    swapSalary = salaries[i];
                    salaries[i] = salaries[j];
                    salaries[j] = swapSalary;
                    swapName = names[i];
                    names[i] = names[j];
                    names[j] = swapName;
                }
            }
        }
    }
    public static void printTopThree(String[] names, int[] salaries, int num) {
        for (int i = 0; i < 3 && i < num; i++)
            System.out.printf("#%d:%s:%d\n", i + 1, names[i], salaries[i]);
    }
    public static void main(String[] args) {
        String names[] = new String[MAXNUM];
        int salaries[] = new int[MAXNUM];
        int numberOfInputs = readNamesAndSalaries(names, salaries);
        orderSalaries(names, salaries, numberOfInputs);
        printTopThree(names, salaries, numberOfInputs);
    }
}
