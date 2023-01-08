import java.util.Scanner;
import java.lang.Runtime;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class graph {

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
		h += "set xlabel \"Days\"; set ylabel \"Population\" rotate by 90; set key right top\n";
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
}