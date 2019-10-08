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

	private long[][] matriz;

	private ArrayList<String> fila;
	private long[][] respA;
	private long[][] respB;
	private long[][] respC;
	private long[][] respD;

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
	public void conectar(String caminho, long[][] matrizQuebrada, long[][] matB, int tipo) {
		matriz = new long[1046][4096];
		fila.add(caminho);
		new Thread() {
			@Override
			public void run() {
				try {
					ICalculadoraMatrizes calc;
					calc = (ICalculadoraMatrizes) Naming.lookup(caminho);
					matriz = calc.mult(matrizQuebrada, matB);
					if (tipo == 1) {
						respA = matriz;
					} else if (tipo == 2) {
						respB = matriz;
					} else if (tipo == 3) {
						respC = matriz;
					} else {
						respD = matriz;
					}
					fila.remove(caminho);
					System.out.println("Tamanho da Fila - Remove: " + fila.size());
				} catch (Exception e) {
					System.err.println("\tErro ao conectar com o Servidor: " + e.getMessage());
					System.exit(1);
				}
			}
		}.start();

	}

	public ArrayList<String> getFila() {
		return fila;
	}

	public void setFila(ArrayList<String> fila) {
		this.fila = fila;
	}

	public long[][] getRespA() {
		return respA;
	}

	public void setRespA(long[][] respA) {
		this.respA = respA;
	}

	public long[][] getRespB() {
		return respB;
	}

	public void setRespB(long[][] respB) {
		this.respB = respB;
	}

	public long[][] getRespC() {
		return respC;
	}

	public void setRespC(long[][] respC) {
		this.respC = respC;
	}

	public long[][] getRespD() {
		return respD;
	}

	public void setRespD(long[][] respD) {
		this.respD = respD;
	}

}
