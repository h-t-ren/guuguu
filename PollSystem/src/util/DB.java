package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DB {
	public static Connection getConn(){
		Connection conn = null;
		try{
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost/pollsystem?user=root&password=root");
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}catch(SQLException e){
			e.printStackTrace();
		}
		return conn;
	}
	
	public static PreparedStatement getPrepare(Connection conn,String sql){
		PreparedStatement pre = null;
		try{
			if(conn != null){
				pre = conn.prepareStatement(sql);
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pre;
	}
	
	public static Statement getStatement(Connection conn){
		Statement stmt = null;
		try{
			if(conn != null){
				stmt = conn.createStatement();
			}
		}catch(SQLException e){
			e.printStackTrace();
		}
		return stmt;
	}
	
	public static ResultSet getRs(Statement stmt,String sql){
		ResultSet rs = null;
		try{
			rs = stmt.executeQuery(sql);
		}catch(SQLException e){
			e.printStackTrace();
		}
		return rs;
	}
	public static PreparedStatement getPrepare(Connection conn,String sql,int auto){
		PreparedStatement pre = null;
		try{
			if(conn != null){
				pre = conn.prepareStatement(sql, auto);
			}	
		}catch(SQLException e){
			e.printStackTrace();
		}
		return pre;
	}
	
	public static void executeUpdate(Statement stmt,String sql){
		try {
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Connection conn){
		try {
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(Statement stmt){
		try {
			stmt.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void close(ResultSet rs){
		try {
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}

