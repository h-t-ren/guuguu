package polls;

import java.sql.*;
import java.util.List;

import util.DB;

public class PollAdminMgr {
	private static PollAdminMgr mgr;
	private PollAdminMgr(){
		
	}
	private int[] pollSize = new int[2];
	
	public static PollAdminMgr getInstance(){
		if(mgr == null){
			mgr = new PollAdminMgr();
			return mgr;
		}else{
			return mgr;
		}
		
	}
	
	public PollInformationBean getPollByAdminId(String adminId){
		PollInformationBean poll = new PollInformationBean();
		String pollId = adminId.substring(0,10);
		PollMgr mgr = PollMgr.getInstance();
		poll = mgr.getPollById(pollId); 
		return poll;
	}
	public void updatePoll(PollInformationBean poll){
		Connection conn = DB.getConn();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 List<Option> options = poll.getOptions();
		String sql = "update polls set title = ?,initiator = ?,description = ?, email = ?,updatetime = ? where pollid = '" + poll.getPollId() + "'";
		PreparedStatement pre = DB.getPrepare(conn, sql);
		Statement stmt = DB.getStatement(conn);
		try {
			pre.setString(1, poll.getTitle());
			pre.setString(2, poll.getInitialName());
			pre.setString(3, poll.getDescription());
			pre.setString(4, poll.getEmail());
			pre.setTimestamp(5, poll.getUpdateTime());
			pre.executeUpdate();
			DB.close(pre);
		
			ResultSet rs1 = DB.getRs(stmt, "select count(*) from polloptions where pollid = '" + poll.getPollId() + "'");
			rs1.next();
			int num = rs1.getInt(1);
			if(rs1!=null){
				DB.close(rs1);
			}
			ResultSet rs2 = DB.getRs(stmt, "select * from polloptions where pollid = '" + poll.getPollId() + "' order by options");
			int[] opId = new int[num];
			String[] oldOptions = new String[num];
			 
			for(int i=0; i<num; i++){
	           rs2.next();
	           opId[i] = rs2.getInt("optionid");
	           oldOptions[i] = rs2.getString("options");
	         }
			if(rs2!=null){
				DB.close(rs2);
			}
			 boolean keep = false;
			 for(int i=0; i<oldOptions.length; i++){
		           keep = false;
		           for(int j=0; j<options.size(); j++){
		             if(oldOptions[i].equals(options.get(j).getOptionContent())){
		               keep = true;
		               break;
		             }
		           }
		           if(!keep){  //delete the options from PollOptions table and votes from the PollParti table
		               stmt.executeUpdate("delete from polloptions where optionid ='" + opId[i] + "'");
		               stmt.executeUpdate("delete from pollitems where optionid ='" + opId[i] + "'");
		             }
		       }
			 boolean add = true; 
			 ResultSet memberCount = null; //first get the meber set
	         memberCount = stmt.executeQuery("select count(*)  from pollmembers where pollid='" +
	         poll.getPollId() + "'");
	         memberCount.next();
	        int memberNum = memberCount.getInt(1);
	        if(memberCount!=null){
	        	DB.close(memberCount);
	        }
	        ResultSet membersRs = null;
	        membersRs = stmt.executeQuery("select memberid from pollmembers where pollid = '" +
	        poll.getPollId() + "' order by memberid");
	        
	        int[] memberId = new int[memberNum];

	        for(int i=0; i<memberNum; i++){
	          membersRs.next();
	          memberId[i] = membersRs.getInt("memberid");
	        }
	        if(membersRs!=null){
	        	DB.close(membersRs);
	        }
	        int choose = 2;


	         for(int i=0; i<options.size(); i++){
	           add = true;
	           for(int j=0; j<oldOptions.length; j++){
	             if(options.get(i).getOptionContent().equals(oldOptions[j])){
	               add = false;
	               break;
	             }
	           }
	           if(add){
	              stmt.executeUpdate("insert into polloptions  values (null,'" + poll.getPollId() +  "', '" + options.get(i).getOptionContent().trim() + "')");

	              String sql1 = "select optionid from polloptions where pollid = '" + poll.getPollId() + "' and options='" + options.get(i).getOptionContent().trim()+"'";
	              ResultSet optionIDSet = stmt.executeQuery(sql1);
	              optionIDSet.next();
	              int optionID = optionIDSet.getInt("optionid");
	              for(int j=0; j < memberNum; j++){
	                stmt.executeUpdate("insert into pollitems values (null,'" + poll.getPollId() +  "', '" + optionID + "', '" + memberId[j] +"', '" + choose +"')");
	              }
	              DB.close(optionIDSet);
	           }
	         }
	     	
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				conn.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
		
			DB.close(stmt);
			DB.close(conn);
		}
	}
	
