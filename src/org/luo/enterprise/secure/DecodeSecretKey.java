package org.luo.enterprise.secure;

import java.security.Key;

import javax.annotation.Resource;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.luo.enterprise.util.OperateFileTool;
import org.springframework.stereotype.Component;
@Component
public class DecodeSecretKey {
	//注入文件操作工具类
	@Resource
	private OperateFileTool fileTool;
	public void setFileTool(OperateFileTool fileTool) {
		this.fileTool = fileTool;
	}
	//解密一级工作密钥的密文
	public String decodeKey(String sk,String tname){
		//将一级工作密钥转换为字节数组
		byte[] result = Base64.decodeBase64(sk);
		try {
			//获取二级密钥
			String twoSK = fileTool.readTwoKEY(tname);
			byte[] keyBytes = Base64.decodeBase64(twoSK);
			//转换密钥
			Key converseKey = new SecretKeySpec(keyBytes, "AES");
			Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
			cipher.init(Cipher.DECRYPT_MODE, converseKey);
			result = cipher.doFinal(result);
		}catch (Exception e) {
			e.printStackTrace();
		}
		return new String(result);
	}
}
