package br.edu.ifsc.main;

import br.edu.ifsc.util.ConectaServidor;
import br.edu.ifsc.util.MD5;
import br.edu.ifsc.util.ManipulaMatrizes;

public class Main {

	private final static int lin = 4096;
	private final static int col = 4096;

	private static long[][] matA = new long[lin][col];
	private static long[][] matB = new long[lin][col];
	private static long[][] matC = new long[lin][col];

	public static void main(String[] args) {
		ManipulaMatrizes manipula = new ManipulaMatrizes();
		ConectaServidor conecta = new ConectaServidor();
		MD5 md5 = new MD5();

		/**
		 * Realiza a leitura das matrizes A e B
		 */
		System.out.println("Realizando a leitura da matriz A...");
		matA = manipula.lerMatriz("src/br/edu/ifsc/matrizes/matA.txt");

		System.out.println("Realizando a leitura da matriz B...");
		matB = manipula.lerMatriz("src/br/edu/ifsc/matrizes/matB.txt");

		/**
		 * Realiza a divisão da matriz A em quatro partes
		 */
		System.out.println("Realizando a divisão da matriz A em quatro partes...");
		long[][] matrizA1 = manipula.dividirMatriz(matA, 0, 1023);
		long[][] matrizA2 = manipula.dividirMatriz(matA, 1024, 2047);
		long[][] matrizA3 = manipula.dividirMatriz(matA, 2048, 3071);
		long[][] matrizA4 = manipula.dividirMatriz(matA, 3072, 4095);

		System.out.println("Iniciando Calculadora Cliente...");

		/**
		 * Chama o servidor que fará a multiplicação de uma das partes da matriz
		 */
		System.out.println("Realizando a multiplicação da 1ª parte da matriz");
		matrizA1 = conecta.conectar("rmi://10.151.33.80:1099/Calculadora", matrizA1, matB);

		System.out.println("Realizando a multiplicação da 2ª parte da matriz");
		matrizA2 = conecta.conectar("rmi://10.151.33.112:1099/Calculadora", matrizA2, matB);

		System.out.println("Realizando a multiplicação da 3ª parte da matriz");
		matrizA3 = conecta.conectar("rmi://10.151.33.134:1099/Calculadora", matrizA3, matB);

		System.out.println("Realizando a multiplicação da 4ª parte da matriz");
		matrizA4 = conecta.conectar("rmi://10.151.33.162:1099/Calculadora", matrizA4, matB);

		System.out.println("Aguardando respostas...");
		while (conecta.getFila().size() != 0) {
			System.out.println("Processando");
		}

		System.out.println("Unindo matrizes....");
		matC = manipula.unirMatriz(matrizA1, matrizA2, matrizA3, matrizA4);

		/**
		 * Gravando a matriz C no arquivo matC.txt
		 */

		System.out.println("Gravando a matriz C no arquivo matC.txt...");
		manipula.gravarMatriz("src/br/edu/ifsc/matrizes/matC.txt", matC);

		/**
		 * Gerando o arquivo MD5
		 */

		System.out.println("Gerando o arquivo MD5");
		md5.gerarMD5();

	}

}
