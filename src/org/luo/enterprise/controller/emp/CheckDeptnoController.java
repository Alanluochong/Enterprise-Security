package org.luo.enterprise.controller.emp;
import java.util.HashMap;
import java.util.Map;

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

@Controller("checkEmpDeptno")
@RequestMapping("/emp")
public class CheckDeptnoController {
	@Resource
	private DeptMapperDao dao;
	public void setDao(DeptMapperDao dao) {
		this.dao = dao;
	}
	@Resource
	private SecurityTool tool;
	public void setTool(SecurityTool tool) {
		this.tool = tool;
	}
	@RequestMapping(value="/checkDeptno/{deptno}",method=RequestMethod.GET)
	@ResponseBody
	@Transactional(readOnly=true)
	public Map<String, Object> checkDeptno(@PathVariable("deptno") Integer deptno){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("deptno", false);//��ʼ�������ű������Ϊ������
		if(deptno!=null){
			Dept dept = dao.findByDeptno(deptno);
			if(dept!=null){//��Ϊ��˵���˲��Ŵ���
				map.put("deptno", true);
				//���������ƴ���map,����ҳ�����
				map.put("dname", tool.decryptAES("dept", dept.getDname()));
			}
		}
		return map;
	}
}
