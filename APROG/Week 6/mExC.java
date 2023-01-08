import java.util.Scanner;
import java.lang.Math;

public class mExC {
    public static void triPossible (double a, double b, double c) {
        if (a + b > c && c + b > a && a + c > b)
            printAngle(a, b, c);
        else
            System.out.println("impossible");
    }
    public static void printAngle (double a, double b, double c) {
        System.out.printf("a=%.2f%n", a);
        System.out.printf("b=%.2f%n", b);
        System.out.printf("c=%.2f%n", c);
        System.out.printf("ang(a,b)=%.2f%n", calcAngle(a, b, c));
        System.out.printf("ang(a,c)=%.2f%n", calcAngle(a, c, b));
        System.out.printf("ang(b,c)=%.2f%n", calcAngle(b, c, a));
    }
    public static double calcAngle (double a, double b, double c) {
        return changeRadi(Math.acos((a*a + b*b - c*c)/(2*a*b)));
    }
    public static double changeRadi (double radi) {
        //System.out.println(radi);
        return (180 * radi / Math.PI);
    }
    public static void main(String[] args) {
        double a, b, c;
        Scanner read = new Scanner(System.in);
        a = read.nextDouble();
        b = read.nextDouble();
        c = read.nextDouble();
        triPossible(a, b, c);
        read.close();
        System.exit(0);
    }
}
