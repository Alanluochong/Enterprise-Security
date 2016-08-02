package org.luo.enterprise.dao;

import java.util.List;

import org.luo.enterprise.entity.Dept;
import org.luo.enterprise.util.MyBatisDao;
import org.luo.enterprise.util.Page;

@MyBatisDao
public interface DeptMapperDao {
	public List<Dept> findPage(Page page);
	public int findRows();
	public Dept findByDeptno(int deptno);
	public Dept findByDname(String dname);
	public void saveDept(Dept dept);
	public void delete(int deptno);
	public void update(Dept dept);
}
