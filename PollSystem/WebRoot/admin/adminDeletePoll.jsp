<%@ page language="java" import="java.util.*,polls.*,calendars.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>咕咕：帮您做出选择</title>
    
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
    <li><a href="../index.jsp">咕咕首页</a></li>
    <li><a href="../index.jsp?type=1&timeout=new">安排内容</a></li>
    <li><a href="../index.jsp?type=2&timeout=new">安排时间</a></li>
    <li class="help"><a href="help.htm">咕咕帮助</a></li>
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
	 	 if(str.equals("删除")){
			
			mgr.deletePoll(adminId);
			poll.setDeleted(true);
			response.sendRedirect("adminIndex.jsp?adminId="+adminId);
			return;
		 }
 
		 if(str.equals("取消")){
			response.sendRedirect("adminIndex.jsp?adminId="+adminId);
			return;
		 }
	
	
	
	}
 %>

<div id="main">
<div id="content"><p><span class="special1">咕咕删除后,将不能恢复,确定要删除这个咕咕吗?</span></p>
<div id="biaodan">
<form action="adminDeletePoll.jsp" method="post">
<input type="hidden" name ="action" value="submit"/>
<img src="../images/bg16.jpg" />
	 <input name="deconfirm" type="submit" value="删除" > 
	 <input name="deconfirm" type="submit" value="取消" >
	 
	  </form></div></div>
</div>
<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>
