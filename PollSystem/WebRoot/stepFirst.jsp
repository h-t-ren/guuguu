<%@ page language="java" import="java.util.*,polls.*;" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>


		<title>��������������ѡ��</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
	
	<link rel="stylesheet" type="text/css" href="css/guuguustyle.css">
	<script language="javascript" src="script/validate.js"></script>

	</head>
	<%
	
		request.setCharacterEncoding("GBK");
		String typeStr = (String)session.getAttribute("type");
		if(typeStr == null){
			response.sendRedirect("index.jsp");
			return;
		}
	 %>

	<style>
<!--
#content {
	width: 700px;
	margin: 0px;
	border-top: 1px dotted #000000;
	padding: 0px;
	text-align: left;
}

#biaodan {
	margin: 0px;
	padding: 10px 0px 0px 51px;
}

p {
	color: #484848;
}

p span {
	color: #000000;
}

p span.special {
	font-size: 22px;
	font-weight: bold;
	color: #000000;
}

input {
	border: 1px solid #3399FF;
}

textarea {
	border-color: #3399FF;
	border-width: 1px;
	border-style: solid;
}

input.special1 {
	padding-left: 8px;
	border: none;
	color: #3399FF;
	background-color: transparent;
}

p span.warning {
	color: #3399FF;
	font-size: 12px;
}

#biaodan1 {
	margin: 0px;
	padding: 0px 0px 0px 9px;
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
					<li>&nbsp; 
						<a href="index.jsp">������ҳ</a>
					</li>
					<li>
						<a href="index.jsp?type=1&timeout=new">��������</a>
					</li>
					<li>
						<a href="index.jsp?type=2&timeout=new">����ʱ��</a>
					</li>
					<li class="help">
						<a href="help.htm">��������</a>
					</li>
				</ul>
			</div>

			<div id="main">

				<div id="content">
					<div id="biaodan">

     <form name="form1" method="post" action="stepFirst.jsp" onsubmit="return check()">
	 <div id="biaodan1">
	   <%if(typeStr.equals("1")){
	   %>
  <p><span class="special">��������(������):</span><span>�趨һ�������ı���,������˵��</span></p>
  <% } else if(typeStr.equals("2")){ 
  %> 
  <p><span class="special">ѡ��ʱ��(������):</span><span>�趨һ�������ı���,������˵��</span></p>
  <%
   }
   %>
   <%	
   		PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
   		String action = request.getParameter("action");
   		
   		if(poll == null){
   			poll = new PollInformationBean();
   			poll.setTitle("");
   			poll.setDescription("");
   			poll.setInitialName("");
   			poll.setEmail("");
   			poll.setOptionNum(4);
   			poll.setPollType(Integer.parseInt(typeStr));
   			session.setAttribute("poll",poll);
   		}
   		if(action != null && action.equals("add")){
   			poll.setTitle(request.getParameter("title").trim());
   			poll.setDescription(request.getParameter("descrip").trim());
   			poll.setInitialName(request.getParameter("initialname").trim());
   			poll.setEmail(request.getParameter("email").trim());
   			String submit = request.getParameter("submit");
   			if(submit.equals("ȡ��>>")){
   				session.invalidate();
   				response.sendRedirect("index.jsp");
   				return;
   			}else {
   				String type = (String)session.getAttribute("type");
   				if(type.equals("1")){
   					response.sendRedirect("setContent.jsp");
   					return;
   				}
   				else if(type.equals("2")){
   					response.sendRedirect("selectDate.jsp");
   					return;
   				}
   			}
   		}
    %>

  <p>����:<span class="warning" id="titlewarning"></span><br />
    <input type="text" size="62" name="title" value="<%=poll.getTitle() %>" onblur="checkTitle(this)">
  </p>
  <p>˵��(��ѡ��):<span class="warning" id="descriptionwarning"></span><br /> 
    <textarea name="descrip" cols="60" rows="10" onblur="checkDesc(this)"><%=poll.getDescription() %></textarea>
  </p>
  <p>�����������ǳ�:<span class="warning" id= "namewarning" ></span><br />
    <input type="text"  size="62" name="initialname" value="<%=poll.getInitialName() %>" onblur="checkName(this)">
  </p>
  <p>���ĵ�������(��ѡ��):<span class="warning" id= "emailwarning"> </span><br />
    <input type="text"  size="62" name="email" value= "<%=poll.getEmail() %>" onblur ="checkEmail(this)">
  </p>
  </div>
  <input type="hidden" name="action" value="add"/>
  <input type="submit" name="submit" value="��һ��>>" class="special1" >
        <input type="submit" name="submit" value="ȡ��>>" class="special1">
		</form>
		</div>
</div>
</div>
<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>
