package org.luo.enterprise.controller.dept;
import javax.annotation.Resource;

import org.luo.enterprise.dao.DeptMapperDao;
import org.luo.enterprise.entity.Dept;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/dept")
public class CheckDeptnoController {
	@Resource
	private DeptMapperDao dao;
	public void setDao(DeptMapperDao dao) {
		this.dao = dao;
	}
	@RequestMapping(value="/checkDeptno/{deptno}",method=RequestMethod.GET)
	@ResponseBody
	@Transactional(readOnly=true)
	public boolean checkDeptno(@PathVariable("deptno") Integer deptno){
		if(deptno!=null){
			Dept dept = dao.findByDeptno(deptno);
			if(dept!=null){
				//�м�¼˵���˲��ű���ѱ�ռ��
				return false;
			}else{
				return true;
			}
		}
		return false;
	}
}
