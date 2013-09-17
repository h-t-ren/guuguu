package polls;

import java.sql.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import util.DB;



public class PollMgr {
	private static PollMgr mgr;
	private PollMgr(){
		
	}
	private int[] pollSize = new int[2];
	
	public static PollMgr getInstance(){
		if(mgr == null){
			mgr = new PollMgr();
			return mgr;
		}else{
			return mgr;
		}
		
	}
	
	public String[] createPoll(PollInformationBean poll){
		String[] pollAdminId = createId(); 
		Connection conn = DB.getConn();
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		String sql1 = "insert into polls values (?,?,?,?,?,?,?,?,?,null,null,?)";
		String sql2 = "insert into polloptions values (null,?,?)";
		PreparedStatement pre1 = DB.getPrepare(conn, sql1);
		PreparedStatement pre2 = DB.getPrepare(conn, sql2);
		String ipAddress = poll.getIpAddress();
		if(ipAddress.length() > 25){
			poll.setIpAddress(ipAddress.substring(0,25));
		}
		try {
			initialPoll(pre1, poll, pollAdminId);
			initialOption(pre2,poll,pollAdminId);
			pre1.executeUpdate();
			pre2.executeBatch();
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
			DB.close(pre1);
			DB.close(pre2);
			DB.close(conn);
		}
		return pollAdminId;
	}
	
	public String[] createPoll2(PollInformationBean poll){
		return null;
	}
	
	private void initialOption(PreparedStatement pre,
			PollInformationBean poll, String[] pollAdminId)throws SQLException {
		List<Option> options = poll.getOptions();
		for(int i=0;i<options.size();i++){
			Option o = options.get(i);
			pre.setString(1, pollAdminId[0]);
			pre.setString(2, o.getOptionContent());
			pre.addBatch();
		}
		
	}

	public void initialPoll(PreparedStatement pre,PollInformationBean poll,String[] pollAdminId)throws SQLException{
		pre.setString(1, pollAdminId[0]);
		pre.setString(2, pollAdminId[1]);
		pre.setString(3, poll.getTitle());
		pre.setString(4, poll.getInitialName());
		pre.setString(5, poll.getDescription());
		pre.setString(6, poll.getEmail());
		pre.setInt(7, poll.getPollType());
		pre.setString(8, poll.getIpAddress());
		pre.setTimestamp(9, poll.getPollCreateTime());
		if(poll.isDeleted()){
			pre.setInt(10, 1);
		}else {
			pre.setInt(10, 0);
		}
		
	}
	
