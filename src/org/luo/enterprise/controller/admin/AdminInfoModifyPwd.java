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
		//����һ��map���϶�����������Ϣ
		Map<String , Object> map = new HashMap<String, Object>();
		//���ɹ�����ȱ��Ϊfalse
		map.put("success", false);
		/**
		 * ��ȡ����ǰ�û���id,���ڲ�ѯ��ǰ����
		 */
		int id = (int) session.getAttribute("id");
		AdminInfo adminInfo = dao.findById(id);
		/*����ǰ�û���������ת��Ϊ���������ݿ������ݶԱ�,�ж��Ƿ���ȷ*/
		oldpwd= tool.encryptMD5(oldpwd);
		if(!oldpwd.equals(adminInfo.getPassword_md5())){
			map.put("error", "��ǰ�����������");
		}else{
			/**
			 * ����û�����������ȷ,���������Ӧ��md5��password����Ӧ���޸�
			 */
			adminInfo.setPassword_md5(tool.encryptMD5(newpwd));
			adminInfo.setPassword(tool.encryptAES("admininfo", newpwd));
			dao.modifyPassword(adminInfo);
			map.put("success", true);
			map.put("message", "�����޸ĳɹ�!");
		}
		return map;
	}
}
