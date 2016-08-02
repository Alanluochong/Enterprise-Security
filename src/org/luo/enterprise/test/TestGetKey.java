package org.luo.enterprise.test;

import org.luo.enterprise.secure.DecodeSecretKey;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestGetKey {
	private static ApplicationContext ac;

	public static void main(String[] args) {
		String conf = "org/luo/enterprise/config/applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
//		KdcMapperDao dao = ac.getBean("kdcMapperDao",KdcMapperDao.class);
		SecurityTool tool = ac.getBean("securityTool",SecurityTool.class);
//		String key = dao.findSK("admininfo");
		String idcard_no = tool.encryptAES("admininfo", "728");
		System.out.println(idcard_no);
//		DecodeSecretKey decode = ac.getBean("decodeSecretKey",DecodeSecretKey.class);
//		String sk = decode.decodeKey("GkLMqJl514jto2i99Bvy10v1/XAmrbsNvd82DjbbpKo=", "admininfo");
//		System.out.println(sk);
	}
}
