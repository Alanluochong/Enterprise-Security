package org.luo.enterprise.controller.dept;

import javax.annotation.Resource;

import org.luo.enterprise.dao.DeptMapperDao;
import org.luo.enterprise.entity.Dept;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dept")
public class DeptAddController {
	@Resource
	private SecurityTool tool;
	@Resource
	private DeptMapperDao dao;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	public void setDao(DeptMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAddDept(){
		return "dept/dept_add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@Transactional
	public String addDept(Dept dept){
		/**
		 * �ڴ洢֮ǰҪ�Ƚ������ֶ�ת��Ϊ����
		 */
		dept.setDname(tool.encryptAES("dept", dept.getDname()));
		dept.setManager(tool.encryptAES("dept", dept.getManager()));
		//����dao�����ݴ������ݿ�
		dao.saveDept(dept);
		return "redirect:dept_list/1";
	}
}
