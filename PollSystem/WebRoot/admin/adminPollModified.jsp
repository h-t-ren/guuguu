<%@ page language="java" import="java.util.*,polls.*,java.sql.Timestamp" pageEncoding="GBK"%>


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
width:680px;
margin:0px;
padding-left:20px;
color:#484848;
border-top:1px dotted #000000;

}
span.special1{
font-size:22px;
font-weight:bold;
color:#3399FF;
}
span.special2{
color:#3399FF;
}
#content a{
text-decoration:none;
font-size:16px;
}
#content a:link,#content a:visited{
color:#3399FF;
}
#content a:hover{
color: #FF850B;
text-decoration:line-through;
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
    <li><a href="../index.jsp?type=1&timeout=new">����ʱ��</a></li>
    <li class="help"><a href="help.htm">��������</a></li>
  </ul>
</div>

<%
		String addr = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
  		String path = request.getContextPath();
  		addr = addr + path + "/";
  		
  		request.setCharacterEncoding("GBK");
		if(session.getAttribute("warn")!=null){
			session.removeAttribute("warn");
		}
		PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
		poll.setUpdateTime(new Timestamp(new Date().getTime()));
		PollAdminMgr mgr = PollAdminMgr.getInstance();
		String pollURL = addr + "executePoll.jsp?pollId=" + poll.getPollId();
		String adminURL = addr + "admin/AdminIndex.jsp?adminId=" + poll.getAdminId();
		mgr.updatePoll(poll);
		
				
		 if(!poll.getEmail().equals("")){
			  String emailbody= "���Ѿ��޸����ڹ����Ϸ����ͶƱ����ַΪ:\r\n\r\n";
			  emailbody=emailbody+ pollURL +"\r\n\r\n";
			  emailbody=emailbody+ "������ϵ�ַ�������ܲμ�ͶƱ���ˡ�\r\n\r\n";
			  emailbody=emailbody+ "�����û�з������ͶƱ�����������������ĵ����ʼ���ַ���������ͶƱ���벻�ù��������ʼ���\r\n\r\n";
			  //sendEmail.send((String) session.getAttribute("title"),"����",emailbody, (String) session.getAttribute("email"));
			
			  String adminEmail= "�벻Ҫת���������ʼ�!\r\n\r\n";
			  adminEmail=adminEmail+ "������ͨ�����µ�ַ���༭�������ͶƱ��" + poll.getTitle() + "\r\n\r\n";
			  adminEmail=adminEmail+ adminURL +"\r\n\r\n";
			  adminEmail=adminEmail+ "�����Ʊ����������ʼ����Ա����Ժ�Դ�ͶƱ���б༭��Ϊ��ֹ���˸Ķ��������ͶƱ���벻Ҫ��������ʼ�\r\n\r\n";
			  adminEmail=adminEmail+ "�����û�з������ͶƱ������������������email��ַ���������ͶƱ���벻�ù����email��\r\n\r\n";
		     //sendEmail.send("�༭ͶƱ--"+(String)session.getAttribute("title"),"����", adminEmail, (String) session.getAttribute("email"));
		}
 %>

<div id="main">

<div id="content">
  <p><span class="special1">��л��ʹ�ù�����</span></p>
   <%
		String email = poll.getEmail();
			if(!(email.equals(""))) {
				%>
				<p>���Ѿ��ɹ��޸���һ��ͶƱ,��ͶƱ�ĵ�ַ�Ѿ������͵����ṩ�ĵ�������:<span class="special2"><%=poll.getEmail() %></span></p>
				<p><span class="special2">��������</span>�����԰�ͶƱ�ĵ�ַת������Ҫ�Ĳμ���,���ǿ������øõ�ַ�������ǵ�ѡ�������յ���ͶƱ�����½����</p>
				<p>���������������һ�����ʼ�,�ں���������ͶƱ�ĵ�ַ���������ڸõ�ַ���޸ġ�ɾ������ͶƱ,Ҳ���԰�ͶƱ���ת��ΪExcel�ļ���</p>
				<p>�����û�յ������ʼ�,��ȷ��������ĵ��������ַ�Ƿ�����,�������,����Ҫ���·���һ��ͶƱ;������������ַû�д���,�����鿴�Ƿ������ĵ��������ṩ�̻����Ƿ���ǽ�Ѹ��ʼ�������,��Ҳ���Է���һ���������������ĵ��顣</p>
			<%
			}else{
			%>
				<p>���Ѿ��ɹ��޸���һ��ͶƱ,��ͶƱ�ĵ�ַΪ��</p>
				<p><a href="<%=pollURL %>"><%=pollURL %></a></p>
				<p><span class="special2">������:</span>�����԰�ͶƱ�ĵ�ַת������Ҫ�Ĳμ���,���ǿ������øõ�ַ�������ǵ�ѡ��</p>
				<p>�������Ʊ������ϵ�ַ,����Ҫ�õ�ַ���鿴ͶƱ�����½����</p>
				<p>&nbsp;</p>
				<p>������ͨ�����µ�ַ���޸ġ�ɾ���������ͶƱ,Ҳ���԰�ͶƱ���ת��ΪExcel�ļ���</p>
				<p><a href="<%=adminURL %>"><%=adminURL %></a></p>
				<p>�������Ʊ������ϵ�ַ,����Ҫ�����õ�ַ,�Է������޸�ͶƱ��</p>
			<%
			}
		 	 session.invalidate();
		%>
</div>
</div>
<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>