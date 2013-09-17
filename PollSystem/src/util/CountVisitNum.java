package util;

import java.sql.*;

public class CountVisitNum {
	
	public synchronized static int getCount(){
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "select * from pollcount";
		ResultSet rs = DB.getRs(stmt, sql);
		int count = 0;
		try {
			rs.next();
			count = rs.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return count;
	}
	
	public synchronized static void updateCount(int count){
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "update pollcount set pollnum = " + count;
		DB.executeUpdate(stmt, sql);
		DB.close(stmt);
		DB.close(conn);
	}
}
