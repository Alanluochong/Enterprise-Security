package org.luo.enterprise.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminInfoModifyPwd {
	@Autowired
	private SecurityTool tool;
	@Resource
	private AdminInfoMapperDao dao;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/tomodifypwd",method=RequestMethod.GET)
	public String toModifyPwd(){
		return "user/user_modi_pwd";
	}
	@ResponseBody
	@RequestMapping(value="/modifypwd",method=RequestMethod.PUT)
	public Map<String, Object> modifyPwd(@RequestHeader("oldpwd") String oldpwd,
			@RequestHeader("newpwd") String newpwd,HttpSession session){
		//返回一个map集合对象存入相关信息
		Map<String , Object> map = new HashMap<String, Object>();
		//将成功与否先标记为false
		map.put("success", false);
		/**
		 * 先取出当前用户的id,用于查询当前密码
		 */
		int id = (int) session.getAttribute("id");
		AdminInfo adminInfo = dao.findById(id);
		/*将当前用户输入密码转换为密文与数据库中数据对比,判断是否正确*/
		oldpwd= tool.encryptMD5(oldpwd);
		if(!oldpwd.equals(adminInfo.getPassword_md5())){
			map.put("error", "当前密码输入错误");
		}else{
			/**
			 * 如果用户输入密码正确,则将新密码对应的md5和password都相应的修改
			 */
			adminInfo.setPassword_md5(tool.encryptMD5(newpwd));
			adminInfo.setPassword(tool.encryptAES("admininfo", newpwd));
			dao.modifyPassword(adminInfo);
			map.put("success", true);
			map.put("message", "密码修改成功!");
		}
		return map;
	}
}
