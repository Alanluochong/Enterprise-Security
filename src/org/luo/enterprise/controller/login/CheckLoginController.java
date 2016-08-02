package org.luo.enterprise.controller.login;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/login")
public class CheckLoginController {
	/*ע����ܹ�����*/
	@Resource
	private SecurityTool tool;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@Resource
	private AdminInfoMapperDao dao;

	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	@ResponseBody
	@RequestMapping(value="/login/{code}",method=RequestMethod.GET)
	public Map<String , Object> execute(@PathVariable(value="code") String code,
			@RequestHeader("name") String name,
			@RequestHeader("password") String password,
			HttpSession session){
//		System.out.println("name:"+name);
//		System.out.println("pwd:"+password);
//		System.out.println("code:"+code);
		//����һ��map���϶�����������Ϣ
		Map<String , Object> map = new HashMap<String, Object>();
		//����½��Ϣ��ʶΪfalse
		map.put("login", false);
		//�����ѯ����
		if(code==null)
			map.put("validateError", "��֤�벻��Ϊ��");
		if(!code.equals(session.getAttribute("checkcode"))){
			map.put("validateError", "��֤�����");
			return map;
		}
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setLogin_name(name);
		/**���û���������ת��Ϊmd5ֵ�ڽ��в�ѯ*/
		password = tool.encryptMD5(password);
		adminInfo.setPassword_md5(password);
//		adminInfo.setAdmin_code(code);
		
		AdminInfo admin = dao.findByLoginNameAndPassword(adminInfo);
//		System.out.println(session.getAttribute("checkcode"));
		
		if(admin==null){
			map.put("error", "�û������������");
		}else {
			map.put("login", true);
			session.setAttribute("id",admin.getId());
			session.setAttribute("real_name", admin.getReal_name());
		}
		return map;
	}
}
