package com.chornobuk.web.model;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;

public class HashingAlgorithm {
	public static String cryptPassword(String password, String salt) {
		if(password == null || salt == null || password.isEmpty() || salt.isEmpty())
			return null;
		byte[] hash = null;
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
			SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			hash = keyFactory.generateSecret(spec).getEncoded();
		} catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
		}
		if (hash == null)
			return "";
		else return new String(hash, StandardCharsets.UTF_8);
	}
	public static String getSalt() {
		SecureRandom sr = new SecureRandom();
		byte[] saltBytes = new byte[16];
		sr.nextBytes(saltBytes);
		return  new String(saltBytes, StandardCharsets.UTF_8);
	}
}
