import java.util.Scanner;

public class mExB {
    static Scanner read = new Scanner(System.in);
    public static void askClass (int students, int classes) {
        int approved;
        String className;
        for (int i = 0; i < classes; i++) {
            className = read.next();
            approved = read.nextInt();
            printInfo(students, approved, className);
        }
    }
    public static void printInfo(int maxStudents, int approvedStudents, String className) {
        System.out.println("Subject: " + className);
        System.out.println("- Approved: " + printStars(approvedStudents));
        /* for (int i = 0; i < approvedStudents; i++) {
            System.out.printf("*");
        } */
        System.out.println("- Failed: " + printStars(maxStudents - approvedStudents));
        /* for (int i = 0; i < maxStudents - approvedStudents; i++) {
            System.out.printf("*");
        } */
    }
    public static String printStars(int numStars) {
        String stars = "";
        for (int i = 0; i < numStars; i++) {
            stars += "*";  
        }
        return stars;
    }
    public static void main(String[] args) {
        int numStudents, numClasses;
        numStudents = read.nextInt();
        numClasses = read.nextInt();
        askClass(numStudents, numClasses);
    }
}
