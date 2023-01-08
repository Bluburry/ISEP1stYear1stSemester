import java.util.Scanner;
import java.io.File;

public class extras {

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
}
