import java.util.Scanner;

public class exFactorial {

	public static int onlyFacts(int a) {
		int fact = 1;
		while (a > 1)
			fact *= a--;
		return fact;
	}
	
	public static void onlyLogic(int[] array) {
		for (int i = array.length - 1, logic = 9; i >= 0; i--, logic--)
			array[i] = onlyFacts(logic);
	}

	public static int minValue(int[] array, int n, int x) {
		int val;
		if (array[9] > x) {
			val = 9;
			while (array[val] > x)
				val--;
			val++;
		}
		else {
			val = 19;
			boolean flagStop = false;
			for (int i = val, sum = 0; !flagStop && val < n; val += 10, sum = 0, i = val) {
				while (i > 0 && sum < x) {
					sum += array[i % 10];
					i /= 10;
				}
				if (sum > x)
					flagStop = true;
			}
			flagStop = false;
			for (int i = val, sum = 0; !flagStop && val < n; val--, sum = 0, i = val) {
				while (i > 0 && sum < x) {
					sum += array[i % 10];
					i /= 10;
				}
				if (sum > x)
					flagStop = true;
			}
		}
		if (val <= n)
			return val;
		else
			return -1;
	}

	public static boolean checkValidity(int val, int n) {
		int count1 = 0, count2 = 0;
		while (val > 0) {
			count1 += val % 10;
			val /= 10;
		}
		while (n > 0) {
			count2 += n % 10;
			n /= 10;
		}
		if (count2 >= count1)
			return true;
		else
			return false;
	}

	public static int factsAndLogic(int n, int x) {
		if (x == 0)
			return n;
		else {
			int count = 0, facts[] = new int[10];
			onlyLogic(facts);
			int val = minValue(facts, n, x);
			for (int i = val, j = i, sum = 0; i <= n && i > 0; i++, j = i, sum = 0) {
				if (checkValidity(val, j))
				{
					while (j > 0 && sum < x) {
						sum += facts[j % 10];
						j /= 10;
					}
					if (sum > x)
						count++;
				}
			}
			return count;
		}
	}

	public static void main(String[] args) {
		Scanner read = new Scanner(System.in);
		int N = read.nextInt(), X = read.nextInt();
		System.out.printf("%d\n", factsAndLogic(N, X));
		read.close();
	}
}