package org.luo.enterprise.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;



/**
 * �ļ�����������
 * ���ݴ�������ݱ���,������Ӧ�Ķ���������Կ
 * @author luo_c
 */
@Component
public class OperateFileTool {
	 FileInputStream fis = null;
	 InputStreamReader isr = null;
	 BufferedReader reader = null;
	public String readTwoKEY(String tname){
		String result = "";
		try {
			String str = "";
			//���ļ�ϵͳ��ĳ���ļ��л�ȡ�ֽ�
			fis = new FileInputStream("D://java/projects/Enterprise-Security/������Կ.bat");
			//�ַ���
			isr = new InputStreamReader(fis);
			//�����ַ���
			reader = new BufferedReader(isr);
			while((str = reader.readLine()) !=null){
				String [] strs = str.split(":");
				if(strs[0].equals(tname))
					result = strs[1];
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			try {
				//�ر���ʱ�����Ⱥ�˳��,�ȹرջ����ַ���,�ڹر��ַ���,�ڹر��ֽ���
				reader.close();
				isr.close();
				fis.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
//	public static void main(String[] args) {
//		System.out.println(readTwoKEY());
//	}
}
