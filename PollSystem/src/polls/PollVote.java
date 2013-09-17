package polls;

import util.DB;

import java.sql.*;
import java.util.List;
import java.util.Date;


public class PollVote {
	public static void inputVote(PollPartiBean pollParti,String parName,List<Option> options){
		Connection conn = DB.getConn();
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = DB.getStatement(conn);
		String sql = "insert into pollmemers values(null,?,?)";
		PreparedStatement pre = DB.getPrepare(conn, sql,Statement.RETURN_GENERATED_KEYS);
		ResultSet rs1 = null;
		ResultSet rs2 = null;
		int[] choose = pollParti.getChoose();
		try {
			pre.setString(1, pollParti.getPollId());
			pre.setString(2, parName);
			pre.executeUpdate();
			rs1 = pre.getGeneratedKeys();
			rs1.next();
			int memberId = rs1.getInt(1);
			for(int i=0; i<choose.length; i++){
               rs2 = stmt.executeQuery("select optionid from polloptions where pollid= '" + pollParti.getPollId() +  "' and options = '" +  options.get(i).getOptionContent() + "'");
               rs2.next();
               int optionId = rs2.getInt(1);

               int ch = 0;
               if(choose[i]==1)ch = 1;
               stmt.executeUpdate("insert into pollitems  values (null,'" + pollParti.getPollId() +  "', '" + optionId +  "', '" + memberId +  "', " + ch + ")");
           }
	        PreparedStatement updateTime = DB.getPrepare(conn, "update polls set lastvotetime = ? where pollid = ? ");
	        updateTime.setTimestamp(1, new Timestamp(new Date().getTime()));//第一个？
	        updateTime.setString(2, pollParti.getPollId());//第二个？
	        updateTime.executeUpdate();
	        DB.close(updateTime);
	        conn.commit();
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			if(rs1!=null){
				DB.close(rs1);
			}
			if(rs2!=null){
				DB.close(rs2);
			}
			DB.close(stmt);
			DB.close(pre);
			DB.close(conn);
		}
	}
}
