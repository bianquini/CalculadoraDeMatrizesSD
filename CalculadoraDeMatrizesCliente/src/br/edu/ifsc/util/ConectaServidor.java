package br.edu.ifsc.util;

import java.rmi.Naming;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;

/**
 * Realiza a conexão com o servidor para realizar as multiplicações
 * 
 * @author Tiago
 *
 */

public class ConectaServidor {

	/**
	 * 
	 * Realiza a conexão com o servidor para realizar a multiplicação das matrizes
	 * 
	 * @param caminho        - Caminho de onde está localizado o servidor
	 * @param matrizQuebrada - Matriz contendo uma parte da matriz A
	 * @param matB           - Matriz B que será multiplicada
	 * 
	 * @return matriz multiplicada com tamanho 1056x4096
	 * @throws Exceção no caso de erro na multiplicação da matriz
	 * 
	 */
	public long[][] conectar(String caminho, long[][] matrizQuebrada, long[][] matB) {
		long[][] matriz = new long[1046][4096];
		try {
			ICalculadoraMatrizes calc = (ICalculadoraMatrizes) Naming.lookup(caminho);
			matriz = calc.mult(matrizQuebrada, matB);
			return matriz;
		} catch (Exception e) {
			System.err.println("\tErro: " + e.getMessage());
			System.exit(1);
		}
		return matriz;
	}

}
