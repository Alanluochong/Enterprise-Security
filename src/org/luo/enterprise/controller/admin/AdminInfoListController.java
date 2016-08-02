package org.luo.enterprise.controller.admin;

import java.util.List;

import javax.annotation.Resource;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.luo.enterprise.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/admin")
public class AdminInfoListController {
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
	@RequestMapping(value="admin_list/{p}",method=RequestMethod.GET)
	public String showAdminList(ModelMap model,@PathVariable("p") int p){
		Page page = new Page();
		page.setPage(p);
		List<AdminInfo> list = dao.findPage(page);
		/**�����ݽ���*/
		for(AdminInfo adminInfo : list){
			adminInfo.setPassword(tool.decryptAES("admininfo", adminInfo.getPassword()));
			adminInfo.setPhone(tool.decryptAES("admininfo", adminInfo.getPhone()));
			adminInfo.setEmail(tool.decryptAES("admininfo", adminInfo.getEmail()));
		}
		//������ҳ��
		int totalRows = dao.findRows();
		int totalPage=1;//Ĭ��Ϊ1
		if(totalRows%page.getPageSize()==0){
			//������ʱֱ������
			totalPage=totalRows/page.getPageSize();
		}else{
			//��������ʱ,�����Ľ����1
			totalPage=totalRows/page.getPageSize()+1;
		}
		//����ҳ������page����
		page.setTotalPage(totalPage);
		// ��������뵽model.��ҳ�����
		model.addAttribute("page", page);
		model.addAttribute("admins", list);
		return "admin/admin_list";//����fee/fee_list.jspҳ��
	}
	
}
