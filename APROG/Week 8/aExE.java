import java.util.Scanner;

public class aExE {
    static final int MAX = 20;
    public static int readNamesAndSalaries(String[] names, double[] salaries) {
        Scanner read = new Scanner(System.in);
        names[0] = read.next();
        int count;
        try {
            for (count = 0; !names[count].equals("end");) {
                salaries[count] = read.nextDouble();
                count++;
                names[count] = read.next();
            }
        }
        finally {
            read.close();
        }
        return count;
    }
    public static double calcAverageSalary(double[] salaries, int num) {
        int count;
        double sum = 0;
        for (count = 0; count < num; count++) {
            sum += salaries[count];
        }
        return sum / count;
    }
    public static void nameUnderAvg(String[] names, double[] salaries, double average, int num) {
        for (int i = 0; i < num; i++) {
            if (salaries[i] < average) 
                System.out.println(names[i]);
        }
    }
    public static void main(String[] args) {
        String names[] = new String[20];
        double salaries[] = new double[20];
        int num = readNamesAndSalaries(names, salaries);
        double averageSalary = calcAverageSalary(salaries, num);
        System.out.printf("%.1f%n", averageSalary);
        nameUnderAvg(names, salaries, averageSalary, num);
    }
}
