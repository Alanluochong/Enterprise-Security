package org.luo.enterprise.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.stereotype.Component;



/**
 * 文件操作工具类
 * 根据传入的数据表名,返回相应的二级加密密钥
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
			//从文件系统的某个文件中获取字节
			fis = new FileInputStream("D://java/projects/Enterprise-Security/二级密钥.bat");
			//字符流
			isr = new InputStreamReader(fis);
			//缓冲字符流
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
				//关闭流时按照先后顺序,先关闭缓冲字符流,在关闭字符流,在关闭字节流
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
