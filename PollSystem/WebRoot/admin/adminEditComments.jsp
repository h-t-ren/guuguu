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

#links{
width:670px;
border-top:1px dotted #000000;
margin:0px;
padding:20px 0px 0px 20px;

}
#content{


}
#biaodan{
padding-left:150px;
}

table.table1{
border:1px solid #005984;
border-collapse:collapse;
text-align:center;
}
table.table1 caption{

font-size:16px;
}
table.table1 caption span{
color:#3399FF;
}
table.table1 th{
border:1px solid #005984;
padding:4px 8px;
text-align:center;
}
table.table1 td{
border:1px solid #005984;
text-align:center;
}
input.special1{
border:0px;
color:#3399FF;
background-color:transparent;
}
tr.jishu{
background-color:#3399FF;
}
tr.oushu{
background-color:#D7EBFF;
}
p.special2{
font-size:18px;
font-weight:bold;
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
<div id="links">
  <p class="special2">���ã������ǲ鿴�ͱ༭���۽���</p>
  <% 
  	request.setCharacterEncoding("GBK");
  	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	PollAdminMgr mgr = PollAdminMgr.getInstance();
	String action = request.getParameter("action");
	if(action!=null&&action.equals("submit")){
		String submitType = request.getParameter("editcomment");
	 if(submitType.equals("ȫѡ")){
		boolean selectAll=true;
		session.setAttribute("selectAll",selectAll);
		response.sendRedirect("adminEditComments.jsp");
		return;
	 }
 
	  if(submitType.equals("ȡ��ȫѡ")){
		boolean selectAll=false;
		session.setAttribute("selectAll",selectAll);
		response.sendRedirect("adminEditComments.jsp");
		return;
	 }
	 
	 if(submitType.equals("ɾ����ѡ")){
		String adminID = poll.getAdminId();
		String[] IDList = (String[]) session.getAttribute("IDList");
		int[][] delSel=new int[IDList.length][2];
		
		for(int i=0; i<IDList.length; i++){
			delSel[i][0] = Integer.parseInt(IDList[i]);
			if(request.getParameter(IDList[i])!=null){
			delSel[i][1]=1;
			
			}else{
				delSel[i][1]=0;
			}
			//out.println(delSel[i][0]+"delete"+delSel[i][1]);
		} 
	    session.setAttribute("delSel",delSel);	 
		response.sendRedirect("adminDeleteComments.jsp");
		return;
	 }
	
	
	}
	
	int n = mgr.getCommentNum(poll.getPollId());
	String[][] comments = pollParti.getComments();
	String[] IDList = new String[comments.length];//һ�������������������	
	boolean selectAll = false;
	if (session.getAttribute("selectAll")!=null){
		selectAll=(Boolean)session.getAttribute("selectAll");
	}else {	
		session.setAttribute("selectAll", selectAll);
	}
 	 if(comments.length==0){
				out.print("��ͶƱû���κ�����");
	}
		%>
  </div>
<div id="content">
<div id="biaodan">
<form action="adminEditComments.jsp" method="post">
<input type="hidden" name="action" value="submit"/>
	<table class="table1">
	<caption>��ǰ����:<span><%=poll.getTitle() %></span></caption>
	<tr class="jishu">
	<td width="20px">&nbsp;</td>
	<th>����������</th>
	<th>����ʱ��</th>
	<th>��������</th>
	</tr>
<%
		
			if(comments.length!=0){
			
				//show comments
				for(int i=0; i<comments.length; i++){
					if(i%2==0){
					out.print("<tr class=oushu>");}
					else{
					out.print("<tr class=jishu>");
					}
					if(selectAll){
						out.println("<td ><input name=" +comments[i][3]+" type=checkbox checked = true></td>");
					}
					else{
						out.println("<td ><input name=" +comments[i][3]+" type=checkbox ></td>");
					}
					
					IDList[i]=comments[i][3];
					out.println("<td >"+comments[i][0]+"</td>");
					out.println("<td >"+comments[i][2]+"</td>");
  					out.println("<td >"+ comments[i][1]+"</td>");
					out.print("</tr>");
				} 
			}
			
			
	session.setAttribute("IDList",IDList);		
%>	 
</table>
<p>
	<input  class="special1" name="editcomment" type="submit" value = "ȫѡ" >
	<input  class="special1" name="editcomment" type="submit" value = "ȡ��ȫѡ" >
	<input  class="special1" name="editcomment" type="submit" value = "ɾ����ѡ" >
	</p>
	</form>
	</div>
</div>
</div>
<div id="footer"><a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>
