package br.edu.ifsc.main;

import br.edu.ifsc.util.LeituraDados;

public class Main {

	private final static int lin = 4096;
	private final static int col = 4096;

	private static long[][] matA = new long[lin][col];
	private static long[][] matB = new long[lin][col];
	private static long[][] matC = new long[lin][col];

	public static void main(String[] args) {
		LeituraDados ler = new LeituraDados();

		// Realizando a leitura da matriz A
		System.out.println("Realizando a leitura da matriz A");
		matA = ler.lerMatriz("src/matA.txt");

		System.out.println("Realizando a leitura da matriz B");
		// Realizando a leitura da matriz B
		matB = ler.lerMatriz("src/matB.txt");

	}

}
