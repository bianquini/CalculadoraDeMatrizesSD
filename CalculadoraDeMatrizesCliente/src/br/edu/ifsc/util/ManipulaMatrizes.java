package br.edu.ifsc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

/**
 * Realiza as manipulações necessárias sobre as matrizes
 * 
 * @author Tiago
 *
 */

public class ManipulaMatrizes {

	private int lin = 4096;
	private int col = 4096;

	private long[][] matriz = new long[lin][col];

	/**
	 * 
	 * Realiza a leitura de um arquivo .txt e preenche a matriz com os dados do
	 * arquivo, retornando uma matriz preenchida
	 * 
	 * @param caminhoMatriz - Caminho de onde está o arquivo necessário para
	 *                      preencher a matriz
	 * 
	 * @return matriz preenchida com os dados do arquivo .txt
	 * @throws Exceção no caso de erro de leitura do arquivo .txt
	 * 
	 */
	public long[][] lerMatriz(String caminhoMatriz) {
		int l, c;
		try {
			FileReader file = new FileReader(caminhoMatriz);
			BufferedReader bufFile = new BufferedReader(file);
			String line = bufFile.readLine();
			l = c = 0;
			while (line != null) {
				matriz[l][c] = Integer.parseInt(line);
				c++;
				if (c >= col) {
					l++;
					c = 0;
				}
				line = bufFile.readLine();
			}
			bufFile.close();
			return matriz;
		} catch (Exception e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}
		return matriz;
	}

	/**
	 * 
	 * Realiza a gravação de um arquivo .txt e preenche esse arquivo com a matriz
	 * que foi passada
	 * 
	 * @param caminhoMatriz - Caminho de onde o arquivo .txt será salvo
	 * @param mat           - Matriz que será salva no arquivo .txt
	 * 
	 * @throws Exceção no caso de erro de gravação do arquivo .txt
	 * 
	 */
	public void gravarMatriz(String caminhoMatriz, long[][] mat) {
		try {
			File fOut = new File(caminhoMatriz);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
			for (int i = 0; i < lin; i++) {
				for (int j = 0; j < col; j++) {
					writer.write(String.valueOf(mat[i][j]));
					if (!((i == lin - 1) && (j == col - 1))) {
						writer.newLine();
					}
				}
			}
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}
	}

	/**
	 * 
	 * Realiza a divisão da matriz conforme o tamanho especificado
	 * 
	 * @param matrizA - Matriz que será dividida
	 * @param linha   - Indice da linha de inicio,ou seja, de onde a matriz começará
	 *                a ser dividida
	 * 
	 * @return uma matriz contendo uma parte da matrizA
	 * 
	 */
	public long[][] dividirMatriz(long[][] matrizA, int linha, int nLinha) {
		long[][] mat = new long[1024][4096];
		int lin = 0;
		for (int l = linha; l < nLinha; l++) {
			for (int c = 0; c < 4096; c++) {
				mat[lin][c] = matrizA[l][c];
			}
			lin++;
		}
		return mat;
	}

	/**
	 * 
	 * Realiza a união de quatro matrizes tranformando-a em uma
	 * 
	 * @param matrizA1 - 1º parte da matriz
	 * @param matrizA2 - 2º parte da matriz
	 * @param matrizA3 - 3º parte da matriz
	 * @param matrizA4 - 4º parte da matriz
	 * 
	 * @return uma matriz completa de tamanho 4096x4096
	 * 
	 */
	public long[][] unirMatriz(long[][] matrizA1, long[][] matrizA2, long[][] matrizA3, long[][] matrizA4) {
		long[][] matC = new long[4096][4096];
		int l2 = 0, c2 = 0;
		int l3 = 0, c3 = 0;
		int l4 = 0, c4 = 0;
		for (int l = 0; l < 4096; l++) {
			for (int c = 0; c < 4096; c++) {
				if (l < 1024) {
					System.out.println("Uniu parte 1");
					matC[l][c] = matrizA1[l][c];
				} else if (l < 2048) {
					System.out.println("Uniu parte 2");
					matC[l][c] = matrizA2[l2][c2];
					l2++;
					c2++;
				} else if (l < 3072) {
					System.out.println("Uniu parte 3");
					matC[l][c] = matrizA3[l3][c3];
					l3++;
					c3++;
				} else {
					System.out.println("Uniu parte 4");
					matC[l][c] = matrizA4[l4][c4];
					l4++;
					c4++;
				}
			}
		}
		return matC;
	}
}
