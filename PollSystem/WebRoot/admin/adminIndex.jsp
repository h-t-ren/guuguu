<%@ page language="java" import="java.util.*,polls.*" pageEncoding="GBK"%>


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

#links{
width:610px;
border-top:1px dotted #000000;
margin:0px;
padding:0px 0px 0px 80px;
height:220px;
}
 ul.ul1{
list-style-type:none;

margin:0px;
padding:0px;


}
ul.ul1 li{
float:left;
font-size:18px;
margin-right:10px;
padding:0px;

}
ul.ul1 li a{
display:block;
padding:44px 15px 44px 15px;
margin:0px;
background-color:#0071E1;
text-decoration:none;
}
ul.ul1 li a:link,ul.ul1 li a:visited{
color: #000000;
border-bottom:2px #003F7D solid;
border-right:2px #003F7D solid;
border-top:2px #eeeeee solid;
border-left: 2px #eeeeee solid;

}
ul.ul1 li a:hover{
padding:49px 12px 39px 18px;
border-top:2px #003F7D solid;
border-left:2px #003F7D solid;
border-bottom:2px #eeeeee solid;
border-right: 2px #eeeeee solid;
color: #FFFFFF;
background-color: #4242FF;
}


#content{
width:610px;
height:140px;
margin:0px;
padding:0px 0px 0px 80px;
}
span.special1{
color: #0075EA;
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

<div id="main">
<div id="links"><p><img src="../images/bg12.jpg" />���ã������ǹ���������棬�����Թ����������ͶƱ�� 
	<%
		String adminId = request.getParameter("adminId");
		if(adminId != null){
			adminId = adminId.trim();
		}else{
			response.sendRedirect("../index.jsp");
		}
	
		
		PollAdminMgr mgr = PollAdminMgr.getInstance();
		PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
		PollPartiBean pollParti = null;
		if(poll == null){
			poll = mgr.getPollByAdminId(adminId);
			poll.setAdminId(adminId);
			session.setAttribute("poll",poll);
		}
		if(poll.getPollCreateTime() != null){
		pollParti = (PollPartiBean)session.getAttribute("pollParti");
		
		if(pollParti == null){
			pollParti = mgr.getPollPartiByAdminId(adminId);
			session.setAttribute("pollParti",pollParti);
		}
		}else{
			pollParti = new PollPartiBean();
		}
		System.out.println(poll.isDeleted());
		if(poll.isDeleted()){
			response.sendRedirect("../pollDeleted.jsp");
			return;
		}
		String action = request.getParameter("action");
		String pollURL="../executePoll.jsp?pollId=" + poll.getPollId();
		
		if(action!=null && action.equals("editpoll")){
			response.sendRedirect("adminStepFirst.jsp");
			//out.println(1);
			return;
		}else if(action!=null && action.equals("editmember")){
			response.sendRedirect("adminEditMembers.jsp");
			return;
		}else if(action!=null && action.equals("editcomment")){
			response.sendRedirect("adminEditComments.jsp");
			return;
		}else if(action!=null && action.equals("outputresult")){
			response.sendRedirect("exportToExcel.jsp");
			return;
		}else if(action!=null && action.equals("deletepoll")){
			response.sendRedirect("adminDeletePoll.jsp");
			return;
		}
		
	   %>
</p>
<ul class="ul1">
<li><a href="adminIndex.jsp?adminId=<%=adminId %>&action=editpoll">�༭ͶƱ����</a></li>
<li><a href="adminIndex.jsp?adminId=<%=adminId %>&action=editmember">�༭�μ���Ա</a></li>
<li><a href="adminIndex.jsp?adminId=<%=adminId %>&action=editcomment">�༭��������</a></li>
</ul>
</div>
<div id="content">
<ul class="ul1">
<li><a href="adminIndex.jsp?adminId=<%=adminId %>&action=outputresult">����ͶƱ���</a></li>
<li><a href="adminIndex.jsp?adminId=<%=adminId %>&action=deletepoll">ɾ������ͶƱ</a></li>
<li><a href="<%=pollURL %>" >�鿴ͶƱ���</a></li>
</ul>

</div>
</div>
<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>
