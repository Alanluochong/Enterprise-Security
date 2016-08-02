package org.luo.enterprise.dao;

import org.luo.enterprise.util.MyBatisDao;

@MyBatisDao
public interface KdcMapperDao {
	public String findSK(String string);
}
