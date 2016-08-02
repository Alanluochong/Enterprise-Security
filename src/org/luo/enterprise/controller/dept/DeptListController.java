package org.luo.enterprise.controller.dept;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.luo.enterprise.dao.DeptMapperDao;
import org.luo.enterprise.entity.Dept;
import org.luo.enterprise.secure.SecurityTool;
import org.luo.enterprise.util.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/dept")
public class DeptListController {
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
	@RequestMapping(value="/dept_list/{p}",method=RequestMethod.GET)
	public String showDeptList(@PathVariable("p") Integer p,ModelMap model,HttpSession session){
		Page page = new Page();
		page.setPage(p);
		List<Dept> list = dao.findPage(page);
		/**
		 * 将数据库中查出来的密文解密
		 */
		for(Dept dept : list){
			dept.setDname(tool.decryptAES("dept", dept.getDname()));
			dept.setManager(tool.decryptAES("dept", dept.getManager()));
		}
		int rows = dao.findRows();
		//初始化总页数
		int totalPage = 1;
		if(rows%page.getPageSize()==0){
			totalPage = rows/page.getPageSize();
		}else{
			totalPage = rows/page.getPageSize()+1;
		}
		//将总页数放入page对象
		page.setTotalPage(totalPage);
		//将结果放入model,供页面访问
		model.addAttribute("page", page);
		model.addAttribute("depts", list);
		//model.addAttribute("real_name", session.getAttribute("real_name"));
		
		return "dept/dept_list";
	}
}
