import java.util.Scanner;

public class ExO {
    public static void main(String[] args) {
        int ingNo, ingCheck, numPizza, menuPizza, menuCheck, countSuggest = 0;
        boolean flagPizza;
        Scanner read = new Scanner(System.in);
        do {
            ingNo = read.nextInt();
        } while (ingNo < 0);
        do {
            numPizza = read.nextInt();
        } while (numPizza <= 0);
        for (int i = 0; i < numPizza; i++) {
            flagPizza = true;
            do {
                menuPizza = read.nextInt();
            } while (menuPizza <= 0);
            menuCheck = menuPizza;
            while (menuCheck > 0) {
                ingCheck = ingNo;
                do {
                    if (menuCheck % 10 == ingCheck % 10) {
                        flagPizza = false;
                    }
                    ingCheck /= 10;
                } while (ingCheck > 0);
                menuCheck /= 10;
            }
            if (flagPizza == true) {
                countSuggest++;
                System.out.printf("Suggestion #%d:%d%n", countSuggest, menuPizza);
            }
        }
        read.close();
    }
}
