//import java.util.Scanner;
import java.lang.Runtime;
import java.io.File;
import java.io.IOException;
//import java.nio.file.Files;

public class autorun {

	public static boolean checkErrors(String fileName, String projectFile, int method, double[] h, int initialPop, int numDays) {
		File fileRead = new File(fileName);
		File fileRead2 = new File(projectFile);
		boolean checker = true;
		for (int i = 0; i < h.length; i++) {
			if (h[i] <= 0 || h[i] >= 1)
				checker = false;
		}
		if (checker)
		{
			if (!fileRead.exists() || !fileRead2.exists())
				checker = false;
			else if (!(method == 1 || method == 2 || method == 3))
				checker = false;
			else if (initialPop <= 0 || numDays <= 0)
				checker = false;
		}
		return checker;
	}

	public static void executeProgram(Process runProcess, int days, int pop, int method, String inputFile, double h, String runFile) throws IOException {
		if (method == 3) {
			executeProgram(runProcess, days, pop, 1, inputFile, h, runFile);
			executeProgram(runProcess, days, pop, 2, inputFile, h, runFile);
		}
		else {
			String helper = "java -jar " + runFile + " " + inputFile + " -m " + method + " -p " + h + " -t " + pop + " -d " + days;
			runProcess = Runtime.getRuntime().exec(helper);
			try {
				runProcess.waitFor();
			} catch (Exception rp) {
				rp.printStackTrace();
				System.out.println("can't continue");
				System.exit(0);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		// This program has to be in the same working directory and your project and input file
		// For easier testing you can also hardcode the values
		int numDays = 30, initialPop = 1000, method = 3;
		String fileName = "input.csv", projectFile = "projeto.java";
		double h[] = {0.1, 0.01, 0.001};

		/*

		int numDays = 0, initialPop = 0, method = 0, numOfH = 0;
		String fileName = "", projectFile = "";
		Boolean correct = true;
		Scanner readUser = new Scanner(System.in);

		System.out.println("Enter the number of days");
		do {
			try {
				numDays = readUser.nextInt();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);

		System.out.println("Enter the starting population");
		do {
			try {
				initialPop = readUser.nextInt();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);
		
		readUser.nextLine();
		System.out.println("Enter the name of the input file");
		do {
			try {
				fileName = readUser.nextLine();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);
		
		System.out.println("Enter the name of the project file, with the extension (i.e: projeto.java)");
		do {
			try {
				projectFile = readUser.nextLine();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);

		System.out.println("Which method do you want to use?\n1 - Euler, 2 - Runge-Kutta, 3 - Both.");
		do {
			try {
				method = readUser.nextInt();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);		

		readUser.nextLine();
		System.out.println("How many h do you want to compare?");
		do {
			try {
				numOfH = readUser.nextInt();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);

		double h[] = new double[numOfH];
		for (int i = 0; i < h.length; i++) {			
			do {
				try {
					h[i] = readUser.nextDouble();
					correct = true;
				} catch (Exception e) {
					System.out.println("Invalid input, please try again.");
					correct = false;
				}
			} while (!correct);
		}

		readUser.close();

		*/

		if (!checkErrors(fileName, projectFile, method, h, initialPop, numDays))
			System.out.println("Dados incorrectos, por favor tente novamente com valores aceitáveis.");
		else {
			String helper = projectFile.replace(".java", "");
			Process runProcess = Runtime.getRuntime().exec("javac " + projectFile);
			try {
				runProcess.waitFor();
			} catch (Exception rp) {
				rp.printStackTrace();
				System.out.println("can't continue");
				System.exit(0);
			}
			runProcess = Runtime.getRuntime().exec("jar cfe run.jar " + helper + " " + helper + ".class");
			try {
				runProcess.waitFor();
			} catch (Exception rp) {
				rp.printStackTrace();
				System.out.println("can't continue");
				System.exit(0);
			}
			for (int i = 0; i < h.length; i++) {
				executeProgram(runProcess, numDays, initialPop, method, fileName, h[i], "run.jar");
			}
			runProcess.destroy();
			File fl1 = new File("autorun.class"), fl2 = new File("run.jar"), fl3 = new File(helper + ".class");
			fl1.delete();
			fl2.delete();
			fl3.delete();
			//moveFiles(fileName);
		}
	}
}