<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>咕咕：帮您做出选择</title>
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/css/guuguu.css" />" />
<link type="text/css" rel="stylesheet"
	href="<c:url value="/resources/js/fullcalendar/fullcalendar.css" />" />
<link rel="stylesheet" href="<c:url value="/resources/js/table/table.css" />" type="text/css" media="screen, projection" />
<script type="text/javascript"
	src="<c:url value="/resources/js/lib/jquery.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/lib/jquery-ui.custom.min.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/fullcalendar/fullcalendar.js" />"></script>
<script type="text/javascript"
	src="<c:url value="/resources/js/validate.js" />"></script>
<script type="text/javascript" src="<c:url value="/resources/js/table/jquery.dataTables.js" />"></script>	
		

</head>

<body>

	<div id="ahead"></div>
	<div id="container">
		<div id="banner"></div>
		<div id="daohang">
			<tiles:insertAttribute name="submenu" />
		</div>
		<div id="main">
			<div id="content">
				<tiles:insertAttribute name="body" />
			</div>
		</div>
		<div id="footer">
			Copyright &copy; 2012-
			<script type="text/javascript"> var d = new Date();document.write(d.getFullYear());</script>
			&nbsp; xxxx
		</div>
	</div>

</body>

</html>