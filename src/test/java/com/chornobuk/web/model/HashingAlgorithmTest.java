package com.chornobuk.web.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.*;

@RunWith(Parameterized.class)
public class HashingAlgorithmTest {

	private String password;
	private String salt;

	@Parameterized.Parameters
	public static Collection<Object[]> data() {
		return Arrays.asList(new Object[][]{
				{"Password", "-098a;sjf;[,243"},
				{"oM2f^RJ#4sDnHF@LLMrb", "tbee8dBR^npJ9S9jJtBR"},
				{"HiyMrktE57^YKqAKvT4X", "Bf3XLpyWJLV#BAoZ2fkJ"}
		});
	}

	public HashingAlgorithmTest(String password, String salt) {
		this.password = password;
		this.salt = salt;
	}


	@Test
	public void cryptPassword() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedPassword = HashingAlgorithm.cryptPassword(password, salt);
		byte[] hash = null;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65536, 128);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		hash = keyFactory.generateSecret(spec).getEncoded();
		assertEquals(new String(hash, StandardCharsets.UTF_8), encryptedPassword);
	}

	@Test
	public void cryptPasswordFalse() throws NoSuchAlgorithmException, InvalidKeySpecException {
		String encryptedPassword = HashingAlgorithm.cryptPassword(password, salt);
		byte[] hash = null;
		KeySpec spec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), 65535, 128);
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
		hash = keyFactory.generateSecret(spec).getEncoded();
		assertNotEquals(new String(hash, StandardCharsets.UTF_8), encryptedPassword);
	}

	@Test
	public void cryptPasswordWithNullPassword() {
		String encryptedPassword = HashingAlgorithm.cryptPassword(null, salt);
		assertNull(encryptedPassword);
	}

	@Test
	public void cryptPasswordWithNullSalt() {
		String encryptedPassword = HashingAlgorithm.cryptPassword(password, null);
		assertNull(encryptedPassword);
	}

	@Test
	public void cryptPasswordWithNullPasswordAndSalt() {
		String encryptedPassword = HashingAlgorithm.cryptPassword(null, null);
		assertNull(encryptedPassword);
	}

	@Test
	public void cryptPasswordWithEmptyPassword() {
		String encryptedPassword = HashingAlgorithm.cryptPassword("", salt);
		assertNull(encryptedPassword);
	}

	@Test
	public void cryptPasswordWithEmptySalt() {
		String encryptedPassword = HashingAlgorithm.cryptPassword(password, "");
		assertNull(encryptedPassword);
	}

	@Test
	public void cryptPasswordWithEmptyPasswordAndSalt() {
		String encryptedPassword = HashingAlgorithm.cryptPassword("", "");
		assertNull(encryptedPassword);
	}
}