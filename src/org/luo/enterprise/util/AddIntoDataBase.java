package org.luo.enterprise.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
/**
 * 向数据库密钥表中添加密钥
 * @author luo_c
 *
 */
public class AddIntoDataBase {
	static String tname;
	public static void main(String[] args) {
		add();
	}
	public static void add(){
		String conf = "org/luo/enterprise/config/applicationContext.xml";
		ApplicationContext ac = new ClassPathXmlApplicationContext(conf);
		AddKeyDao dao = ac.getBean("addKeyDao", AddKeyDao.class);
		GKEY gkey = ac.getBean("gKEY", GKEY.class);
		String skey = gkey.generateKey();
		tname = "emp";
		dao.addKey(tname, skey);
	}

}
