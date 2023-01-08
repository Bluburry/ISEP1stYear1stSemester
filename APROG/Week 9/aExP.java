import java.util.Scanner;

public class aExP {
    static final int STARTVAL = 19;
    // se a função calcDigitOverVal não encontrar digito singular cujo faturial seja maior que o valor
    // vai então devoltar o menor valor seguinte que possivelmente passe, neste caso 19
    // porque 19 -> 1! + 9! > 9!
    public static int calcFactorial(int n) {
        int sum = 1;
        while (n > 0)
            sum *= n--;
        return sum;
    }
	public static void fillArrayWithFactorials(int[] array) {
        	for (int i = 0; i < array.length; i++)
	        	array[i] = calcFactorial(i);
	}
	public static int calcDigitOverVal(int[] array, int val) {
		int num = STARTVAL;
        boolean flagOver = false;
		for (int i = 0; i < array.length && !flagOver; i++) {
			if (array[i] > val) {
				num = i;
                flagOver = true;
            }
		}
		return num;
	}
	public static int factorialsOverX(int[] array, int N, int X) {
		int sum, val, count = 0;
		val = calcDigitOverVal(array, X);
		boolean flagOver, flagEnd = false;
		for (int j, i = val; i <= N && !flagEnd; i++) {
			sum = 0;
			flagOver = false;
            j = i;
			while (j > 0 && !flagOver && sum < X) {
				if (j % 10 >= val)
					flagOver = true;
				sum += array[j % 10];
				j /= 10;
			}
			if (sum > X || flagOver)
				count++;
		}
		return count;
	}
    public static void main(String[] args) {
        int arrayNums[] = new int[10];
	    fillArrayWithFactorials(arrayNums);
        Scanner read = new Scanner(System.in);
        int N, X;
        N = read.nextInt();
        X = read.nextInt();
        System.out.println(factorialsOverX(arrayNums, N, X));
        read.close();
    }
}
