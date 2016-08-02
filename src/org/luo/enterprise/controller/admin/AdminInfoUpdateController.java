package org.luo.enterprise.controller.admin;

import javax.annotation.Resource;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminInfoUpdateController {
	@Autowired
	private SecurityTool tool;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@Resource
	private AdminInfoMapperDao dao;
	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/{id}/toedit",method=RequestMethod.GET)
	public String toUpdate(@PathVariable("id" ) Integer id,ModelMap model){
		AdminInfo adminInfo = dao.findById(id);
		/**
		 * 将查到的数据解密为明文返回浏览器
		 */
		adminInfo.setIdcard_no(tool.decryptAES("admininfo", adminInfo.getIdcard_no()));
		adminInfo.setPhone(tool.decryptAES("admininfo", adminInfo.getPhone()));
		adminInfo.setEmail(tool.decryptAES("admininfo", adminInfo.getEmail()));
		model.addAttribute("adminInfo", adminInfo);
		return "admin/admin_modi";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	@Transactional
	public String updateAdminInfo(AdminInfo adminInfo){
		/**
		 * 修改密码后需要将数据库中密文也随之更新
		 * 计算相应密文存入数据库
		 */
		adminInfo.setIdcard_no(tool.encryptAES("admininfo", adminInfo.getIdcard_no()));
		adminInfo.setPhone(tool.encryptAES("admininfo", adminInfo.getPhone()));
		adminInfo.setEmail(tool.encryptAES("admininfo", adminInfo.getEmail()));
		adminInfo.setPassword_md5(tool.encryptMD5(adminInfo.getPassword()));
		adminInfo.setPassword(tool.encryptAES("admininfo", adminInfo.getPassword()));
		dao.updateAdminInfo(adminInfo);
		return "redirect:/admin/admin_list/1";
	}

}
