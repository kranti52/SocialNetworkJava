package com.myapp.crypto;

import java.security.NoSuchAlgorithmException;
import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.Key;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

import com.myapp.repository.KeyPairRepository;

public class EncryptDecrypt {
	private KeyPairRepository db;
	private static Cipher cipher;
	private static String algorithm = "DESede";
	public EncryptDecrypt() {
		this.db=new KeyPairRepository();
		try {
			cipher = Cipher.getInstance(algorithm);
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Key keyGenerator()
	{

		KeyGenerator keyGenerator;
		try {
			keyGenerator = KeyGenerator.getInstance(algorithm);
			Key secretKey = keyGenerator.generateKey();
			return secretKey;
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	public String encrypt(String plainText)throws Exception {
		byte[] plainTextByte = plainText.getBytes();
		System.out.println(plainText);
		Key secretKey=this.keyGenerator();
		cipher.init(Cipher.ENCRYPT_MODE, secretKey);
		byte[] encryptedByte = cipher.doFinal(plainTextByte);
		String encryptedText = new String(Base64.encodeBase64(encryptedByte));
		String key = new String(Base64.encodeBase64(secretKey.getEncoded()));
		this.db.createKeyPair(encryptedText,key);
		return encryptedText;
		
		
	}

	public String decrypt(String encryptedText, String secret_key) throws Exception {
		//Base64.Decoder decoder = Base64.getDecoder();
		byte[] secret = Base64.decodeBase64(secret_key.getBytes());
		System.out.println(secret_key);
		System.out.println(secret);
		SecretKey key = new SecretKeySpec(secret, 0, secret.length, algorithm);
		byte[] encryptedTextByte = Base64.decodeBase64(encryptedText.getBytes());
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
		String decryptedText = new String(decryptedByte);

		return decryptedText;
		

        
	}
}
