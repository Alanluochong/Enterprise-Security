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
		 * �������ݿ��в��ҵ����ݽ�����ҳ����ʾ
		 */
		for(Emp emp : list){
			emp.setSal(tool.decryptAES("emp", emp.getSal()));
			emp.setEmail(tool.decryptAES("emp", emp.getEmail()));
			emp.setPhone(tool.decryptAES("emp", emp.getPhone()));
			emp.setIdcard_no(tool.decryptAES("emp", emp.getIdcard_no()));
			emp.setDname(tool.decryptAES("dept", emp.getDname()));
		}
		//������ҳ��
		int totalRows = dao.findRows();
		int totalPage = 1;//Ĭ��Ϊ1
		if(totalRows%page.getPageSize()==0){
			//������ʱֱ������
			totalPage=totalRows/page.getPageSize();
		}else{
			//��������ʱ,�����Ľ����1
			totalPage=totalRows/page.getPageSize()+1;
		}
		page.setTotalPage(totalPage);
		/**
		 * �����Է���model,�Ա�ǰ��ҳ��ʹ��
		 */
		model.addAttribute("page", page);
		model.addAttribute("emps", list);
		return "emp/emp_list";
	}
}
