package org.luo.enterprise.util;

import java.security.SecureRandom;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;
@Component("gKEY")
public class GKEY {
	public static void main(String[] args) {
		String key = generateKey();
		System.out.println(key);
	}
	public static String generateKey(){
		String key = "";
		//Éú³Ékey
		try {
			KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
			keyGenerator.init(new SecureRandom());
			SecretKey secretKey = keyGenerator.generateKey();
			byte[] keyBytes = secretKey.getEncoded();
			key = Base64.encodeBase64String(keyBytes);
			System.out.println(key);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return key;
	}
}