	public synchronized String[] createId(){
		String[] pollAdminId = new String[2];
		String[] alp={"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z",
				"0","1","2","3","4","5","6","7","8","9","0"};
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "select count(*) from polls"; 
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			rs.next();
			int totalNum = rs.getInt(1);
			String numStr = new Integer(totalNum).toString();
			int length = numStr.length();
			String pollId = new String("");
			if(length < 10){
				for(int i=0;i<10;i++){
					pollId = pollId + alp[(int)(Math.random()*36)];
				}
				int position = (int)Math.random()*length;
				pollId = pollId.substring(position) + pollId.substring(0,position); 
			}
			String  adminId = pollId + alp[(int)(Math.random()*26)]+alp[(int)(Math.random()*26)]+alp[(int)(Math.random()*26)];
			pollAdminId[0] = pollId;
			pollAdminId[1] = adminId;

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return pollAdminId;
	}
	
	
	public PollInformationBean getPollById(String pollId){
		PollInformationBean poll = new PollInformationBean();
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "select * from polls where pollid = '" + pollId + "'";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			rs.next();
			poll.setPollId(pollId);
			poll.setAdminId(rs.getString("adminid"));
			poll.setInitialName(rs.getString("initiator"));
			poll.setTitle(rs.getString("title"));
			poll.setDescription(rs.getString("description"));
			poll.setEmail(rs.getString("email"));
			poll.setIpAddress(rs.getString("ipaddress"));
			poll.setPollCreateTime(rs.getTimestamp("pollcreatetime"));
			poll.setUpdateTime(rs.getTimestamp("updatetime"));
			poll.setLastVoteTime(rs.getTimestamp("lastvotetime"));
			poll.setPollType(rs.getInt("polltype"));
			if(rs.getInt("isdelete")==0){
				poll.setDeleted(false);
			}else if(rs.getInt("isdelete")==1){
				poll.setDeleted(true);
			}
			initOptions(conn,poll);
		} catch (SQLException e) {
			System.out.print("找不到对应信息！");
		}finally{
			DB.close(rs);
			DB.close(stmt);
			DB.close(conn);
		}
		return poll;
	}
	private void initOptions(Connection conn, PollInformationBean poll) {
		List<Option> options = new ArrayList<Option>();
		Statement stmt = DB.getStatement(conn);
		String sql = "select * from polloptions where pollid = '" + poll.getPollId() + "' order by options";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			while(rs.next()){
				Option o = new Option();
				o.setId(rs.getInt("optionid"));
				o.setPollId(rs.getString("pollid"));
				o.setOptionContent(rs.getString("options"));
				options.add(o);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
		}
		if(options.size()<=4){
			poll.setOptionNum(4);
		}else{
			poll.setOptionNum(options.size());
		}
		
		poll.setOptions(options);
	}

	public PollPartiBean getPollPartiById(String pollId){
		PollPartiBean pollParti = new PollPartiBean();
		List<PollMember> members = new ArrayList<PollMember>();
		int[][] pollResult = null;
		String[][] comments = null;
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sqlMember = "select * from pollmembers where pollid = '" + pollId + "'" ;
		ResultSet rsMember = DB.getRs(stmt, sqlMember);
		try {
			while(rsMember.next()){
				PollMember pm = new PollMember();
				pm.setId(rsMember.getInt("memberid"));
				pm.setPollId(pollId);
				pm.setMemberName(rsMember.getString("membername"));
				members.add(pm);
			}
			comments = initComments(conn,pollId);
			pollResult = initPR(conn,pollId);
			pollParti.setPollResult(pollResult);
			pollParti.setMembers(members);
			pollParti.setPollId(pollId);
			pollParti.setComments(comments);
			pollParti.setCommentNum(getCommentNum(conn,pollId));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close(rsMember);
			DB.close(stmt);
			DB.close(conn);
		}
		return pollParti;
	}
	
	public int getCommentNum(Connection conn, String pollId) {
		Statement stmt = DB.getStatement(conn);
		String sql = "select count(*) from pollcomments where pollid= '" + pollId + "'";
		ResultSet rs = DB.getRs(stmt, sql);
		int num = 0;
		try {
			if(rs.next()){
				num = rs.getInt(1);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				DB.close(rs);
			}
			DB.close(stmt);
		}
		return num;
	}

	public  String[][] initComments(Connection conn, String pollId) {
		String[][] comments = null;
		Statement stmtCount = DB.getStatement(conn);
		String sqlCount = "select count(*) from pollcomments where pollid = '" + pollId + "'";
		ResultSet rsCount = DB.getRs(stmtCount, sqlCount);
		ResultSet rs = null;
		Statement stmt = DB.getStatement(conn);
		int commentNum = 0;
		Calendar cTime = Calendar.getInstance();
		try {
			if(rsCount.next()){
				commentNum = rsCount.getInt(1);
			}
			comments = new String [commentNum][4];
			if(rsCount!=null){
				DB.close(rsCount);
			}
			DB.close(stmtCount);
			String sql = "select * from pollcomments where pollId = '" + pollId + "' order by commentid desc" ;
			rs = DB.getRs(stmt, sql);
			for(int i=0;i<commentNum;i++){
			   rs.next();
		       comments[i][3] = Integer.toString(rs.getInt("commentid"));
		       comments[i][0] = rs.getString("name");
		       comments[i][1] = rs.getString("commentcont");
		       cTime.setTimeInMillis(rs.getTimestamp("commenttime").getTime());//用毫秒数设置时间
		       comments[i][2] = cTime.get(Calendar.YEAR)+"."+(cTime.get(Calendar.MONTH)+1)+"."+cTime.get(Calendar.DAY_OF_MONTH)+". "+cTime.get(Calendar.HOUR_OF_DAY)+":"+cTime.get(Calendar.MINUTE);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs!=null){
				DB.close(rs);
			}
			DB.close(stmt);
		}
		return comments;
	}

	private int[][] initPR(Connection conn, String pollId) {
		initPollSize(conn, pollId);
		int[] optionsId = getOptionsId(conn, pollId);
		int[] membersId = getMembersId(conn, pollId);
		
		return getPollResult(conn, pollId, optionsId, membersId);
	}

	public int[] initPollSize(Connection conn,String pollId){
		Statement stmt1 = DB.getStatement(conn);
		Statement stmt2 = DB.getStatement(conn);
		String sql1 = "select count(*) from polloptions where pollid = '" + pollId + "'";
		ResultSet rs1 = DB.getRs(stmt1, sql1);
		String sql2 = "select count(*) from pollmembers where pollid='" + pollId + "'";
		ResultSet rs2 = DB.getRs(stmt2, sql2);
		try {
			rs1.next();
			rs2.next();			
			pollSize[0] = rs1.getInt(1);
			pollSize[1] = rs2.getInt(1);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(rs1!=null){
				DB.close(rs1);
			}
			if(rs2!=null){
				DB.close(rs2);
			}
			
			DB.close(stmt1);
			DB.close(stmt2);
		}
		return pollSize;
	}
	public int[] getPollSize(String pollId){
		Connection conn = DB.getConn();
		int[] a = initPollSize(conn, pollId);
		DB.close(conn);
		return a;
	}
	public int[] getOptionsId(Connection conn,String pollId){
		int[] optionsId = new int[pollSize[0]];
		Statement stmt = DB.getStatement(conn);
		String sql = "select * from polloptions where pollId ='" + pollId + "' order by options";
		ResultSet rs = DB.getRs(stmt, sql);
		try {
			for (int i = 0; i < pollSize[0]; i++) {
				rs.next();
				optionsId[i]= rs.getInt("optionid");
			} 
	    }catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
		}
		return optionsId;
	}
	public int[] getMembersId(Connection conn,String pollId){
		int[] membersId = new int[pollSize[1]];
		Statement stmt = DB.getStatement(conn);
		String sql =  "select * from pollmembers where pollid = '" + pollId + "'" ;
		ResultSet rs = DB.getRs(stmt, sql);
		try{
			for (int i = 0; i < pollSize[1]; i++) {
		        rs.next();
		        membersId[i] = rs.getInt("memberid");
		     }
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			DB.close(rs);
			DB.close(stmt);
		}
		return membersId;
	}
	
	public int[][] getPollResult(Connection conn, String pollId,int[] optionsId,int[] membersId){
		int[][] pollResult = new int[pollSize[1]][pollSize[0]];
		Statement stmt = DB.getStatement(conn);
		ResultSet rs = null;
		try{
		    for(int i = 0; i < pollSize[1]; i++) {
		      for(int j = 0; j < pollSize[0]; j++){
		       rs = stmt.executeQuery("select itemcont from pollitems where pollid = '" +
		                                   pollId + "' and memberid = " + membersId[i]+ " and optionid = "+ optionsId[j]);
		       if(rs.next()){
		    	   pollResult[i][j] = rs.getInt("itemcont");
		       }
		      }
		    }
		}catch(SQLException e){
			e.printStackTrace();
		}finally{
			if(rs!=null){
				DB.close(rs);
			}
			DB.close(stmt);
		}
		return pollResult;
	}
	
	public PollInformationBean getPollByAdminId(String adminId){
		PollInformationBean poll = new PollInformationBean();
		
		return poll;
	}
	
	public  void inputVote(PollPartiBean pollParti,String parName,List<Option> options){
		Connection conn = DB.getConn();
		
		try {
			conn.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		Statement stmt = DB.getStatement(conn);
		String sql = "insert into pollmembers values(null,?,?)";
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
//System.out.println("select optionid from polloptions where pollid = '" + pollParti.getPollId() +  "' and options = '" +  options.get(i).getOptionContent() + "'");
               rs2 = stmt.executeQuery("select optionid from polloptions where pollid = '" + pollParti.getPollId() +  "' and options = '" +  options.get(i).getOptionContent() + "'");
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
			e.printStackTrace();
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
	
	public void inputComment(String pollId,String name, String comment, String ip){
		Connection conn = DB.getConn();
		String sql = "insert into pollcomments values (null,?,?,?,?,?)";
		PreparedStatement pre = DB.getPrepare(conn, sql);
		String ipaddress = ip;
	    if(ipaddress.length()>25){
	      ipaddress = ipaddress.substring(0,25);
	    }
	    try {
			pre.setString(1, pollId);
			pre.setString(2, name);
			pre.setString(3, ipaddress);
			pre.setTimestamp(4, new Timestamp(new Date().getTime()));
			pre.setString(5, comment);
			pre.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DB.close(pre);
			DB.close(conn);
		}
	}
	public static void main(String[] args)throws Exception {
		new PollMgr().test();
	}
	public void test() throws Exception{
		Connection conn = DB.getConn();
		Statement stmt = DB.getStatement(conn);
		String sql = "select count(*) from polls where pollid = '1111' " ;
		ResultSet rs = DB.getRs(stmt, sql);
		rs.next();
		System.out.println(rs.getInt(1));
	}
}
