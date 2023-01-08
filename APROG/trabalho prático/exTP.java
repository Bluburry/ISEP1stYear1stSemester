import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class exTP {
    //static final String FILETOREAD = "/media/bluburry/Windows/Programming/ISEP/APROG/trabalho prático/fileTest.txt";
    static final String FILETOREAD = "fileTest.txt";
    static final int ARRAYDANGER[] = {40, 30, 20};
    static final String DANGER_LEVELS[] = {"CATASTROPHIC", "EXTREME", "HIGH", "MODERATE"};
    static final int FIRE_TEMP = 50;
    static final int FIRST_DEVIATION = -10;
    static final int SECOND_DEVIATION = 10;
    static final int WATER_SIZE = 3;

    // a)
    public static void readMatrixNumbersFromFile(int[][] matrix, Scanner readFile) throws FileNotFoundException  {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] = readFile.nextInt();
    }
    
    // b)
    public static void printMatrixValues(int[][] matrix) { // must be right aligned
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.printf("%5d", matrix[i][j]); // align to the right and leave some space in between
            System.out.printf("%n");
        }
    }
    
    // c)
    public static int getMapLetter(int num) { // helps for other exercises and simplifies printMap
        int letter = 0;
        while (letter < ARRAYDANGER.length && num < ARRAYDANGER[letter])
            letter++; // this way i can correlate the position on this array to the levels array, using the exact same position for both
        return DANGER_LEVELS[letter].charAt(0);
    }
    public static void printMap(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++)
                System.out.printf("%c", getMapLetter(matrix[i][j]));
            System.out.printf("%n");
        }
    }
    
    // d)
    public static void matrixDeviation(int[][] matrix, int deviation) {
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                matrix[i][j] += deviation;
    }
    
    // e)
    public static double letterPercentages(int[][] matrix, int letter) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (letter == getMapLetter(matrix[i][j]))
                    count++;
        return (double) count / (matrix.length * matrix[0].length) * 100;
    }
    public static void alertArea(int[][] matrix) {
        for (int i = DANGER_LEVELS.length - 1; i >= 0; i--)
            System.out.printf("%-10s\t: %6.2f%%%n", DANGER_LEVELS[i], letterPercentages(matrix, DANGER_LEVELS[i].charAt(0)));
    }
    
    // f)
    public static int tempRiseToCatastrophic(int[][] matrix) {
        int temp = ARRAYDANGER[0] - matrix[0][0];
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (ARRAYDANGER[0] - matrix[i][j] > temp)
                    temp = ARRAYDANGER[0] - matrix[i][j];
        if (temp < 0)
            temp = 0;
        return temp;
    }
    
    // g)
    public static double percentageLetterChange(int[][] matrix, int deviation) {
        int count = 0;
        for (int i = 0; i < matrix.length; i++)
            for (int j = 0; j < matrix[0].length; j++)
                if (getMapLetter(matrix[i][j]) != getMapLetter(matrix[i][j] + deviation))
                    count++;
        matrixDeviation(matrix, deviation);
        printMap(matrix);
        return (double) count / (matrix.length * matrix[0].length) * 100;
    }

    // h)
    public static void mapWindChanges(int[][] matrix) {
        for (int i = 0; i < matrix[0].length; i++)
            System.out.printf("%c", getMapLetter(matrix[0][i]));
        System.out.printf("%n");
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (getMapLetter(matrix[i - 1][j]) == 'C')
                    System.out.printf("C");
                else
                    System.out.printf("%c", getMapLetter(matrix[i][j]));
            }
            System.out.printf("%n");
        }
    }

    // i)
    // IMP: neste exercício os dois primeiros métodos a seguir responde ao exercício
    // No entanto, por curiosidade quissemos descobrir uma forma que permitisse escalar para qualquer área da queda de água
    // Servindo para isso a função comentada abaixo, que serve tanto para pares ou impares
    // (no caso deste programa, alterando o valor da constante WATER_SIZE, para 5 ou 8, por exemplo)

    public static int squareAnalysis(int[][] matrix, int lineStart, int colStart) {
        int numOfFires = 0;
        for (int i = -1; i < WATER_SIZE - 1; i++) {
            for (int j = -1; j < WATER_SIZE - 1; j++) {
                if (matrix[lineStart + i][colStart + j] > FIRE_TEMP) {
                    numOfFires++;
                }
            }
        }
        return numOfFires;
    }
    public static String waterDrop(int[][] matrix) {
        if (matrix.length < WATER_SIZE || matrix[0].length < WATER_SIZE) {
            return "not enough room";
        }
        else {
            int maxFire = 0;
            int fireCell[] = new int[2];
            for (int i = 1; i < matrix.length - 1; i++) {
                for (int numofFires, j = 1; j < matrix[0].length - 1; j++) {
                    numofFires = squareAnalysis(matrix, i, j);
                    if (maxFire < numofFires) {
                        maxFire = numofFires;
                        fireCell[0] = i;
                        fireCell[1] = j;
                    }
                }
            }
            if (maxFire > 0) {
                return "drop water at (" + fireCell[0] + ", " + fireCell[1] + ")"; 
            }
            else {
                return "no fire";
            }
        }
    }

    /* 
    public static int squareAnalysis(int[][] matrix, int lineStart, int colStart) {
        int numOfFires = 0;
        for (int i = 0; i < WATER_SIZE; i++) {
            for (int j = 0; j < WATER_SIZE; j++) {
                if (matrix[lineStart + i][colStart + j] > FIRE_TEMP) {
                    numOfFires++;
                }
            }
        }
        return numOfFires;
    }
    public static String waterDrop(int[][] matrix) {
        if (matrix.length < WATER_SIZE || matrix[0].length < WATER_SIZE) {
            return "not enough room";
        }
        else {
            int maxFire = 0;
            int fireCell[] = new int[2];
            for (int i = 0; i < matrix.length - (WATER_SIZE - 1); i++) {
                for (int numofFires, j = 0; j < matrix[0].length - (WATER_SIZE - 1); j++) {
                    numofFires = squareAnalysis(matrix, i, j);
                    if (maxFire < numofFires) {
                        maxFire = numofFires;
                        fireCell[0] = i;
                        fireCell[1] = j;
                    }
                }
            }
            int size_water = WATER_SIZE;
            if (maxFire > 0) {
                String s ="drop water at";
                if (size_water % 2 != 0) {
                    s += " (" + (fireCell[0] + (WATER_SIZE / 2)) + ", " + (fireCell[1] + (WATER_SIZE / 2)) + ")";
                }
                else {
                    s += "\n\t[" + (fireCell[0] + (WATER_SIZE / 2) - 1) + ", " + (fireCell[1] + (WATER_SIZE / 2) - 1);
                    s += "] [" + (fireCell[0] + (WATER_SIZE / 2) - 1) + ", " + (fireCell[1] + (WATER_SIZE / 2));
                    s += "]\n\t[" + (fireCell[0] + (WATER_SIZE / 2)) + ", " + (fireCell[1] + (WATER_SIZE / 2) - 1);
                    s += "] [" + (fireCell[0] + (WATER_SIZE / 2)) + ", " + (fireCell[1] + (WATER_SIZE / 2)) + "]";
                }
                return s;
            }
            else {
                return "no fire";
            }
        }
    } 
    */
    
    // j)
    public static String safeColumn(int[][] matrix) {
        int column;
        boolean flagSafe = false;
        for (column = matrix[0].length - 1; column >= 0 && !flagSafe; column--) {
            flagSafe = true;
            for (int i = 0; i < matrix.length && flagSafe; i++)
                if (matrix[i][column] >= ARRAYDANGER[0])
                    flagSafe = false;
        }
        if (flagSafe)
            return "(" + (column + 1) + ")";
        else
            return "NONE";
    }
    
    public static void main(String[] args) throws FileNotFoundException {
        Scanner read = new Scanner(new File(FILETOREAD));
        read.nextLine();
        int numLines, numColumns;
        numLines = read.nextInt();
        numColumns = read.nextInt();
        int matrix[][] = new int[numLines][numColumns];

        // a)
        //readMatrixNumbers(matrix);
        readMatrixNumbersFromFile(matrix, read);

        // b)
        System.out.println("b)");
        printMatrixValues(matrix);
        
        // c) 
        System.out.println("\nc)");
        printMap(matrix);
        
        // d) 
        System.out.println("\nd)");
        matrixDeviation(matrix, FIRST_DEVIATION);
        printMatrixValues(matrix);
        System.out.println();
        printMap(matrix);
        
        // e) 
        System.out.println("\ne)");
        alertArea(matrix);

        // f) 
        System.out.println("\nf)");
        System.out.printf("To get all terrain on CATASTROPHIC alert, the temperature has to rise : %d ºC%n", tempRiseToCatastrophic(matrix));
        
        // g) 
        System.out.println("\ng)");
        System.out.printf("%nAlert Levels changes due to temperature variations by 10ºC : %.2f%%%n", percentageLetterChange(matrix, SECOND_DEVIATION));

        // h) 
        System.out.println("\nh)");
        mapWindChanges(matrix);
        
        // i) 
        System.out.println("\ni)");
        printMatrixValues(matrix);
        System.out.printf("%n%s%n", waterDrop(matrix));
        
        // j) 
        System.out.println("\nj)");
        System.out.println("safe column = " + safeColumn(matrix));
        
        read.close();
    }
}
