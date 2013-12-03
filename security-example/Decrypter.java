/*
 * Encryption program concept.
 * This program encrypts text files and subsequently decrypts them.
 * 
 * Author:	Jacob Coddaire
 * Date:	23 September 2013
 * Version: 1.0
 * 
 * CIS 458-01
 * Fall 2013
 */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.CipherInputStream;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class Decrypter {
	
	public Decrypter()
	{
	}
	
	/**
	 * This constructor takes the given key, IV, and encrypted message and decrypts it to its plaintext.
	 * @param keyFileName - the symmetric key. This must be stored in a text file.
	 * @param ivFileName - the IV to use. This must be stored in a text file.
	 * @param encryptedMessageFileName - the encrypted message to decrypt.
	 */
	public void Decrypt(String keyFileName, String ivFileName, String encryptedMessageFileName){
		
		//get the key.
		SecretKey key = GenerateKeyFromFile(keyFileName);
		
		//get the IV.
		IvParameterSpec ivBytes = GenerateIVFromFile(ivFileName);
		
		//decrypt the data.
		Decrypt (encryptedMessageFileName, key, ivBytes);
	}
	
	/**
	 * This method generates a SecretKey object from a given file name. It must be stored as a text file.
	 * @param fileName - the file name to read from.
	 * @return SecretKey object
	 */
	private SecretKey GenerateKeyFromFile(String fileName){
		
		byte[] keyBytes = ReadFromFile(fileName);
		SecretKeySpec key = new SecretKeySpec(keyBytes, "AES");
		return key;
	}
	
	/**
	 * This method generates an IVParameterSpec object from a text file.
	 * @param fileName - the file name for the stored IV.
	 * @return IvParameterSpec object
	 */
	private IvParameterSpec GenerateIVFromFile(String fileName){
		
		byte[] ivBytes = ReadFromFile(fileName);
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		return iv;
	}
	
	/**
	 * This method decrypts the given encrypted message with the key and IV.
	 * @param ciphertextFileName - the file name of the encrypted (ciphertext) message.
	 * @param key - the key to use in the decryption process.
	 * @param iv - the IV to use in the decryption process.
	 */
	private void Decrypt (String ciphertextFileName, SecretKey key, IvParameterSpec iv){
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE,  key, iv);
		} 
		catch (InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | InvalidAlgorithmParameterException e) {
			System.out.println("There was a problem initializing the decryption cipher. Invalid key.");
			e.printStackTrace();
		}
		
		FileInputStream inputStream = null;
		try {
			inputStream = new FileInputStream(ciphertextFileName);
		} catch (FileNotFoundException e1) {
			System.out.println("The ciphertext file, "+ciphertextFileName+", was not found.");
			e1.printStackTrace();
		}
		CipherInputStream cipherStream = new CipherInputStream(inputStream, cipher);
		BufferedReader reader = new BufferedReader(new InputStreamReader(cipherStream));
		StringBuilder sb = new StringBuilder();
	    try {
	    	int value = 0;
	        while((value = reader.read()) != -1)
	        {
	            char c = (char)value;
	            sb.append(c);
	        }
		    reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	    File file = new File("plaintextmessage.txt");
		PrintWriter printer = null;
		try {
			printer = new PrintWriter(file);
		} catch (FileNotFoundException e) {
			System.out.println("plaintextmessage.txt was not found. That's odd because I just created it.\n\nYOU DEFIED THE LAWS OF PHYSICS.\n\nWelcome home, Neo.\n\n");
			e.printStackTrace();
		}
		
		printer.println(sb.toString());
		System.out.println("The contents of plaintextmessage.txt were written successfully.");
		
		printer.close();
		org.apache.commons.io.IOUtils.closeQuietly(inputStream);
		org.apache.commons.io.IOUtils.closeQuietly(cipherStream);	
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