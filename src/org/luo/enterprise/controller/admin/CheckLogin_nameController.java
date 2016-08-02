package org.luo.enterprise.controller.admin;

import javax.annotation.Resource;

import org.luo.enterprise.dao.AdminInfoMapperDao;
import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.secure.SecurityTool;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class CheckLogin_nameController {
	@Resource
	private AdminInfoMapperDao dao;
	@Resource
	private SecurityTool tool;
	public void setDao(AdminInfoMapperDao dao) {
		this.dao = dao;
	}
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@ResponseBody//返回JSON格式数据
	@RequestMapping(value="/checkLogin_name/{login_name}",method=RequestMethod.GET)
	public boolean checkLogin_name(@PathVariable("login_name") String login_name){
		boolean flag = false;
		if(login_name!=null){
			//将
			AdminInfo admin = dao.findByLogin_name(login_name);
			if(admin==null){//说明管理员账号没有被占用
				flag=true;
			}
		}
		return flag;
	}

}
