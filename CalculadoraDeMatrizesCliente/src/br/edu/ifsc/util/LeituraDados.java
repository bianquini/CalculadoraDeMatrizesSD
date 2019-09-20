package br.edu.ifsc.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.security.MessageDigest;

public class LeituraDados {

	private int lin = 4096;
	private int col = 4096;

	private long[][] matriz = new long[lin][col];
	private long[][] matrizC = new long[lin][col];

	public long[][] lerMatriz(String caminhoMatriz) {
		int l, c;
		// Realizando a leitura da matriz
		try {
			FileReader file = new FileReader(caminhoMatriz);
			BufferedReader bufFile = new BufferedReader(file);

			// Lê a primeira linha
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

	public void gravarMatriz(String caminhoMatriz) {
		// Grava um arquivo com a matriz C
		try {
			File fOut = new File(caminhoMatriz);
			BufferedWriter writer = new BufferedWriter(new FileWriter(fOut));
			for (int i = 0; i < lin; i++) {
				for (int j = 0; j < col; j++) {
					writer.write(String.valueOf(matrizC[i][j]));
					if ((i == lin - 1) && (j == col - 1)) {
						continue;
					} else {
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

	// MD5
	public byte[] getFileBytes(File file) {
		int len = (int) file.length();
		byte[] sendBuf = new byte[len];
		FileInputStream inFile = null;

		try {
			inFile = new FileInputStream(file);
			inFile.read(sendBuf, 0, len);
		} catch (Exception e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}

		return sendBuf;
	}

	// Converte Hexadecimal
	public String toHexFormat(final byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	// Gera o MD5
	public void gerarMD5() {
		try {
			File matCFile = new File("src/matC.txt");
			int matCSize = (int) matCFile.length();
			byte[] matCBytes = new byte[matCSize];
			matCBytes = getFileBytes(matCFile);
			MessageDigest md = MessageDigest.getInstance("MD5");
			byte[] hash = md.digest(matCBytes);
			System.out.print("\nHash: " + toHexFormat(hash));
			System.out.print("\nGravando arquivo matC.md5...");
			File md5File = new File("src/matC.md5");
			BufferedWriter writer = new BufferedWriter(new FileWriter(md5File));
			writer.write(toHexFormat(hash) + "  matC.txt");
			writer.flush();
			writer.close();
		} catch (Exception e) {
			System.err.print("\n\tErro: " + e.getMessage());
			System.exit(1);
		}
	}
}
