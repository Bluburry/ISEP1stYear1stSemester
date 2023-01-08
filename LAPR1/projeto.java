import java.util.Scanner;
import java.lang.Runtime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class projeto {

	// trata dos erros de input do utilizador no modo interativo
	// return sempre falso apenas para nao requirir tantas linhas no main
    public static boolean handleException(Scanner readUser) {
        System.out.println("Invalid input, please try again.");
        readUser.nextLine(); // descarta a linha que resultou em erro
        return false;
    }

	// Verifica se os valores introduzidos pelo utilizados são aceitáveis
	public static boolean checkErrors(File fileRead, int method, double h, int initialPop, int numDays) {
		boolean checker = true;
		if (!fileRead.exists()) // verifica a existencia do ficheiro
			checker = false;
		else if (!(method == 1 || method == 2))
			checker = false;
		else if (h <= 0 || h > 1)
			checker = false;
		else if (initialPop <= 0 || numDays <= 0)
			checker = false;
		return checker;
	}

	// Utilizando os valores inseridos pelo utilizador cria o nome para o ficheiro de outuput
	public static String getOutputFile(String name, int method, double step, int population, int  days) {
		String outputName = name + "m" + method + "p" + step + "t" + population + "d" + days;
		outputName = outputName.replace(".", ""); //retira o ponto do passo de iteraçao
		outputName += ".csv";
		return (outputName);
	}

	// Lê do ficheiro e guarda num array os valores de parametros que serão utilizados
    public static void readFromFile(String fileLine, double[] arrayParameters) {
		fileLine = fileLine.replaceAll(",", "."); // trocar todas as "," por "." para se poder ler os valores dos numeros
		String divLine[] = fileLine.split(";"); // fazer proveito do ";" separando todos os valores do ficheiro
		for (int i = 0; i < arrayParameters.length; i++)
			arrayParameters[i] = Double.parseDouble(divLine[i + 1]); 
		// a primeira posiçao do array que le a linha vai ser sempre o nome da pessoa
		// de forma que isso e ignorado para se poder guardar apenas os parametros
	}

	// Escreve para o ficheiro de output os valores calculados
	public static void writeToFile(File dir, String fileWrite, int days, double matrixSIR[][]) throws IOException {
		FileWriter fw = new FileWriter(new File(dir, fileWrite));
		String helperString, stringToWrite = String.format("dia;S;I;R;N\n");
		// cria logo a primeira linha, visto que esta sera sempre igual
		for (int i = 0; i < days; i++) {
			helperString = String.format("%d;%.5f;%.5f;%.5f;%.5f\n", i, matrixSIR[0][i], matrixSIR[1][i], matrixSIR[2][i], matrixSIR[0][i] + matrixSIR[1][i] + matrixSIR[2][i]);
			// utilizamos uma string auxiliar para poder formatar os valores para terem as casas decimais desejadas
			stringToWrite += helperString;
		}
		stringToWrite = stringToWrite.replace(".", ",");
		fw.write(stringToWrite);
		fw.close();
	}

	// Utilizando o gnuplot cria um png do gráfico baseado nos valores obtidos
	public static void plotGraph(File tableDir, File imageDir, String fileWrite) throws FileNotFoundException, IOException {
		Scanner rf = new Scanner(new File(tableDir, fileWrite));
		File helper = new File("outputExtra.csv"), secHelper = new File("commands.gp");
		FileWriter fw = new FileWriter(helper), fw2 = new FileWriter(secHelper);
		// outputExtra.csv -> torna o ficheiro de output em algo aceitavel pelo gnuplot
		// commands.gp -> vai guardar os comandos a serem corridos 
		
		String h, strHelper = "", fileName = fileWrite.replace(".csv", "");
		// helper vai guardar os conteudos do ficheiro tirando a primeira lina

		rf.nextLine(); // ignora a primeira linha, que causa problemas com o gnuplot
		while (rf.hasNextLine()) {
			strHelper += rf.nextLine();
			strHelper += "\n";
		}
		fw.write(strHelper);

		// h vai guardar todos os comandos que vao ser corridos pelo gnuplot
		h = "set terminal pngcairo size 1280,960 fontscale 1.6\nset output \"" + imageDir + "/" + fileName + ".png\"\n";
		h += "set ylabel \"Population\" rotate; set xlabel \"Days\"; set key right top\n";
		h += "set datafile separator \";\"\n";
		h += "plot \"outputExtra.csv\" using 1:2 with linespoints title \"Susceptible\", ";
		h += "\"outputExtra.csv\" using 1:3 with linespoints title \"Infected\", ";
		h += "\"outputExtra.csv\" using 1:4 with linespoints title \"Recovered\"\n";

		fw2.write(h);
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
		helper.delete();
		secHelper.delete();
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

	public static void eulerMethod(File dir, String fileWrite, double beta, double gama, double ro, double alpha, int days, int population, double h) throws IOException {
		double matrixSIR[][] = new double[3][days];
		// matriz permite utilizar e criar apenas uma variavel para guardar todos os valores necessarios
		matrixSIR[1][0] = 1;
		matrixSIR[2][0] = 0;
		matrixSIR[0][0] = population - matrixSIR[1][0] - matrixSIR[2][0];

		double sirNext[] = new double[3], sirPrev[] = {matrixSIR[0][0], matrixSIR[1][0], matrixSIR[2][0]};

		for (int i = 1; i < days; i++) {
			// este segundo for efetua o calculo necessario de steps entre cada dia
			for (double j = 0; j < 1 / h; j++) {
				sirNext[0] = sirPrev[0] + caculateSusceptible(h, beta, sirPrev[0], sirPrev[1]);
				sirNext[1] = sirPrev[1] + caculateInfected(h, beta, gama, ro, alpha, sirPrev[0], sirPrev[1], sirPrev[2]);
				sirNext[2] = sirPrev[2] + caculateRecovered(h, beta, gama, ro, alpha, sirPrev[0], sirPrev[1], sirPrev[2]);

				sirPrev[0] = sirNext[0];
				sirPrev[1] = sirNext[1];
				sirPrev[2] = sirNext[2];
			}

			matrixSIR[0][i] = sirNext[0];
			matrixSIR[1][i] = sirNext[1];
			matrixSIR[2][i] = sirNext[2];
		}

		writeToFile(dir, fileWrite, days, matrixSIR);
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

	public static void kuttaMethod(File dir, String fileWrite, double beta, double gama, double ro, double alpha, int days, int population, double h) throws IOException {
		double matrixSIR[][] = new double[3][days];
		// matriz permite utilizar e criar apenas uma variavel para guardar todos os valores necessarios
		matrixSIR[1][0] = 1;
		matrixSIR[2][0] = 0;
		matrixSIR[0][0] = population - matrixSIR[1][0] - matrixSIR[2][0];

		double sirStart[] = {matrixSIR[0][0], matrixSIR[1][0], matrixSIR[2][0]};

		for (int i = 1; i < days; i++) {
			// este segundo for efetua o calculo necessario de steps entre cada dia
			for (double j = 0; j < 1 / h; j ++) {
				calculateKutta(h, beta, gama, ro, alpha, sirStart);
			}

			matrixSIR[0][i] = sirStart[0];
			matrixSIR[1][i] = sirStart[1];
			matrixSIR[2][i] = sirStart[2];
		}

		writeToFile(dir, fileWrite, days, matrixSIR);
	}	

	public static void main(String[] args) throws FileNotFoundException, IOException {
		/* int numDays = 30, initialPop = 1000, method = 2;
		double h = 0.1;
		String fileName = "input.csv"; */
        
		int numDays = 0, initialPop = 0, method = 0;
		double h = 0;
		String fileName = "";

		// Modo não interativo, utiliza try catch para garantir que não houve erros na introdução de valores
		if (args.length > 0) {
			if (args.length != 9) {
				System.out.println("Dados incorrectos, por favor corrija e tente novamente");
				System.exit(0);
			}
			fileName = args[0];
			try {
				method = Integer.parseInt(args[2]);
				h = Double.parseDouble(args[4].replace(",", "."));	
				initialPop = Integer.parseInt(args[6]);
				numDays = Integer.parseInt(args[8]);
			} catch (Exception e) {
				System.out.println("Dados incorrectos, por favor corrija e tente novamente.");
				System.exit(0);
			}
		}

		// Modo interativo, também utilizando try catch para garantir que os valores são aceitáveis
		else {
			Boolean correct = true;
			Scanner readUser = new Scanner(System.in);

			System.out.println("Enter the number of days");
			do {
				try {
					numDays = readUser.nextInt();
					correct = true;
				} catch (Exception e) {
					correct = handleException(readUser);
				}
			} while (!correct);

			System.out.println("Enter the starting population");
			do {
				try {
					initialPop = readUser.nextInt();
					correct = true;
				} catch (Exception e) {
					correct = handleException(readUser);
				}
			} while (!correct);

			System.out.println("Enter the value of h");
			do {
				try {
					h = readUser.nextDouble();
					correct = true;
				} catch (Exception e) {
					try {
						// Este segundo try catch tenta aceitar valores que sejam escritos com "," em vez de "."
						h = Double.parseDouble(readUser.nextLine().replaceAll(",", "."));
						correct = true;
					} catch (Exception f) {
						correct = handleException(readUser);
					}
				}
			} while (!correct);

			System.out.println("Which method do you want to use?\n1 - Euler, 2 - Runge-Kutta");
			do {
				try {
					method = readUser.nextInt();
					correct = true;
				} catch (Exception e) {
					correct = handleException(readUser);
				}
			} while (!correct);
			
			readUser.nextLine();
			System.out.println("Enter the name of the input file");
			do {
				try {
					fileName = readUser.nextLine();
					correct = true;
				} catch (Exception e) {
					correct = handleException(readUser);
				}
			} while (!correct);
			
			readUser.close();
		}
        
		
		File readFile = new File(fileName); // Criado para depois ser possível validar a sua existência na working directory
		if (!checkErrors(readFile, method, h, initialPop, numDays))
			System.out.println("Dados incorrectos, por favor tente novamente com valores aceitáveis.");
		else {
			File tableDir = new File("tables");
			tableDir.mkdir();
			File imageDir = new File("images");
			imageDir.mkdir();
			Scanner read = new Scanner(readFile);
			double arrayParameters[] = new double[4]; // 0 - beta, 1 - gama, 2 - ro, 3 - alpha
			read.nextLine(); // ignora a primeira linha
			String fileLine, fileWrite, patientName;
			while (read.hasNextLine()) {
				fileLine = read.nextLine();
				patientName = fileLine.split(";")[0]; // vai buscar so o nome do primeiro infetado
				fileWrite = getOutputFile(patientName, method, h, initialPop, numDays);
				readFromFile(fileLine, arrayParameters);
				if (method == 1)
					eulerMethod(tableDir, fileWrite, arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h);
				else
					kuttaMethod(tableDir, fileWrite, arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h);
				plotGraph(tableDir, imageDir, fileWrite);
			}
			read.close();
		}
	}
}