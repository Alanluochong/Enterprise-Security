package org.luo.enterprise.controller.emp;

import javax.annotation.Resource;

import org.luo.enterprise.dao.EmpMapperDao;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/emp")
public class EmpDeleteController {
	@Resource
	private EmpMapperDao dao;
	public void setDao(EmpMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	@ResponseBody
	@Transactional
	public Boolean deleteEmp(@PathVariable("id") Integer id){
		if(id!=null){
			dao.deleteEmp(id);
		}
		return true;
	}
}
