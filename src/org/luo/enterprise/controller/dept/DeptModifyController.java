package org.luo.enterprise.controller.dept;

import javax.annotation.Resource;

import org.luo.enterprise.dao.DeptMapperDao;
import org.luo.enterprise.entity.Dept;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dept")
public class DeptModifyController {
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
	@RequestMapping(value="/{deptno}/toedit",method=RequestMethod.GET)
	public String toDeptEdit(@PathVariable("deptno") Integer deptno,ModelMap model){
		if(deptno!=null){
			Dept dept = dao.findByDeptno(deptno);
			/**
			 * 将数据解密后向页面显示
			 */
			dept.setDname(tool.decryptAES("dept", dept.getDname()));
			dept.setManager(tool.decryptAES("dept", dept.getManager()));
			model.addAttribute("dept", dept);
			return "dept/dept_modi";
		}
		return "";
	}
	@RequestMapping(value="/{deptno}",method=RequestMethod.PUT)
	public String updateDept(Dept dept){
		/**
		 * 存储之前先将数据加密
		 */
		dept.setDname(tool.encryptAES("dept", dept.getDname()));
		dept.setManager(tool.encryptAES("dept", dept.getManager()));
		dao.update(dept);
		return "redirect:/dept/dept_list/1";
	}
}
