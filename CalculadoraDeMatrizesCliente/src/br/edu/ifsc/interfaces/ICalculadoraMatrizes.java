package br.edu.ifsc.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * Interface com m�todos da calculadora de matrizes
 * 
 * @author Tiago
 *
 */
public interface ICalculadoraMatrizes extends Remote {

	/**
	 * Realiza a multiplica��o de duas matrizes
	 * 
	 * @param matrizQuebrada - Matriz contendo uma parte da matriz A
	 * @param matB           - Matriz B que ser� multiplicada
	 * 
	 * @return matriz multiplicada com tamanho 1056x4096
	 * 
	 */
	public long[][] mult(long[][] matrizQuebrada, long[][] matrizB) throws RemoteException;

}
