import java.io.File;
import java.io.IOException;
public class calcs {

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

		readWrite.writeToFile(dir, fileWrite, days, matrixSIR);
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

		readWrite.writeToFile(dir, fileWrite, days, matrixSIR);
	}
}
