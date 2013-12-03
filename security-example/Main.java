/*
 * Security program concept.
 * This program encrypts, decrypts, and verifies text files.
 * 
 * Author:	Jacob Coddaire
 * Date:	26 September 2013
 * Version: 1.1
 * 
 * CIS 458-01
 * Fall 2013
 */
public class Main {
	public static void main(String[] args) {
		
		Verifier verify = new Verifier();
		System.out.println(verify.verify("test.signed", "public.der", "test.txt"));
		
		//Encrypter encrypter = new Encrypter();
		//encrypter.Encrypt("test.txt");
		
		//Ideally, a programmer could specify a different type of encryption on-the-fly. Problem is, I have not set up a functioning method in the decryption class.
		//encrypter.Encrypt("test.txt", "AES", "AES/CBC/PKCS5Padding");
		
		//Decrypter decrypter = new Decrypter();
		//decrypter.Decrypt("key.txt", "iv.txt", "encrypted.txt");
	}
}