package br.edu.ifsc.util;

import java.rmi.Naming;
import java.util.ArrayList;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;

/**
 * Realiza a conexão com o servidor para realizar as multiplicações
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
	 * Realiza a conexão com o servidor para realizar a multiplicação das matrizes
	 * 
	 * @param caminho        - Caminho de onde estará localizado o servidor
	 * @param matrizQuebrada - Matriz contendo uma parte da matriz A
	 * @param matB           - Matriz B que será multiplicada
	 * 
	 * @return matriz multiplicada com tamanho 1056x4096
	 * @throws Exceção no caso de erro na multiplicação da matriz
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
