<%@ page language="java" import="java.util.*,polls.*,calendars.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>��������������ѡ��</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="../css/guuguustyle1.css" rel="stylesheet" type="text/css" />

  </head>
  
<style>
<!--
#content{
width:673px;
margin:0px;
padding-left:20px;
padding-top:20px;
border-top:1px dotted #000000;
height:160px;

}
span.special1{
font-size:22px;
font-weight:bold;

}
#content input{
border:0px;
color: #3399FF;
background-color:transparent;
font-size:16px;
}
#biaodan{
padding-top:40px;
padding-left:20px;
}
-->
</style>

<body>

<div id="ahead"></div>
<div id="container">
<div id="banner">
</div>
<div id="daohang">
  <ul>
    <li><a href="../index.jsp">������ҳ</a></li>
    <li><a href="../index.jsp?type=1&timeout=new">��������</a></li>
    <li><a href="../index.jsp?type=2&timeout=new">����ʱ��</a></li>
    <li class="help"><a href="help.htm">��������</a></li>
  </ul>
</div>
<%
	request.setCharacterEncoding("GBK");
  	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	PollAdminMgr mgr = PollAdminMgr.getInstance();
	String adminId = poll.getAdminId();
	String action = request.getParameter("action");
	if(action!=null && action.equals("submit")){
	 String str = request.getParameter("deconfirm");
	 	 if(str.equals("ɾ��")){
			
			mgr.deletePoll(adminId);
			poll.setDeleted(true);
			response.sendRedirect("adminIndex.jsp?adminId="+adminId);
			return;
		 }
 
		 if(str.equals("ȡ��")){
			response.sendRedirect("adminIndex.jsp?adminId="+adminId);
			return;
		 }
	
	
	
	}
 %>

<div id="main">
<div id="content"><p><span class="special1">����ɾ����,�����ָܻ�,ȷ��Ҫɾ�����������?</span></p>
<div id="biaodan">
<form action="adminDeletePoll.jsp" method="post">
<input type="hidden" name ="action" value="submit"/>
<img src="../images/bg16.jpg" />
	 <input name="deconfirm" type="submit" value="ɾ��" > 
	 <input name="deconfirm" type="submit" value="ȡ��" >
	 
	  </form></div></div>
</div>
<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>
