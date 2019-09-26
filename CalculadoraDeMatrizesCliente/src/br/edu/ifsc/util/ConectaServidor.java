package br.edu.ifsc.util;

import java.rmi.Naming;
import java.util.ArrayList;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;

/**
 * Realiza a conex�o com o servidor para realizar as multiplica��es
 * 
 * @author Tiago
 *
 */

public class ConectaServidor {

	private long[][] matriz = new long[1046][4096];

	private ArrayList<String> fila;

	/**
	 * 
	 * Realiza a conex�o com o servidor para realizar a multiplica��o das matrizes
	 * 
	 * @param caminho        - Caminho de onde est� localizado o servidor
	 * @param matrizQuebrada - Matriz contendo uma parte da matriz A
	 * @param matB           - Matriz B que ser� multiplicada
	 * 
	 * @return matriz multiplicada com tamanho 1056x4096
	 * @throws Exce��o no caso de erro na multiplica��o da matriz
	 * 
	 */
	public long[][] conectar(String caminho, long[][] matrizQuebrada, long[][] matB) {
		new Thread() {
			@Override
			public void run() {
				try {
					fila.add(caminho);
					ICalculadoraMatrizes calc;
					System.setSecurityManager(new SecurityManager());
					calc = (ICalculadoraMatrizes) Naming.lookup(caminho);
					matriz = calc.mult(matrizQuebrada, matB);
					fila.remove(caminho);
					// return matriz;
				} catch (Exception e) {
					System.err.println("\tErro no Servidor: " + e.getMessage());
					System.exit(1);
				}
				// return matriz;
			}
		}.start();
		return matriz;
	}

	public ArrayList<String> getFila() {
		return fila;
	}

	public void setFila(ArrayList<String> fila) {
		this.fila = fila;
	}

}
