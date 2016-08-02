package org.luo.enterprise.util;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

//@Repository
/*�־ò�ע��*/
public class AddKeyDao {
	@Autowired/*��������ƥ��*/
	private JDBCDataSource dataSource;

	public JDBCDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(JDBCDataSource dataSource) {
		this.dataSource = dataSource;
	}
	/*�����ݿ��������Կ*/
	public void addKey(String table,String skey ){
		String sql = "insert into kdc(tname,skey) values(?,?)";
		Connection conn = null;
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, table);
			ps.setString(2, skey);
			ps.execute();
			ps.close();
		} catch (Exception e) {
			e.printStackTrace();
		}finally{
			dataSource.close(conn);
		}
	}
	
}
