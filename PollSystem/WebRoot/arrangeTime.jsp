<%@ page language="java" import="java.util.*;" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		

		<title>咕咕：帮您做出选择</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		
	<link rel="stylesheet" type="text/css" href="css/guuguustyle.css">


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

body#current1 #links ul li.shijian {
	border-bottom: #FFFFFF 1px solid;
}

body#current1 #links ul li.shijian a:link,body#current1 #links ul li.shijian a:visited
	{
	background-color: #FFFFFF;
	color: #000000;
}

body#current1 #links ul li.shijian a:hover {
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
-->
</style>

	<body id="current1">
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
						<a href="index.jsp?type=1&timeout=new">安排内容</a>
					</li>
					<li>
						<a href="index.jsp?type=2&timeout=new">安排时间</a>
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
							咕咕帮您安排时间
						</li>
						<li>
							吃饭、聚会、旅游等活动的时间安排,想选择出一个适合大多数人的时间时 ...
						</li>
						<li class="special1">
							<a href="index.jsp?type=2">点击这里安排一个时间&gt;&gt;</a>&nbsp;&nbsp;
							<img src="images/bg5.jpg" />
						</li>
					</ul>
				</div>
			</div>
			<div id="footer">
				<a href="about.htm">关于</a> &nbsp;·&nbsp;
				<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp;
				<a href="contact.htm">联系我们</a>
			</div>
		</div>
	</body>
</html>
