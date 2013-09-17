<%@ page language="java" import="java.util.*,polls.*,java.sql.Timestamp" pageEncoding="GBK"%>


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
	<li><a href="../index.jsp">咕咕首页</a></li>
    <li><a href="../index.jsp?type=1&timeout=new">安排内容</a></li>
    <li><a href="../index.jsp?type=1&timeout=new">安排时间</a></li>
    <li class="help"><a href="help.htm">咕咕帮助</a></li>
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
			  String emailbody= "您已经修改了在咕咕上发起的投票，地址为:\r\n\r\n";
			  emailbody=emailbody+ pollURL +"\r\n\r\n";
			  emailbody=emailbody+ "请把以上地址发给可能参加投票的人。\r\n\r\n";
			  emailbody=emailbody+ "如果您没有发起这个投票，那是有人用了您的电子邮件地址发起了这个投票，请不用管这封电子邮件。\r\n\r\n";
			  //sendEmail.send((String) session.getAttribute("title"),"咕咕",emailbody, (String) session.getAttribute("email"));
			
			  String adminEmail= "请不要转发这封电子邮件!\r\n\r\n";
			  adminEmail=adminEmail+ "您可以通过以下地址来编辑您发起的投票：" + poll.getTitle() + "\r\n\r\n";
			  adminEmail=adminEmail+ adminURL +"\r\n\r\n";
			  adminEmail=adminEmail+ "请妥善保存这封电子邮件，以便您以后对此投票进行编辑。为防止他人改动您发起的投票，请不要公开这封邮件\r\n\r\n";
			  adminEmail=adminEmail+ "如果您没有发起这个投票，那是有人用了您的email地址发起了这个投票，请不用管这封email。\r\n\r\n";
		     //sendEmail.send("编辑投票--"+(String)session.getAttribute("title"),"咕咕", adminEmail, (String) session.getAttribute("email"));
		}
 %>

<div id="main">

<div id="content">
  <p><span class="special1">感谢您使用咕咕！</span></p>
   <%
		String email = poll.getEmail();
			if(!(email.equals(""))) {
				%>
				<p>您已经成功修改了一个投票,该投票的地址已经被发送到您提供的电子邮箱:<span class="special2"><%=poll.getEmail() %></span></p>
				<p><span class="special2">接下来：</span>您可以把投票的地址转给您想要的参加者,他们可以利用该地址给出他们的选择。您将收到该投票的最新结果。</p>
				<p>我们另外给您发了一电子邮件,内含管理您的投票的地址。您可以在该地址上修改、删除您的投票,也可以把投票结果转存为Excel文件。</p>
				<p>如果您没收到电子邮件,请确认您输入的电子邮箱地址是否有误,如果有误,您需要重新发起一个投票;如果电子邮箱地址没有错误,请您查看是否是您的电子信箱提供商或者是防火墙把该邮件屏蔽了,您也可以发起一个不输入电子邮箱的调查。</p>
			<%
			}else{
			%>
				<p>您已经成功修改了一个投票,该投票的地址为：</p>
				<p><a href="<%=pollURL %>"><%=pollURL %></a></p>
				<p><span class="special2">接下来:</span>您可以把投票的地址转给您想要的参加者,他们可以利用该地址给出他们的选择。</p>
				<p>请您妥善保存以上地址,您需要该地址来查看投票的最新结果。</p>
				<p>&nbsp;</p>
				<p>您可以通过以下地址来修改、删除您发起的投票,也可以把投票结果转存为Excel文件。</p>
				<p><a href="<%=adminURL %>"><%=adminURL %></a></p>
				<p>请您妥善保存以上地址,并不要公开该地址,以防他人修改投票。</p>
			<%
			}
		 	 session.invalidate();
		%>
</div>
</div>
<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>