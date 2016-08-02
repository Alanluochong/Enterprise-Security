package org.luo.enterprise.controller.emp;

import org.luo.enterprise.dao.EmpMapperDao;
import org.luo.enterprise.entity.Emp;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/emp")
public class EmpAddController {
	@Autowired
	private SecurityTool tool;
	@Autowired
	private EmpMapperDao dao;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	public void setDao(EmpMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/toAdd",method=RequestMethod.GET)
	public String toAdd(){
		return "emp/emp_add";
	}
	@RequestMapping(value="/add",method=RequestMethod.POST)
	@Transactional
	public String addEmp(Emp emp){
		/**
		 * 将用户所填明文中需要加密的字段进行加密
		 * 需要加密的字段为PHONE,EMAIL,IDCARD_NO,SAL
		 */
		emp.setPhone(tool.encryptAES("emp", emp.getPhone()));
		emp.setEmail(tool.encryptAES("emp", emp.getEmail()));
		emp.setIdcard_no(tool.encryptAES("emp", emp.getIdcard_no()));
		emp.setSal(tool.encryptAES("emp", emp.getSal()));
		dao.addEmp(emp);
		return "redirect:emp_list/1";
	}
}
