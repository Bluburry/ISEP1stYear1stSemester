import java.util.Scanner;

public class ExX {
    public static void main(String[] args) {
        int code;
        String brand;
        Scanner read = new Scanner(System.in);
        code = read.nextInt();
        switch (code) {
            case 1:
                brand = "Tag Heuer";
                break;
            case 2:
                brand = "Rolex";
                break;
            case 3:
                brand = "Omega";
                break;
            case 4:
                brand = "Cartier";
                break;
            case 5:
                brand = "Bvlgari";
                break;
            case 6:
                brand = "Raymond Weil";
                break;
            default:
                brand = "Invalid brand";
                break;
        }
        System.out.println(brand);
        read.close();
    }
}
