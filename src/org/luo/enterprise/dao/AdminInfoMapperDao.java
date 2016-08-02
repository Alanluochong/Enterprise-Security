package org.luo.enterprise.dao;

import java.util.List;

import org.luo.enterprise.entity.AdminInfo;
import org.luo.enterprise.util.MyBatisDao;
import org.luo.enterprise.util.Page;
@MyBatisDao
public interface AdminInfoMapperDao {
	public AdminInfo findByLoginNameAndPassword(AdminInfo adminInfo);
	public List<AdminInfo> findAll();
	public int findRows();
	public List<AdminInfo> findPage(Page page);
	public void deleteAdminInfo(int id);
	public void saveAdminInfo(AdminInfo adminInfo);
	public AdminInfo findById(int id);
	public void updateAdminInfo(AdminInfo adminInfo);
	public void modifyPassword(AdminInfo adminInfo);
	public AdminInfo findByLogin_name(String login_name);
}
