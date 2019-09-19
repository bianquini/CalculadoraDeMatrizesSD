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
    public long[] multiplica(long[] linha, long[][] matB) {

        long[] result = new long[4096];
        for (int i = 0; i < linha.length; i++) {
            for (int j = 0; j < matB.length; j++) {
                for (int k = 0; k < matB.length; k++) {
                    result[i] += linha[i] * matB[k][j];
                }
            }
        }
        return result;
    }
}