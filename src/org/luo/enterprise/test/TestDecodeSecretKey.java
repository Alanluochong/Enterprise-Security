package org.luo.enterprise.test;

import org.luo.enterprise.secure.DecodeSecretKey;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestDecodeSecretKey {
	private static ApplicationContext ac;

	public static void main(String[] args) {
		String conf = "org/luo/enterprise/config/applicationContext.xml";
		ac = new ClassPathXmlApplicationContext(conf);
		DecodeSecretKey decode = ac.getBean("decodeSecretKey",DecodeSecretKey.class);
		String sk = decode.decodeKey("GkLMqJl514jto2i99Bvy10v1/XAmrbsNvd82DjbbpKo=", "admininfo");
		System.out.println(sk);
	}
}
