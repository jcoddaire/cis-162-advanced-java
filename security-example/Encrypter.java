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

import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Random;

import javax.crypto.Cipher;
import javax.crypto.CipherOutputStream;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;

public class Encrypter {
	
	/**
	 * This object instantiates an Encrypter object that can be used to encrypt data in a text file.
	 * The default encryption method is AES (128 bit key). The cipher uses CBC/PKCS5Padding.
	 */
	public Encrypter(){
	}
	
	/**
	 * This method takes a given file and encrypts it with AES and CBC. The comments are for an academic requirement.
	 * @param fileName - the name of the file to encrypt.
	 */
	public void Encrypt(String fileName){
	
		//Generate the key.
		SecretKey key = GenerateRandomKey("AES");
		
		//Initialize the IV.
		byte[] ivBytes = GenerateNewIvBytes();
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		
		//Generate the cipher.
		Cipher cipher = GenerateNewCipher("AES/CBC/PKCS5Padding", key, iv);
		
		//Read the contents of the file.
		String fileContents = ReadPlaintextFromFile("test.txt");
		
		//Encrypt the plaintext.
		Encrypt(fileContents, cipher, "encrypted.txt");
		
		//write the key and IV to separate files.
		WriteToFile(key.getEncoded(), "key.txt");
		WriteToFile(iv.getIV(), "iv.txt");
		
	}
	
	/**
	 * This method encrypts a given file with the given encryption algorithm and cipher algorithm.
	 * @param fileName - the file to encrypt.
	 * @param keyEncryptionAlgorithm - the key encryption algorithm to use. Example: AES
	 * @param cipherAlgorithm - the cipher algorithm to use. Example: AES/CBC/PKCS5Padding
	 */
	public void Encrypt(String fileName, String keyEncryptionAlgorithm, String cipherAlgorithm){
		
		SecretKey key = GenerateRandomKey(keyEncryptionAlgorithm);
		byte[] ivBytes = GenerateNewIvBytes();
		IvParameterSpec iv = new IvParameterSpec(ivBytes);
		Cipher cipher = GenerateNewCipher(cipherAlgorithm, key, iv);
		String fileContents = ReadPlaintextFromFile("test.txt");
		Encrypt(fileContents, cipher, "encrypted.txt");
		
		WriteToFile(key.getEncoded(), "key.txt");
		WriteToFile(iv.getIV(), "iv.txt");
		
	}
	
	/**
	 * This method generates a random key and returns it as a SecretKey object.
	 * @param algorithm - the algorithm the key will use.
	 */
	private SecretKey GenerateRandomKey(String algorithm){

		KeyGenerator keyGen;
		SecretKey key = null;
		try {
			keyGen = KeyGenerator.getInstance(algorithm);
			keyGen.init(128);//consider 192 or 256
			key = keyGen.generateKey();
		} 
		catch (NoSuchAlgorithmException e) {
			System.out.println("The algorithm, " + algorithm + ", does not exist!");
			e.printStackTrace();
		}
		
		return key;
	}
	
	/**
	 * This method generates a random byte array. The byte array is 16 bytes long.
	 * @return byte[]
	 */
	private byte[] GenerateNewIvBytes(){
		
		byte[] result = new byte[16];
		Random randomGenerator = new Random();
		randomGenerator.nextBytes(result);
		return result;
	}
	
	/**
	 * This method generates a new cipher based on the type, key, and IV.
	 * @param cipherType - the type of cipher to be returned. For example, AES/CBC/PKCS5Padding is acceptable.
	 * @param key - the key to encrypt with.
	 * @param iv - the initialization vector to use.
	 * @return - A Cipher object.
	 */
	private Cipher GenerateNewCipher(String cipherType, SecretKey key, IvParameterSpec iv){
		Cipher cipher = null;
		try {
			cipher = Cipher.getInstance(cipherType);
			cipher.init(Cipher.ENCRYPT_MODE, key, iv);
		} 
		catch (InvalidKeyException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | NoSuchPaddingException e) {
			System.out.println("The cipher failed to initialize.");
			e.printStackTrace();
		}
		return cipher;
	}
	
	/**
	 * This method encrypts a message with a specified cipher to a specified fileName.
	 * @param message - the plaintext to encrypt.
	 * @param cipher - the ciper to use in the encryption method.
	 * @param fileName - the name of the file to write encrypted data to.
	 */
	private void Encrypt(String message, Cipher cipher, String fileName){
		
		File file = new File(fileName);
		FileOutputStream fileStream = null;
		try {
			fileStream = new FileOutputStream(file);
		} 
		catch (FileNotFoundException e1) {
			System.out.println("The file, "+fileName+", does not exist or could not be created.");
			e1.printStackTrace();
		}
		CipherOutputStream cipherStream = new CipherOutputStream(fileStream, cipher);
		try {
			cipherStream.write(message.getBytes());
			cipherStream.close();
			fileStream.close();
		} 
		catch (IOException e) {
			System.out.println("There was an error writing the message to the encrypted file.");
			e.printStackTrace();
		}
	}
	
	/**
	 * This method reads data from a file and returns it as a String object.
	 * @param fileName - the file to read data from
	 * @return String object
	 */
	private String ReadPlaintextFromFile(String fileName){

		String result = "";
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(fileName));
			String line = "";
			while ((line = reader.readLine()) != null) {
			    result = result + line;
			}
			reader.close();
		} 
		catch (FileNotFoundException e) {
			System.out.println("The file, "+ fileName + ", was not found.");
			e.printStackTrace();
		} catch (IOException e) {
			System.out.println("There was a problem reading the file " + fileName + ".");
			e.printStackTrace();
		}
		return result;
		
	}
	
	/**
	 * This method writes data from a byte array to a specified file name.
	 * @param valueToWrite - the byte array object to write to a file.
	 * @param fileName - the file name for the file.
	 */
	private void WriteToFile (byte[] valueToWrite, String fileName){
		
		try {
			FileOutputStream output = new FileOutputStream(new File(fileName));
			org.apache.commons.io.IOUtils.write(valueToWrite, output);
			System.out.println("The contents of " + fileName + " were written successfully.");
		} 
		catch (FileNotFoundException e) {
			System.out.println("For some odd reason, the file, "+ fileName + ", was not found. That's really odd because I should have created it.");
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
}