import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class readWrite {
    
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
}