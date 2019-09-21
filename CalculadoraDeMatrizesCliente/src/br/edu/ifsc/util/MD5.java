package br.edu.ifsc.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.security.MessageDigest;

/**
 * Realiza a geração do arquivo MD5
 * 
 * @author Tiago
 *
 */

public class MD5 {

	/**
	 * 
	 * Realiza a conversão de um arquivo em um array de bytes
	 * 
	 * @param file - Arquivo que será convertido
	 * 
	 * @return array de bytes
	 * @throws Exceção no caso de erro na conversão
	 * 
	 */
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

	/**
	 * 
	 * Realiza a conversão de um array de bytes em um sequência de caracteres
	 * hexadecimal
	 * 
	 * @param bytes - Array de bytes que será convertido
	 * 
	 * @return uma sequência de caracteres hexadecimal
	 * 
	 */
	public String toHexFormat(final byte[] bytes) {
		final StringBuilder sb = new StringBuilder();
		for (byte b : bytes) {
			sb.append(String.format("%02x", b));
		}
		return sb.toString();
	}

	/**
	 * 
	 * Gera o arquivo MD5
	 * 
	 */
	public void gerarMD5() {
		try {
			File matCFile = new File("src/br/edu/ifsc/matrizes/matC.txt");
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
