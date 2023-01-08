import java.util.Scanner;
import java.lang.Runtime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class multiH {

    public static boolean checkErrors(File readFile, double h[], int initialPop, int numDays) {
		boolean checker = true;
        for (int i = 0; i < h.length; i++) {
			if (h[i] <= 0 || h[i] >= 1)
				checker = false;
		}
        if (!checker) {
            if (!readFile.exists()) // verifica a existencia do ficheiro
                checker = false;
            else if (initialPop <= 0 || numDays <= 0)
                checker = false;
        }
		return checker;
	}

    // Lê do ficheiro e guarda num array os valores de parametros que serão utilizados
    public static void readFromFile(String fileLine, double[] arrayParameters) {
		fileLine = fileLine.replaceAll(",", "."); // trocar todas as "," por "." para se poder ler os valores dos numeros
		String divLine[] = fileLine.split(";"); // fazer proveito do ";" separar todos os valores do ficheiro
		for (int i = 0; i < arrayParameters.length; i++)
			arrayParameters[i] = Double.parseDouble(divLine[i + 1]); 
		// a primeira posiçao do array que le a linha vai ser sempre o nome da pessoa
		// de forma que isso e ignorado para se poder guardar apenas os parametros
	}


	public static void writeToFile(File dir, String fileWrite, double h[], double matrix[][]) throws IOException {
		FileWriter fw = new FileWriter(new File(dir, fileWrite));
		String helperString, stringToWrite = "dia";
		
		for (int i = 0; i < h.length; i++) {
			stringToWrite += ";h" + (i + 1);
		}
		stringToWrite += "\n";
		for (int j = 0; j < matrix[0].length; j++) {
			helperString = String.format("%d", j);
			for (int i = 0; i < matrix.length; i++) {
				helperString += String.format(";%.5f",matrix[i][j]);
			}
			stringToWrite += helperString + "\n";
		}
		stringToWrite = stringToWrite.replace(".", ",");
		fw.write(stringToWrite);
		fw.close();
	}

	public static void writeAll(File dir, String fileWrite, double h[], double matrixS[][], double matrixI[][], double matrixR[][]) throws FileNotFoundException, IOException {
		fileWrite += "S.csv";
		writeToFile(dir, fileWrite, h, matrixS);
		fileWrite = fileWrite.replace("S.csv", "I.csv");
		writeToFile(dir, fileWrite, h, matrixI);
		fileWrite = fileWrite.replace("I.csv", "R.csv");
		writeToFile(dir, fileWrite, h, matrixR);
	}

	public static void plotGraph(File csvDir, File imageDir, String fileWrite, double h[]) throws FileNotFoundException, IOException {
		Scanner rf = new Scanner(new File(csvDir, fileWrite));
		File filehelper = new File("outputExtra.csv"), secHelper = new File("commands.gp");
		FileWriter fw = new FileWriter(filehelper), fw2 = new FileWriter(secHelper);
		String helper, strHelper = "", fileName = fileWrite.replace(".csv", "");

		rf.nextLine();
		while (rf.hasNextLine()) {
			strHelper += rf.nextLine();
			strHelper += "\n";
		}
		fw.write(strHelper);

		helper = "set terminal pngcairo size 1280,960 fontscale 1.6\nset output \"" + imageDir + "/" + fileName + ".png\"\n";
		helper += "set xlabel \"Days\"; set ylabel \"Susceptible\" rotate by 90; set key right top\n";
		helper += "set datafile separator \";\"\n";
		helper += "plot \"outputExtra.csv\" using 1:2 with linespoints title \"h1\", ";
		for (int i = 1; i < h.length; i++)
			helper += "\"outputExtra.csv\" using 1:" + (i + 2) + " with linespoints title \"h" + (i + 1) + "\", ";

		fw2.write(helper);
		rf.close();
		fw.close();
		fw2.close();

		Process plotRun = Runtime.getRuntime().exec("gnuplot commands.gp");
		try {
			plotRun.waitFor();
		} catch (Exception pr) {
			pr.printStackTrace();
		}
		
		// apaga os ficheiros criados, visto nao serem necessarios para o programa depois de este ser executado
		filehelper.delete();
		secHelper.delete();
	}

	public static void plotAll(File csvDir, File imageDir, String fileWrite, double h[]) throws FileNotFoundException, IOException {
		fileWrite += "S.csv";
		plotGraph(csvDir, imageDir, fileWrite, h);
		fileWrite = fileWrite.replace("S.csv", "I.csv");
		plotGraph(csvDir, imageDir, fileWrite, h);
		fileWrite = fileWrite.replace("I.csv", "R.csv");
		plotGraph(csvDir, imageDir, fileWrite, h);
	}

	public static void plotGraphInterval(File csvDir, File imageDir, String fileWrite, int start, int end, double h[]) throws FileNotFoundException, IOException {
		String helperString = start + "to" + end + ".csv", newFileWrite = fileWrite.replace(".csv", helperString);
		Scanner rf = new Scanner(new File(csvDir, fileWrite));
		File secHelper = new File("commands.gp");
		FileWriter fw = new FileWriter(new File(csvDir, newFileWrite)), fw2 = new FileWriter(secHelper);
		String helper, strHelper = "", fileName = newFileWrite.replace(".csv", "");

		rf.nextLine();
		for (int i = 0; i < start; i++)
			rf.nextLine();
		for (int i = 0; i < end - start + 1; i++) {
			strHelper += rf.nextLine();
			strHelper += "\n";
		}
		fw.write(strHelper);

		helper = "set terminal pngcairo size 1280,960 fontscale 1.6\nset output \"" + imageDir + "/" + fileName + ".png\"\n";
		helper += "set xlabel \"Days\"; set ylabel \"Susceptible\" rotate by 90; set key right top\n";
		helper += "set datafile separator \";\"\n";
		helper += "plot \"" + csvDir + "/" + fileName + ".csv\" using 1:2 with linespoints title \"h1\", ";
		for (int i = 1; i < h.length; i++)
			helper += "\"" + csvDir + "/" + fileName + ".csv\" using 1:" + (i + 2) + " with linespoints title \"h" + (i + 1) + "\", ";

		fw2.write(helper);
		rf.close();
		fw.close();
		fw2.close();

		Process plotRun = Runtime.getRuntime().exec("gnuplot commands.gp");
		try {
			plotRun.waitFor();
		} catch (Exception pr) {
			pr.printStackTrace();
		}
		
		// apaga os ficheiros criados, visto nao serem necessarios para o programa depois de este ser executado
		secHelper.delete();
	}

	public static void plotAllIInterval(File csvDir, File imageDir, String fileWrite, int start, int end, double h[]) throws FileNotFoundException, IOException {
		fileWrite += "S.csv";
		plotGraphInterval(csvDir, imageDir, fileWrite, start, end, h);
		fileWrite = fileWrite.replace("S.csv", "I.csv");
		plotGraphInterval(csvDir, imageDir, fileWrite, start, end, h);
		fileWrite = fileWrite.replace("I.csv", "R.csv");
		plotGraphInterval(csvDir, imageDir, fileWrite, start, end, h);
	}

    // formula para calcular suscetiveis
    public static double caculateSusceptible(double h, double beta, double s, double i) {
        return (h * (- beta * s * i));
	}

	// formula para calcular infetados
	public static double caculateInfected(double h, double beta, double gama, double ro, double alpha, double s, double i, double r) {
		return (h * (ro * beta * s * i - gama * i + alpha * r));
	}

	// formula para calcular recuperados
	public static double caculateRecovered(double h, double beta, double gama, double ro, double alpha, double s, double i, double r) {
        return (h * (gama * i - alpha * r + (1 - ro) * beta * s * i));
	}

	public static void eulerMethod(double arrayS[], double arrayI[], double arrayR[], double beta, double gama, double ro, double alpha, int days, int population, double h) throws IOException {
		arrayI[0] = 1;
		arrayR[0] = 0;
		arrayS[0] = population - arrayI[0] - arrayR[0];

		double sirNext[] = new double[3], sirPrev[] = {arrayS[0], arrayI[0], arrayR[0]};

		for (int i = 1; i < days; i++) {
			// este segundo for efetua o calculo necessario de steps entre cada dia
			for (double j = h; j < 1; j += h) {
				sirNext[0] = sirPrev[0] + caculateSusceptible(h, beta, sirPrev[0], sirPrev[1]);
				sirNext[1] = sirPrev[1] + caculateInfected(h, beta, gama, ro, alpha, sirPrev[0], sirPrev[1], sirPrev[2]);
				sirNext[2] = sirPrev[2] + caculateRecovered(h, beta, gama, ro, alpha, sirPrev[0], sirPrev[1], sirPrev[2]);

				sirPrev[0] = sirNext[0];
				sirPrev[1] = sirNext[1];
				sirPrev[2] = sirNext[2];
			}

			arrayS[i] = sirNext[0];
			arrayI[i] = sirNext[1];
			arrayR[i] = sirNext[2];
		}
	}

    public static void calculateKutta(double h, double beta, double gama, double ro, double alpha, double[] popSIR) {
        double k[][] = new double[5][3];
		// crio matriz para facilitar o guardar a informaçao de todos, visto que preciso de cada um dos k para cada uma das populaçoes
		// utiliza a mesma convençao que o array das populaçoes, de maneira que a posiçao do array vai ser a mesma que as populaçoes com que estiver a trabalhar

        k[0][0] = caculateSusceptible(h, beta, popSIR[0], popSIR[1]);
        k[0][1] = caculateInfected(h, beta, gama, ro, alpha, popSIR[0], popSIR[1], popSIR[2]);
        k[0][2] = caculateRecovered(h, beta, gama, ro, alpha, popSIR[0], popSIR[1], popSIR[2]);

		for (int i = 1; i < 3; i++) { // calcula so o k2 e k3, visto serem os unicos que se calculam da mesma forma
			k[i][0] = caculateSusceptible(h, beta, popSIR[0] + k[i - 1][0] / 2, popSIR[1] + k[i - 1][1] / 2);
			k[i][1] = caculateInfected(h, beta, gama, ro, alpha, popSIR[0] + k[i - 1][0] / 2, popSIR[1] + k[i - 1][1] / 2, popSIR[2] + k[i - 1][2] / 2);
			k[i][2] = caculateRecovered(h, beta, gama, ro, alpha, popSIR[0] + k[i - 1][0] / 2, popSIR[1] + k[i - 1][1] / 2, popSIR[2] + k[i - 1][2] / 2);
		}
        
        k[3][0] = caculateSusceptible(h, beta, popSIR[0] + k[2][0], popSIR[1] + k[2][1]);
        k[3][1] = caculateInfected(h, beta, gama, ro, alpha, popSIR[0] + k[2][0], popSIR[1] + k[2][1], popSIR[2] + k[2][2]);
        k[3][2] = caculateRecovered(h, beta, gama, ro, alpha, popSIR[0] + k[2][0], popSIR[1] + k[2][1], popSIR[2] + k[2][2]);
        
        for (int i = 0; i < 3; i++)
            k[4][i] = (k[0][i] + 2 * k[1][i] + 2 * k[2][i] + k[3][i]) / 6;
        
        for (int i = 0; i < 3; i++)
            popSIR[i] += k[4][i];
		// utilizar o += simplifica e elimina a necessidade de usar variaveis para guardar o valor anterior
    }

    public static void kuttaMethod(double arrayS[], double arrayI[], double arrayR[], double beta, double gama, double ro, double alpha, int days, int population, double h) throws IOException {
		arrayI[0] = 1;
		arrayR[0] = 0;
		arrayS[0] = population - arrayI[0] - arrayR[0];

		double sirStart[] = {arrayS[0], arrayI[0], arrayR[0]};

		for (int i = 1; i < days; i++) {
			// este segundo for efetua o calculo necessario de steps entre cada dia
			for (double j = h; j < 1; j += h) {
				calculateKutta(h, beta, gama, ro, alpha, sirStart);
			}

			arrayS[i] = sirStart[0];
			arrayI[i] = sirStart[1];
			arrayR[i] = sirStart[2];
		}
	}

    public static void main(String[] args) throws FileNotFoundException, IOException{
        int numDays = 30, initialPop = 1000, dayStart = 5, dayEnd = 10;
		String fileName = "input.csv";
		double h[] = {0.1, 0.01, 0.001, 0.0001, 0.00001, 0.000001};

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

		System.out.println("For better analysis tell us a date period in which to zoom.");

		System.out.println("Start day");
		do {
			try {
				dayStart = readUser.nextInt();
				correct = true;
			} catch (Exception e) {
				System.out.println("Invalid input, please try again.");
				correct = false;
			}
		} while (!correct);	

		System.out.println("End day");
		do {
			try {
				dayEnd = readUser.nextInt();
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

		File readFile = new File(fileName); // Criado para depois ser possível validar a sua existência na working directory
		if (!checkErrors(readFile, h, initialPop, numDays))
			System.out.println("Dados incorrectos, por favor tente novamente com valores aceitáveis.");
		else {
			File csvDir = new File("tables");
			csvDir.mkdir();
			File imageDir = new File("images");
			imageDir.mkdir();
			Scanner read = new Scanner(readFile);
			double arrayParameters[] = new double[4]; // 0 - beta, 1 - gama, 2 - ro, 3 - alpha
			double matrixS[][] = new double[h.length][numDays],  matrixI[][] = new double[h.length][numDays],  matrixR[][] = new double[h.length][numDays];
			read.nextLine(); // ignora a primeira linha
			String fileLine, patientName;
			while (read.hasNextLine()) {
				fileLine = read.nextLine();
				patientName = fileLine.split(";")[0];
                readFromFile(fileLine, arrayParameters);
				for (int i = 0; i < h.length; i++)
                    eulerMethod(matrixS[i], matrixI[i], matrixR[i], arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h[i]);
				fileName = patientName + "m1" + "t" + initialPop + "d" + numDays;
				writeAll(csvDir, fileName, h, matrixS, matrixI, matrixR);
				plotAll(csvDir, imageDir, fileName, h);
				plotAllIInterval(csvDir, imageDir, fileName, dayStart, dayEnd, h);
				for (int i = 0; i < h.length; i++)
                    kuttaMethod(matrixS[i], matrixI[i], matrixR[i], arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h[i]);
				fileName = patientName + "m2" + "t" + initialPop + "d" + numDays;
				writeAll(csvDir, fileName, h, matrixS, matrixI, matrixR);
				plotAll(csvDir, imageDir, fileName, h);
				plotAllIInterval(csvDir, imageDir, fileName, dayStart, dayEnd, h);
			}
			read.close();
		}
	}
}
