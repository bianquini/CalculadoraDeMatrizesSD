package br.edu.ifsc.util;

import br.edu.ifsc.interfaces.ICalculadoraMatrizes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class CalculadoraMatrizesServidor extends UnicastRemoteObject implements ICalculadoraMatrizes {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	

	public CalculadoraMatrizesServidor() throws RemoteException {
    }


	@Override
	public long[][] multiplica(long[][] fragA, long[][] matrizB) throws RemoteException {
		
        long[][] result = new long[4096][];
        for (int i = 0; i < fragA.length; i++) {
            for (int j = 0; j < matrizB.length; j++) {
                for (int k = 0; k < matrizB.length; k++) {
                    result[i][j] += fragA[i][k] * matrizB[k][j];
                }
            }
        }
        return result;
	}




}