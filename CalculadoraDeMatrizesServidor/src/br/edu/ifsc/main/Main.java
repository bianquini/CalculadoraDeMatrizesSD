package br.edu.ifsc.main;

import java.rmi.Naming;

import br.edu.ifsc.util.CalculadoraMatrizesServidor;

public class Main {

	public static void main(String[] args) {
		System.out.println("Iniciando CalculadoraServerMatriz...");
		try {
			// Inicia o gerenciador de segurança
			System.out.println("\tIniciando o gerenciador de segurança...");
			System.setSecurityManager(new SecurityManager());

			// Instancia o objeto localmente
			System.out.println("\tInstanciado o objeto localmente...");
			CalculadoraMatrizesServidor calc = new CalculadoraMatrizesServidor();

			// Registra o objeto para acesso remoto
			System.out.println("\tRegistrando o objeto para acesso remoto...");

//			System.setProperty("java.rmi.server.hostname", "10.151.33.80");
//			LocateRegistry.createRegistry(1098);

			Naming.rebind("rmi://localhost:1099/Calculadora", calc);

			// Aguardando requisições
			System.out.println("\tAguardando requisições...");
		} catch (Exception e) {
			System.err.println("Erro: " + e.getMessage());
			System.exit(1);
		}

	}

}
