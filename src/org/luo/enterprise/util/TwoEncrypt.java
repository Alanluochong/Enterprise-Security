package org.luo.enterprise.util;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

/**
 * ��ԭ��һ��������Կ����aes�ٴμ���
 * @author luo_c
 *
 */
public class TwoEncrypt {
	public static void main(String[] args) {
		//һ��������Կ
		String data = "hW3w8+oYAbg/N8cv+3wt1g==";
		//����������Կ
		String sk = "Snmm3GEVqHrGpCmOCSublg==";
		byte[] keyBytes = Base64.decodeBase64(sk);
		//ת����Կ
		Key converseKey = new SecretKeySpec(keyBytes, "AES");
		//����
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
