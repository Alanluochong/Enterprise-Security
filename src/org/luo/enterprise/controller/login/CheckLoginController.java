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
	/*注入加密工具类*/
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
		//返回一个map集合对象存入相关信息
		Map<String , Object> map = new HashMap<String, Object>();
		//将登陆信息标识为false
		map.put("login", false);
		//构造查询对象
		if(code==null)
			map.put("validateError", "验证码不能为空");
		if(!code.equals(session.getAttribute("checkcode"))){
			map.put("validateError", "验证码错误");
			return map;
		}
		AdminInfo adminInfo = new AdminInfo();
		adminInfo.setLogin_name(name);
		/**将用户输入密码转换为md5值在进行查询*/
		password = tool.encryptMD5(password);
		adminInfo.setPassword_md5(password);
//		adminInfo.setAdmin_code(code);
		
		AdminInfo admin = dao.findByLoginNameAndPassword(adminInfo);
//		System.out.println(session.getAttribute("checkcode"));
		
		if(admin==null){
			map.put("error", "用户名或密码错误");
		}else {
			map.put("login", true);
			session.setAttribute("id",admin.getId());
			session.setAttribute("real_name", admin.getReal_name());
		}
		return map;
	}
}
