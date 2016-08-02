package org.luo.enterprise.controller.emp;

import javax.annotation.Resource;

import org.luo.enterprise.dao.EmpMapperDao;
import org.luo.enterprise.entity.Emp;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/emp")
public class EmpUpdateController {
	@Resource
	private EmpMapperDao dao;
	@Resource
	private SecurityTool tool;
	public void setDao(EmpMapperDao dao) {
		this.dao = dao;
	}
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@RequestMapping(value="/{id}/toedit",method=RequestMethod.GET)
	public String toEdit(@PathVariable("id") Integer id,ModelMap model){
		Emp emp = dao.findById(id);
		/**
		 * 修改员工信息时需要将数据库中的密文解密后发送给浏览器端
		 */
		emp.setPhone(tool.decryptAES("emp", emp.getPhone()));
		emp.setIdcard_no(tool.decryptAES("emp", emp.getIdcard_no()));
		emp.setSal(tool.decryptAES("emp", emp.getSal()));
		emp.setEmail(tool.decryptAES("emp", emp.getEmail()));
		//将需要显示的数据放入model,拱浏览器使用
		model.addAttribute("emp", emp);
		return "emp/emp_modi";
	}
	@RequestMapping(value="/{id}",method=RequestMethod.PUT)
	public String updateEmp(Emp emp){
		/**
		 * 用户修改数据后,需要将敏感字段加密后在进行数据库更新
		 */
		emp.setPhone(tool.encryptAES("emp", emp.getPhone()));
		emp.setEmail(tool.encryptAES("emp", emp.getEmail()));
		emp.setIdcard_no(tool.encryptAES("emp", emp.getIdcard_no()));
		emp.setSal(tool.encryptAES("emp", emp.getSal()));
		dao.updateEmp(emp);
		return "redirect:/emp/emp_list/1";
	}
}
