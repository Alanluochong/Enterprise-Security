package org.luo.enterprise.controller.admin;

import javax.annotation.Resource;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminInfoAddController {
	//ע��ӽ��ܹ�����
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
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "admin/admin_add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@Transactional
	public String add(AdminInfo adminInfo){
		//����������װ��admininfo������,
		//JSP�������name����Ҫ��AdminInfo������һ��
		/*���û���д���ݼ���*/
		adminInfo.setPassword_md5(tool.encryptMD5(adminInfo.getPassword()));
		adminInfo.setPassword(tool.encryptAES("admininfo", adminInfo.getPassword()));
		adminInfo.setIdcard_no(tool.encryptAES("admininfo", adminInfo.getIdcard_no()));
		adminInfo.setPhone(tool.encryptAES("admininfo", adminInfo.getPhone()));
		adminInfo.setEmail(tool.encryptAES("admininfo", adminInfo.getEmail()));
		//�������ݿ���ʲ㱣�����Ա��Ϣ
		dao.saveAdminInfo(adminInfo);
		return "redirect:/admin/admin_list/1";
	}
}
