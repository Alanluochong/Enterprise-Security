package org.luo.enterprise.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * 将原来一级工作密钥采用aes再次加密
 * @author luo_c
 *
 */
public class TwoEncrypt {
	public static void main(String[] args) {
		//一级工作密钥
		String data = "hW3w8+oYAbg/N8cv+3wt1g==";
		//二级加密密钥
		String sk = "Snmm3GEVqHrGpCmOCSublg==";
		byte[] keyBytes = Base64.decodeBase64(sk);
		//转换密钥
		Key converseKey = new SecretKeySpec(keyBytes, "AES");
		//加密
		try {
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, converseKey);
			byte [] result = cipher.doFinal(data.getBytes());
			data= Base64.encodeBase64String(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		System.out.println(data);
	}
}
