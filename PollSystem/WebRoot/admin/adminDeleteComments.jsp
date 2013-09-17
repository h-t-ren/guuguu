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


}
span.special1{
font-size:16px;
font-weight:bold;
}

#content input{
border:0px;
color:#3399FF;
background-color:transparent;

}

table.table1{
border:1px solid #005984;
border-collapse:collapse;
text-align:center;
}

table.table1 th{
border:1px solid #005984;
padding:4px 8px;
text-align:center;
background-color:#3399FF;
}
table.table1 td{
text-align:center;
border:1px solid #005984;
}
#biaodan{
padding-left:150px;
}
table.table1 tr.altrow{
background-color: #4242FF;
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

<div id="main">
<div id="content"><p><span class="special1">确定要删除所选评论吗？</span></p>

<p> 以下为您选择的将要被删除的记录</p>
<div id="biaodan">
 <% 
	request.setCharacterEncoding("GBK");
  	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	PollAdminMgr mgr = PollAdminMgr.getInstance();
	int n = mgr.getCommentNum(poll.getPollId());
	String[][] comments = pollParti.getComments();
	int[][] delSel = (int[][])session.getAttribute("delSel");
	String action = request.getParameter("action");
	
	if(action!=null && action.equals("submit"))
	{
		String submitType = request.getParameter("deconfirm");
		
	 if(submitType.equals("删除")){
		mgr.deleteComment(delSel,poll.getPollId());
		pollParti.setComments(mgr.getComments(poll.getPollId()));
		response.sendRedirect("adminEditComments.jsp");
		return;
 	}
 
	 if(submitType.equals("取消")){
		response.sendRedirect("adminEditComments.jsp");
		return;
 	}
	
	
	}
	%>
	<table class="table1">
	<tr>
	<th>评论人姓名</th>
	<th>评论时间</th>
	<th>评论内容</th>
	</tr>
	<% 
	for(int i=0; i<comments.length; i++){
			if(delSel[i][1]==1){		
					
					out.print("<tr >");		
					out.println("<td >"+comments[i][0]+"</td>");
					out.println("<td >"+comments[i][2]+"</td>");
  					out.println("<td >"+ comments[i][1]+"</td>");
					out.print("</tr>");
			}	
			
	} 
	
%>	
 </table>
<p></p><form action="adminDeleteComments.jsp" method="post">
<input type="hidden" name="action" value= "submit"/>
 <input name="deconfirm" type="submit" value="删除" /> 
	 <input name="deconfirm" type="submit" value="取消" /></form>
	 <br/>
	  </div></div>
</div>
<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
<script language="javascript">
var rows=document.getElementsByTagName("tr");
for(var i=0;i<rows.length;i++)
{
rows[i].onmouseover=function(){
this.className+="altrow";
}
rows[i].onmouseout=function(){
this.className=this.className.replace("altrow","");
}
}

</script>
</body>
</html>