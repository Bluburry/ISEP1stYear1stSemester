import java.util.Scanner;

public class aExQ {
    static final Scanner read = new Scanner(System.in);
    public static void readNames(String[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                array[i][j] = read.nextLine();
            }
        }
    }
    public static void findTenant(String[][] array, String name) {
        boolean flagLives = false;
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[0].length; j++) {
                if (name.equals(array[i][j])) {
                    flagLives = true;
                    System.out.print(printApartment(i, j, name));
                }
            }
        }
        if (!flagLives)
            System.out.println("Do not live in the building");
    }
    public static String printApartment(int entrance, int floor, String name) {
        return "name=" + name + "\nentrance=" + entrance + "\nfloor=" + floor + "\n";
    }
    public static void main(String[] args) {
        int entrances, floors;
        entrances = read.nextInt();
        floors = read.nextInt();
        read.nextLine();
        String tenants[][] = new String[entrances][floors];
        readNames(tenants);
        String tenantToFind = read.nextLine();
        findTenant(tenants, tenantToFind);
    }
}
