package org.luo.enterprise.controller.admin;

import javax.annotation.Resource;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class AdminInfoDeleteController {
	@Resource
	private AdminInfoMapperDao dao;
	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	@ResponseBody
	@Transactional
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public boolean delete(@PathVariable("id") Integer id){
		if(id!=null){
			dao.deleteAdminInfo(id);
		}
		return true;
	}
	
}
