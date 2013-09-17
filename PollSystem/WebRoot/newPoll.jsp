<%@ page language="java" import="java.util.*;" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>My JSP 'newPoll.jsp' starting page</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

	</head>
	<%
	
	session.invalidate();
	String typeStr = request.getParameter("type");
	
	if(typeStr != null){
		int type = 0;
		try{
			 type = Integer.parseInt(typeStr);
		}catch(NumberFormatException e){
			type = 1;
		}
		if(type == 1){
			response.sendRedirect("index.jsp?type=1");
			return;
		}else if(type == 2){
			response.sendRedirect("index.jsp?type=2");
			return;
		}
	}
	%>
</html>
