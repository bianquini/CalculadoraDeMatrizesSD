package br.edu.ifsc.main;

import java.rmi.Naming;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;
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
		matA = ler.lerMatriz("src/br/edu/ifsc/matrizes/matA.txt");

		System.out.println("Realizando a leitura da matriz B");
		// Realizando a leitura da matriz B
		matB = ler.lerMatriz("src/br/edu/ifsc/matrizes/matB.txt");
		
		long[][] matrizQuebrada = new long[1024][4096];
		
		System.out.println("Iniciando Calculadora Client...");
		try {
			ICalculadoraMatrizes calc = (ICalculadoraMatrizes)Naming.lookup("rmi://localhost:1099/Calculadora");
			System.out.println("\tExecutando ADD(2,2): "+calc.mult(matrizQuebrada, matB));
		
		} catch (Exception e) {
			System.err.println("\tErro: "+e.getMessage());
			System.exit(1);
		}

	}

}
