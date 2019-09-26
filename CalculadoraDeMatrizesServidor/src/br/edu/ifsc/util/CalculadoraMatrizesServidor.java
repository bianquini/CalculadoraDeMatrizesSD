package br.edu.ifsc.util;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;

public class CalculadoraMatrizesServidor extends UnicastRemoteObject implements ICalculadoraMatrizes {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CalculadoraMatrizesServidor() throws RemoteException {
	}

	@Override
	public long[][] mult(long[][] fragA, long[][] matrizB) throws RemoteException {

		System.out.println("Calculando matriz...");

		long[][] result = new long[1024][4096];
		for (int i = 0; i < fragA.length; i++) {
			for (int j = 0; j < matrizB.length; j++) {
				for (int k = 0; k < matrizB.length; k++) {
					result[i][j] += fragA[i][k] * matrizB[k][j];
				}
			}
		}
		System.out.println("Finalizando calculo....");
		return result;
	}

}