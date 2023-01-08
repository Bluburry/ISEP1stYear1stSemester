import java.util.Scanner;
import java.lang.Math;

public class mExF {
    static Scanner read = new Scanner(System.in);
    private static void sphereVol() {
        double radius;
        radius = read.nextDouble();
        System.out.printf("%.2f%n", (4 * Math.PI * Math.pow(radius, 3)) / 3);
    }
    private static void cylinderVol() {
        double radius, height;
        radius = read.nextDouble();
        height = read.nextDouble();
        System.out.printf("%.2f%n", Math.PI * Math.pow(radius, 2) * height);
    }
    private static void coneVol() {
        double radius, height;
        radius = read.nextDouble();
        height = read.nextDouble();
        System.out.printf("%.2f%n", (Math.PI * Math.pow(radius, 2) * height) / 3);
    }
    public static void main(String[] args) {
        String solid;
        do {
            solid = read.next();
            switch (solid) {
                case "sphere":
                    sphereVol();
                    break;
                case "cylinder":
                    cylinderVol();
                    break;
                case "cone":
                    coneVol();
                    break;
                default:
                    break;
            }
        } while(!solid.equals("end"));
        System. exit(0);
    }
}
