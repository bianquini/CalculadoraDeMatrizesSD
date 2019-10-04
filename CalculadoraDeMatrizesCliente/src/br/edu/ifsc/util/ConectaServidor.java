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

	public ConectaServidor() {
		fila = new ArrayList<>();
	}

	/**
	 * 
	 * Realiza a conex�o com o servidor para realizar a multiplica��o das matrizes
	 * 
	 * @param caminho        - Caminho de onde estar� localizado o servidor
	 * @param matrizQuebrada - Matriz contendo uma parte da matriz A
	 * @param matB           - Matriz B que ser� multiplicada
	 * 
	 * @return matriz multiplicada com tamanho 1056x4096
	 * @throws Exce��o no caso de erro na multiplica��o da matriz
	 * 
	 */
	public long[][] conectar(String caminho, long[][] matrizQuebrada, long[][] matB) {
		fila.add(caminho);
		System.out.println("Tamanho da Fila - Add: " + fila.size());
		new Thread() {
			@Override
			public void run() {
				try {

					ICalculadoraMatrizes calc;
					calc = (ICalculadoraMatrizes) Naming.lookup(caminho);
					matriz = calc.mult(matrizQuebrada, matB);
					fila.remove(caminho);
					System.out.println("Tamanho da Fila - Remove: " + fila.size());
				} catch (Exception e) {
					System.err.println("\tErro no Servidor: " + e.getMessage());
					System.exit(1);
				}
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