	public PollPartiBean getPollPartiByAdminId(String adminId){
		PollPartiBean pollParti = new PollPartiBean();
		String pollId = adminId.substring(0,10);
		PollMgr mgr = PollMgr.getInstance();
		pollParti = mgr.getPollPartiById(pollId);
		return pollParti;
	}
	public int[] getPollSize(String pollId){
		PollMgr mgr = PollMgr.getInstance();
		return mgr.getPollSize(pollId);
	}
	
	  public String[][] getMembersAdmin(int[] pollSize,String pollId){
		   String[][] membersAdmin = new String[pollSize[1]][2];
		   Connection conn = DB.getConn();
		   Statement stmt = DB.getStatement(conn);
		   String sql = "select * from pollmembers where pollid = '" + pollId + "'";
		   ResultSet rs = DB.getRs(stmt, sql);
		    try{
		    	 for (int i = 0; i < pollSize[1]; i++) {
		    		 if(rs.next()){
		    			 membersAdmin[i][0] = Integer.toString(rs.getInt("memberid"));
					     membersAdmin[i][1] = rs.getString("membername");
		    		 }
		    	 }
		    		 
		    }catch(SQLException e){
		    	e.printStackTrace();
		    }finally{
		    	DB.close(rs);
		    	DB.close(stmt);
		    	DB.close(conn);
		    }
 
		   return membersAdmin;
		 }
	  
	  public void deleteMembers(int[][] a,String pollId){
		  
		  Connection conn = DB.getConn();
		  try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		  Statement stmt = DB.getStatement(conn);
		  ResultSet rs = null;
			    for(int i=0; i<a.length; i++){
			      if(a[i][1]==1){
			    	
			        String sql = "delete from pollmembers where memberid = " + a[i][0];//删除一个元组
			        String sql2 = "delete from pollitems where memberid = " + a[i][0];
//System.out.println(sql);
			        DB.executeUpdate(stmt, sql);
			        DB.executeUpdate(stmt, sql2);
			      }
			    }
			    try {
					conn.commit();
					conn.setAutoCommit(true);
				} catch (SQLException e) {
					try {
						conn.rollback();
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				}finally{
					DB.close(stmt);
			  		DB.close(conn);
				}
		
	  }
	  
	  public void deleteComment(int[][] a ,String pollId){
		  	Connection conn = DB.getConn();
			Statement stmt = DB.getStatement(conn);
			    for(int i=0; i<a.length; i++){
			      if(a[i][1]==1){
			        String sql = "delete from pollcomments where commentid=" + a[i][0] ;
			        DB.executeUpdate(stmt, sql);
			      }
			    }
			  DB.close(stmt);
			  DB.close(conn);
	  }
	  
	  public int getCommentNum(String pollId){
		  Connection conn = DB.getConn();
		  PollMgr mgr = PollMgr.getInstance();
		  int a = mgr.getCommentNum(conn, pollId);
		  DB.close(conn);
		  return a;
	  }
	  
	  public String[][] getComments(String pollId){
		  Connection conn = DB.getConn();
		  PollMgr mgr = PollMgr.getInstance();
		  String[][] comments = null;
		  comments =  mgr.initComments(conn, pollId);
		  DB.close(conn);
		  return comments;
	  }
	  
	  public void deletePoll(String adminID){

		     Connection conn = DB.getConn();
		     Statement stmt = DB.getStatement(conn);
		     String sql = "update polls set isdelete = 1 where adminid='" + adminID + "'";
		     DB.executeUpdate(stmt, sql);
		     DB.close(stmt);
		     DB.close(conn);

		  }
}
