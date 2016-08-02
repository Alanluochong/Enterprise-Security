package org.luo.enterprise.controller.dept;

import javax.annotation.Resource;

import org.luo.enterprise.dao.DeptMapperDao;
import org.luo.enterprise.entity.Dept;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class CheckDnameController {
	@Resource
	private SecurityTool tool;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@Resource
	private DeptMapperDao dao;
	public void setDao(DeptMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/checkDname/{dname}",method=RequestMethod.GET)
	@ResponseBody
	@Transactional(readOnly=true)
	public boolean checkDname(@PathVariable("dname") String dname){
		boolean flag = false;
		if(dname!=null){
			dname = tool.encryptAES("dept", dname);
			Dept dept = dao.findByDname(dname);
			if(dept==null){
				flag = true;
			}
		}
		return flag;
	}
}
