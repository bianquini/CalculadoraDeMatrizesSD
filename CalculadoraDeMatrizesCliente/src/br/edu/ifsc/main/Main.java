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
		long[][] matrizA1 = manipula.dividirMatriz(matA, 0, 1024);
		long[][] matrizA2 = manipula.dividirMatriz(matA, 1024, 2048);
		long[][] matrizA3 = manipula.dividirMatriz(matA, 2048, 3072);
		long[][] matrizA4 = manipula.dividirMatriz(matA, 3072, 4096);

		System.out.println("Iniciando Calculadora Client...");

		/**
		 * Chama o servidor que fará a multiplicação de uma das partes da matriz
		 */
		System.out.println("Realizando a multiplicaçao da 1º parte da matriz");
		matrizA1 = conecta.conectar("192.", matrizA1, matB);

		/*System.out.println("Realizando a multiplicaçao da 2º parte da matriz");
		matrizA2 = conecta.conectar("caminho", matrizA2, matB);

		System.out.println("Realizando a multiplicaçao da 3º parte da matriz");
		matrizA3 = conecta.conectar("caminho", matrizA3, matB);

		System.out.println("Realizando a multiplicaçao da 4º parte da matriz");
		matrizA4 = conecta.conectar("caminho", matrizA4, matB);

		matC = manipula.unirMatriz(matrizA1, matrizA2, matrizA3, matrizA4);
		
		/**
		 * Gravando a matriz C no arquivo matC.txt
		 
		System.out.println("Gravando a matriz C no arquivo matC.txt...");
		manipula.gravarMatriz("src/br/edu/ifsc/matrizes/matC.txt", matC);
		
		/**
		 * Gerando o arquivo MD5
		 
		System.out.println("Gerando o arquivo MD5");
		md5.gerarMD5();
		*/

	}

}
