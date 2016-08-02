package org.luo.enterprise.secure;

import java.security.Key;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.luo.enterprise.dao.KdcMapperDao;
import org.springframework.stereotype.Component;
/*加密工具类*/
@Component
public class SecurityTool {
	@Resource
	private KdcMapperDao dao;
	public void setDao(KdcMapperDao dao) {
		this.dao = dao;
	}
	//注入解密密钥的工具类
	@Resource
	private DecodeSecretKey decodeSecretKey;
	public void setDecodeSecretKey(DecodeSecretKey decodeSecretKey) {
		this.decodeSecretKey = decodeSecretKey;
	}

	/*加密过程*/
	public String encryptAES(String tname,String data){
		//加密
		try {
			//获取数据库中的密钥密文
			String sk = dao.findSK(tname);
			//使用解密密钥工具类的descodeKey方法将数据库存取的密钥密文解密,之后流程按原逻辑进行加密
			sk = decodeSecretKey.decodeKey(sk, tname);
			
			byte[] keyBytes = Base64.decodeBase64(sk);
			//转换密钥
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
	
	/**解密过程*/
	public String decryptAES(String tname,String data){
		//将密文字符串转换为字节数组
		byte[] result = Base64.decodeBase64(data);
		try {
			//获取数据库中的密钥密文
			String sk = dao.findSK(tname);
			//使用解密密钥工具类的descodeKey方法将数据库存的密钥密文解密,之后流程按原逻辑进行解密
			sk = decodeSecretKey.decodeKey(sk, tname);
//			System.out.println(sk);
			byte[] keyBytes = Base64.decodeBase64(sk);
			//转换密钥
			Key converseKey = new SecretKeySpec(keyBytes, "AES");
			//解密
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, converseKey);
			result = cipher.doFinal(result);
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return new String(result);
	}
	/**md5加密*/
	public String encryptMD5(String pwd){
		return DigestUtils.md5Hex(pwd);
	}
	
	
}
