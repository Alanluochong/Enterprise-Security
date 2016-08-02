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
	//ע���ļ�����������
	@Resource
	private OperateFileTool fileTool;
	public void setFileTool(OperateFileTool fileTool) {
		this.fileTool = fileTool;
	}
	//����һ��������Կ������
	public String decodeKey(String sk,String tname){
		//��һ��������Կת��Ϊ�ֽ�����
		byte[] result = Base64.decodeBase64(sk);
		try {
			//��ȡ������Կ
			String twoSK = fileTool.readTwoKEY(tname);
			byte[] keyBytes = Base64.decodeBase64(twoSK);
			//ת����Կ
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
