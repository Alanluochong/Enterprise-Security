package org.luo.enterprise.controller.emp;

import java.util.List;

import javax.annotation.Resource;

import org.luo.enterprise.dao.EmpMapperDao;
import org.luo.enterprise.entity.Emp;
import org.luo.enterprise.secure.SecurityTool;
import org.luo.enterprise.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("emp")
public class EmpListController {
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
	@RequestMapping(value="emp_list/{p}",method=RequestMethod.GET)
	public String showEmpList(ModelMap model,@PathVariable Integer p){
		Page page = new Page();
		page.setPage(p);
		List<Emp> list = dao.findPage(page);
		/**
		 * 将从数据库中查找的数据解密往页面显示
		 */
		for(Emp emp : list){
			emp.setSal(tool.decryptAES("emp", emp.getSal()));
			emp.setEmail(tool.decryptAES("emp", emp.getEmail()));
			emp.setPhone(tool.decryptAES("emp", emp.getPhone()));
			emp.setIdcard_no(tool.decryptAES("emp", emp.getIdcard_no()));
			emp.setDname(tool.decryptAES("dept", emp.getDname()));
		}
		//计算总页数
		int totalRows = dao.findRows();
		int totalPage = 1;//默认为1
		if(totalRows%page.getPageSize()==0){
			//能整除时直接整除
			totalPage=totalRows/page.getPageSize();
		}else{
			//不能整除时,整除的结果加1
			totalPage=totalRows/page.getPageSize()+1;
		}
		page.setTotalPage(totalPage);
		/**
		 * 将属性放入model,以便前端页面使用
		 */
		model.addAttribute("page", page);
		model.addAttribute("emps", list);
		return "emp/emp_list";
	}
}
