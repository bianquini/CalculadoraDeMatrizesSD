package br.edu.ifsc.interfaces;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ICalculadoraMatrizes extends Remote {

	public long[][] mult(long[][] fragA, long[][] matrizB) throws RemoteException;
}
