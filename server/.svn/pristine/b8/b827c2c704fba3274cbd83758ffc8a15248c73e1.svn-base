package jeecg.ext.service.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import jeecg.ext.service.JdbcService;

import org.jeecgframework.core.util.ResourceUtil;

public class JdbcServiceImpl implements JdbcService{
	
	private Connection conn;
	
	public JdbcServiceImpl(){
		String driver = ResourceUtil.getConfigByName("jdbc.driver");
		String url = ResourceUtil.getConfigByName("jdbc.url.jeecg");
		String passwd = ResourceUtil.getConfigByName("jdbc.password.jeecg");
		String userName = ResourceUtil.getConfigByName("jdbc.username.jeecg");
		try {
			Class.forName(driver);
			conn  = DriverManager.getConnection(url, userName, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public JdbcServiceImpl(String driver , String url , String userName, String  passwd){

		try {
			Class.forName(driver);
			conn  = DriverManager.getConnection(url, userName, passwd);
		} catch (Exception e) {
			e.printStackTrace();
		} 	
	}
	
	public List getQuerySqlMeta(String sql){
		List list = new ArrayList(0);
		try {
			Statement st = conn.createStatement();
			
			st.setFetchSize(1);
			ResultSet rs = st.executeQuery(sql);
			ResultSetMetaData rmd = rs.getMetaData();
			int columnCount = rmd.getColumnCount();
			for(int i = 1; i <= columnCount; i++){
				list.add(rmd.getColumnLabel(i));
				
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		return  list;
	}

	public Connection getConn() {
		return conn;
	}

	public void setConn(Connection conn) {
		this.conn = conn;
	}
	
	public void close(){
		if(conn != null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
