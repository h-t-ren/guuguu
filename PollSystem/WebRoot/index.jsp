<%@ page language="java" import="java.util.*,util.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>咕咕：帮您做出选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">

		<link href="css/guuguustyle.css" rel="stylesheet" type="text/css" />

	</head>
	<style>
<!--
#links {
	width: 100%;
	margin: 0px;
	height: 60px;
	padding: 0px;
}

#links ul {
	font-weight: bold;
	padding: 0px;
	margin: 0px;
	list-style-type: none;
	padding-bottom: 57px;
	border-bottom: 1px #3399FF solid;
}

#links ul li {
	float: left;
	font-size: 18px;
	margin: 0px;
	padding: 0px;
	text-align: center;
	border: 1px #3399FF solid;
}

#links ul li a {
	margin: 0px;
	padding: 18px;
	display: block;
}

#links ul li a:link,#links ul li a:visited {
	color: #000000;
	background-color: #B3D9FF;
	text-decoration: none;
}

#links ul li a:hover {
	color: #FFFFff;
	background: #3399FF;
}

body#current #links ul li.neirong {
	border-bottom: #FFFFFF 1px solid;
}

body#current #links ul li.neirong a:link,body#current #links ul li.neirong a:visited
	{
	background-color: #FFFFFF;
	color: #000000;
}

body#current #links ul li.neirong a:hover {
	color: #3399FF;
}

#content {
	width: 698px;
	border-right: #3399FF 1px solid;
	border-bottom: #3399FF 1px solid;
	border-left: #3399FF 1px solid;
	border-top: #FFFFFF 1px solid;
	margin-top: 0px;
	padding: 0px;
	height: 236px;
}

#content ul li.special2 {
	border-bottom: #000000 dotted 1px;
	font-size: 22px;
	color: #000000;
	padding: 40px 10px 40px 20px;
	width: 600px;
	font-weight: bold;
}

#content ul {;
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	font-size: 18px;
}

#content ul li {
	padding: 5px 10px 5px 20px;;
}

#content ul li.special1 a {
	text-decoration: none;
	position: relative;
	top: -40px;
}

#content ul li.special1 a:link,#content ul li.special1 a:visited {
	color: #3399FF;
}

#content ul li.special1 a:hover {
	color: #FF850B;
	text-decoration: underline;
}

span.special3 {
	margin: 0px;
	font-size: 12px;
}

#footer span.special3 a {
	text-decoration: underline;
}

#footer span.special3 a:link,#footer span.special3 a:visited,#footer span.special3 a:hover
	{
	color: #484848;
}
-->
</style>

<%
	if(session.isNew()){
		int count = CountVisitNum.getCount();
		count = count + 1;
		CountVisitNum.updateCount(count);
	}

 %>
<%	
	String typeStr = request.getParameter("type");
	String timeOut = request.getParameter("timeout");
	if(typeStr != null){
		int type = 0;
		try{
			 type = Integer.parseInt(typeStr);
		}catch(NumberFormatException e){
			type = 1;
		}
		if(timeOut != null && timeOut.equals("newpoll")){
			if(type == 1){
				response.sendRedirect("newPoll.jsp?type=1");
				return;
			}else if(type == 2){
				response.sendRedirect("newPoll.jsp?type=2");
				return;
			}
			
		}
		if(type == 1 || type == 2){
			session.setAttribute("type",typeStr);
			response.sendRedirect("stepFirst.jsp");
			return;
		}
	}
 %>

<body id="current">


		<div id="ahead"></div>
		<div id="container">
			<div id="banner">
			</div>
			<div id="daohang">
				<ul>
					<li>
						<a href="index.jsp">咕咕首页</a>
					</li>
					<li>
						<a href="index.jsp?type=1&timeout=newpoll">安排内容</a>
					</li>
					<li>
						<a href="index.jsp?type=2&timeout=newpoll">安排时间</a>
					</li>
					<li class="help">
						<a href="help.htm">咕咕帮助</a>
					</li>
				</ul>
			</div>

			<div id="main">
				<div id="links">
					<ul>
						<li class="neirong">
							<a href="index.jsp">安排适当的内容</a>
						</li>
						<li class="shijian">
							<a href="arrangeTime.jsp">选择合适的时间</a>
						</li>
					</ul>
				</div>
				<div id="content">
					<ul>
						<li class="special2">
							咕咕帮您做出选择
						</li>
						<li>
							出去吃饭、旅游、聚会等等活动,当有数个选项,想知道哪个选项最受欢迎时...
						</li>
						<li class="special1">
							<a href="index.jsp?type=1">点击这里安排一项活动&gt;&gt;</a>&nbsp;&nbsp;
							<img src="images/bg4.jpg" />
						</li>
					</ul>
				</div>
			</div>
			<div id="footer">
				<a href="about.htm">关于</a> &nbsp;·&nbsp;
				<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp;
				<a href="contact.htm">联系我们</a>
				<br />
				<br />
				<span class="special3">@2010 guuguu <a
					href="http://www.miibeian.gov.cn">沪ICP备10006207号</a> </span>
			</div>
		</div>
	</body>
</html>
