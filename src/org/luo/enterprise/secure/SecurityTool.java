package org.luo.enterprise.secure;

import java.security.Key;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.luo.enterprise.dao.KdcMapperDao;
import org.springframework.stereotype.Component;
/*���ܹ�����*/
@Component
public class SecurityTool {
	@Resource
	private KdcMapperDao dao;
	public void setDao(KdcMapperDao dao) {
		this.dao = dao;
	}
	//ע�������Կ�Ĺ�����
	@Resource
	private DecodeSecretKey decodeSecretKey;
	public void setDecodeSecretKey(DecodeSecretKey decodeSecretKey) {
		this.decodeSecretKey = decodeSecretKey;
	}

	/*���ܹ���*/
	public String encryptAES(String tname,String data){
		//����
		try {
			//��ȡ���ݿ��е���Կ����
			String sk = dao.findSK(tname);
			//ʹ�ý�����Կ�������descodeKey���������ݿ��ȡ����Կ���Ľ���,֮�����̰�ԭ�߼����м���
			sk = decodeSecretKey.decodeKey(sk, tname);
			
			byte[] keyBytes = Base64.decodeBase64(sk);
			//ת����Կ
			Key converseKey = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.ENCRYPT_MODE, converseKey);
			byte [] result = cipher.doFinal(data.getBytes());
			data= Base64.encodeBase64String(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return data;
	}
	
	/**���ܹ���*/
	public String decryptAES(String tname,String data){
		//�������ַ���ת��Ϊ�ֽ�����
		byte[] result = Base64.decodeBase64(data);
		try {
			//��ȡ���ݿ��е���Կ����
			String sk = dao.findSK(tname);
			//ʹ�ý�����Կ�������descodeKey���������ݿ�����Կ���Ľ���,֮�����̰�ԭ�߼����н���
			sk = decodeSecretKey.decodeKey(sk, tname);
//			System.out.println(sk);
			byte[] keyBytes = Base64.decodeBase64(sk);
			//ת����Կ
			Key converseKey = new SecretKeySpec(keyBytes, "AES");
			//����
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, converseKey);
			result = cipher.doFinal(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new String(result);
	}
	/**md5����*/
	public String encryptMD5(String pwd){
		return DigestUtils.md5Hex(pwd);
	}
	
	
}
