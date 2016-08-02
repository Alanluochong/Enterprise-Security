package org.luo.enterprise.dao;

import java.util.List;

import org.luo.enterprise.entity.Emp;
import org.luo.enterprise.util.MyBatisDao;
import org.luo.enterprise.util.Page;
@MyBatisDao
public interface EmpMapperDao {
	public void addEmp(Emp emp);
	public List<Emp> findPage(Page page);
	public int findRows();
	public void deleteEmp(Integer id);
	public Emp findById(Integer id);
	public void updateEmp(Emp emp);
}
