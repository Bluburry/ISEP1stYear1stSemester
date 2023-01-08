import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class start {
	
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
					correct = extras.handleException(readUser);
				}
			} while (!correct);

			System.out.println("Enter the starting population");
			do {
				try {
					initialPop = readUser.nextInt();
					correct = true;
				} catch (Exception e) {
					correct = extras.handleException(readUser);
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
						correct = extras.handleException(readUser);
					}
				}
			} while (!correct);

			System.out.println("Which method do you want to use?\n1 - Euler, 2 - Runge-Kutta");
			do {
				try {
					method = readUser.nextInt();
					correct = true;
				} catch (Exception e) {
					correct = extras.handleException(readUser);
				}
			} while (!correct);
			
			readUser.nextLine();
			System.out.println("Enter the name of the input file");
			do {
				try {
					fileName = readUser.nextLine();
					correct = true;
				} catch (Exception e) {
					correct = extras.handleException(readUser);
				}
			} while (!correct);
			
			readUser.close();
		}
        
		
		File readFile = new File(fileName); // Criado para depois ser possível validar a sua existência na working directory
		if (!extras.checkErrors(readFile, method, h, initialPop, numDays))
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
				fileWrite = extras.getOutputFile(patientName, method, h, initialPop, numDays);
				readWrite.readFromFile(fileLine, arrayParameters);
				if (method == 1)
					calcs.eulerMethod(tableDir, fileWrite, arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h);
				else
					calcs.kuttaMethod(tableDir, fileWrite, arrayParameters[0], arrayParameters[1], arrayParameters[2], arrayParameters[3], numDays, initialPop, h);
				graph.plotGraph(tableDir, imageDir, fileWrite);
			}
			read.close();
		}
	}
}
