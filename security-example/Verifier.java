/*
 * Security program concept.
 * This program verifies the signature of text files.
 * 
 * Author:	Jacob Coddaire
 * Date:	26 September 2013
 * Version: 1.1
 * 
 * CIS 458-01
 * Fall 2013
 */

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.X509EncodedKeySpec;


public class Verifier {
	
	/**
	 * Initializes a new Verifier object to be used with signature verification.
	 */
	public Verifier(){
		
	}

	/**
	 * Verifies the integrity of a given file name by checking the key and signature file.
	 * @param signedFileName - the signed file to use.
	 * @param publicKeyInDerFormat - the public key to use, this should be in DER format.
	 * @param plainTextFileName - the plain text file to validate.
	 * @return
	 */
	public String verify(String signedFileName, String publicKeyInDerFormat, String plainTextFileName)
	{
		String result = "";
		PublicKey key = GeneratePublicKeyFromFile(publicKeyInDerFormat);
		
		//initialize the signature object.
		Signature sig = null;
		try {
			sig = Signature.getInstance("SHA1withRSA");
			sig.initVerify(key);
		} catch (NoSuchAlgorithmException | InvalidKeyException e) {
			System.out.println("There was a problem with the algorithm for the signature.");
			e.printStackTrace();
		}
		
		//read in the plaintext file and buffer the sig object up.
		FileInputStream datafis = null;
		BufferedInputStream bufin = null;
		try {
			datafis = new FileInputStream(plainTextFileName);
			bufin = new BufferedInputStream(datafis);
			byte[] buffer = new byte[1024];
			int len;
			while (bufin.available() != 0) {
			    len = bufin.read(buffer);
			    sig.update(buffer, 0, len);
			}
			bufin.close();
		} catch (SignatureException | IOException e) {
			e.printStackTrace();
		}
		//test to see if the signed file is equal to the plaintext.
		boolean isMatch = false;
		try {
			 isMatch = sig.verify(ReadFromFile(signedFileName));
		} catch (SignatureException e) {
			e.printStackTrace();
		}
		
		//print an appropriate message.
		if (isMatch == true){
			result = "The file is signed successfully.";
		}else{
			result = "The file is corrupted. Do not trust it. A third party could have modified the file you provided.";
		}
		
		return result;
		
	}
	
	/**
	 * Generates a public key from a given file. The file should be a public key in DER format.
	 * This method uses RSA as the encryption algorithm.
	 * @param keyFileName - the file name of the key.
	 * @return PublicKey object.
	 */
	private PublicKey GeneratePublicKeyFromFile(String keyFileName){
		
		PublicKey key = null;
		X509EncodedKeySpec encodedKey = new X509EncodedKeySpec(ReadFromFile(keyFileName));             
		KeyFactory fac = null;
		try {
			fac = KeyFactory.getInstance("RSA");
			 key = fac.generatePublic(encodedKey);
		} 
		catch (NoSuchAlgorithmException e) {
			System.out.println("The encryption algorithm does not exist.");
			e.printStackTrace();
		} 
		catch (InvalidKeySpecException e) {
			System.out.println("The key did not work.");
			e.printStackTrace();
		}
		return key;
	}
	
	/**
	 * This method reads text from a given file name and returns it as a byte array.
	 * @param fileName - the file to read data from.
	 * @return byte []
	 */
	private byte [] ReadFromFile(String fileName){
		
		File file = new File(fileName);
		byte [] result = new byte[]{};
		
		try {
			result = org.apache.commons.io.FileUtils.readFileToByteArray(file);
		} 
		catch (IOException e) {
			System.out.println("There was an error reading the file " +fileName);
			e.printStackTrace();
		}
		
		return result;
	}
}