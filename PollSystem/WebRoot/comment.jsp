<%@ page language="java" import="java.util.*,polls.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>咕咕：帮您做出选择</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
<link href="css/guuguustyle2.css" rel="stylesheet" type="text/css" />

  </head>
 <style>
<!--
#links{
width:100%;
text-align:center;

}
span.special1{
font-size:22px;
font-weight:bold;

}
span.special2 a{
text-decoration:none;
}
span.special2 a:link,.special2 a:visited{
color:#3399FF;
}
span.special2 a:hover{
text-decoration:underline;
color: #FF850B;
}
span.special3{
color:#3399FF;
}

#content{
width:100%;
color: #484848;
padding-top:10px;
padding-left:180px;


}
input.special4{
border-top:0px;
border-right:0px;
border-left:0px;
border-bottom:1px solid  #3399FF;
}
textarea{
border-color:#3399FF;
border-width:1px;
border-style:solid;

}
input.special5{
padding:0px;
border:none;
color: #3399FF;
background-color:transparent;
}

-->
</style>

<body>
<div id="ahead"></div>
<div id="container">
<div id="banner">
</div>
<div id="main">
<div id="links">
<img src="images/bg15.jpg" /> 
		<% 	
			request.setCharacterEncoding("GBK");
			PollMgr mgr = PollMgr.getInstance();
	        String pollId = request.getParameter("pollId");
	        PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	        PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	        if(poll == null || pollParti == null){
	        	if(pollId == null){
	        		response.sendRedirect("index.jsp");
	        		return;
	        	}else{
	        		pollId = pollId.trim();
	        		if(poll == null){
	        			poll = mgr.getPollById(pollId);
	        			session.setAttribute("poll",poll);
	        		}
	        		if(pollParti == null){
	        			pollParti = mgr.getPollPartiById(pollId);
	        			session.setAttribute("pollParti",pollParti);
	        		}
	        	}
	        }
	        String[][] comments = pollParti.getComments();
	        String action = request.getParameter("action");
	        if(action!=null && action.equals("input")){
	        	if(request.getParameter("comment").trim().equals("")){
					session.setAttribute("cc","您还没做出评论!");
					response.sendRedirect("comment.jsp?pollID=" + poll.getPollId());
					return;
				}else{
					String name = "匿名评论人";
	    			if((String)session.getAttribute("cc")!=null){ session.removeAttribute("cc");}
					if(!request.getParameter("name").trim().equals("")){
						if(request.getParameter("name").trim().length()>10){
							name = request.getParameter("name").trim().substring(0,10);
						}else{
							name = request.getParameter("name").trim();
						}
					}
			
					String comment = new String();
					if(request.getParameter("comment").trim().length()>640){
						comment = request.getParameter("comment").trim().substring(0,640);
					}else{
						comment=request.getParameter("comment").trim();
					}
						mgr.inputComment(poll.getPollId(),name, comment, request.getRemoteAddr());
						session.removeAttribute("pollParti");
						pollParti = mgr.getPollPartiById(poll.getPollId());
	        			session.setAttribute("pollParti",pollParti);
						response.sendRedirect("comment.jsp?pollID=" + poll.getPollId());
						return;
					}
					
	       		 }
		 %>
		 <span class="special1">您所评论的主题是：</span><span class="special2"><a href="executePoll.jsp?pollId=<%=poll.getPollId() %>"><%=poll.getTitle() %></a></span>
		 </div>
<div id="content">
		<% 
			if(comments.length==0){
			}else{	
				for(int i=0; i<comments.length; i++){
					%>
					<span class="special3"><%=comments[i][2] %>&nbsp;<%=comments[i][0] %></span>
					<%=comments[i][1] %>
					<br/>
					<% 				
				} 
			}
			%>
			<form name="form1" method ="post" action = "comment.jsp">
			<input type="hidden" name="action" value="input"/>
			<input type="hidden" name="action" value="<%=poll.getPollId() %>"/>
		  <p><span style="font-size:12px;">
		  <% if((String)session.getAttribute("cc")!=null)
	        { 
	        %>
	        	<%=(String)session.getAttribute("cc") %>
	        <% 
	        }
	  %></span></p>
	  姓名 ：<input class="special4" name="name" type="text" size="10">（评论不能超过640个字,超过的字将被自动删除）<p>
		     <textarea name="comment" cols="80" rows="10"></textarea></p>
		        <p><input class="special5" type="submit" name="Submit" value="提交评论>>"></p>
	           
	      </form>
</div>
</div>
<div id="footer"><a href="index.jsp">主页</a> &nbsp;·&nbsp;<a href="about.htm">关于</a>&nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>