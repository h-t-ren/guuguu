<%@ page language="java" import="java.util.*,polls.*,calendars.*"pageEncoding="GBK"%>


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
<script language="javascript" src="../script/validate.js"></script>
  </head>
  <style>
<!--


#content{
width:700px;
margin:0px;
border-top:1px dotted #000000;
padding:0px;
text-align: left;
}
#biaodan{
margin:0px;
padding:10px 0px 0px 51px;
}
p{
color:#484848;
}
p span{
color:#000000;
}
p span.special{
font-size:22px;
font-weight:bold;
color:#000000;
}
input{
border:1px solid  #3399FF;
}
textarea{
border-color:#3399FF;
border-width:1px;
border-style:solid;
}
input.special1{
padding-left:8px;
border:none;
color:#3399FF;
background-color:transparent;
}
p span.warning{
color:#3399FF;
font-size:12px;
}
#biaodan1{
margin:0px;
padding:0px 0px 0px 9px;
}

-->
</style>

<body>
<div id="ahead"> </div>
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

<div id="main">

<div id="content">
<div id="biaodan">
	<%
	
		request.setCharacterEncoding("GBK");
		PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
		String action = request.getParameter("action");
		if(action != null && action.equals("add")){
   			poll.setTitle(request.getParameter("title").trim());
   			poll.setDescription(request.getParameter("descrip").trim());
   			poll.setInitialName(request.getParameter("initialname").trim());
   			poll.setEmail(request.getParameter("email").trim());
   			String submit = request.getParameter("submit");
   			if(submit.equals("取消>>")){
   				session.invalidate();
   				response.sendRedirect("../index.jsp");
   				return;
   			}else {
   				int type = poll.getPollType();
   				if(type==1){
   					response.sendRedirect("adminSetContent.jsp");
   					return;
   				}
   				else if(type==2){
   					CalendarMgr mgr = CalendarMgr.getInstance();
   					MyCalendar mc =  mgr.getMyCalendar();
   					session.setAttribute("MyCalendar",mc);
   					
 					List<Option> options = poll.getOptions();
					String[] dayoptions = new String[options.size()];
					boolean[] repeated = new boolean[options.size()];
					int nOfRe = 0;
	
					for(int i=0; i<options.size(); i++){
						repeated[i] = false;
						dayoptions[i] = options.get(i).getOptionContent().trim().substring(0, options.get(i).getOptionContent().trim().indexOf("time"));
						if(i>0){
							for(int j=0; j<i; j++){
								if(dayoptions[j].equals(dayoptions[i])){
								repeated[i] = true;
								nOfRe++;
								break;
								}
							}
						}
					}
	
					String[] op = new String[options.size()-nOfRe]; //cleared (no-repeated) array of selected days
					int nn = 0;
					for(int i=0; i<dayoptions.length; i++){
						if(!repeated[i]){//记录下不重复的时间
							op[nn] = dayoptions[i];
							nn ++;
						}
					}
	
	            List[] opList = new LinkedList[op.length]; //list of selected day with different time
	            for(int i=0; i<opList.length; i++){
					List aOption = new LinkedList();//又重新定义新的链表
					aOption.add(op[i]);
					for(int j=0; j<options.size(); j++){
						if(options.get(j).getOptionContent().trim().substring(0, options.get(j).getOptionContent().trim().indexOf("time")).equals(op[i]) && options.get(j).getOptionContent().trim().substring(options.get(j).getOptionContent().trim().indexOf("time")).length()>4){
							aOption.add(options.get(j).getOptionContent().trim().substring(options.get(j).getOptionContent().trim().indexOf("time")+4));
						}
					}
					opList[i] = aOption;//时间串链表
	
				}
	 
				session.setAttribute("opList",opList);
	
				List selected = new LinkedList(); //list of selected days
	
				for(int i=0; i<opList.length; i++){
				  int[] aday = new int[3];
				  aday[0] = Integer.parseInt(op[i].substring(0,4));//年
				  aday[1] = Integer.parseInt(op[i].substring(5,op[i].lastIndexOf(".")))-1;//月
				  aday[2] = Integer.parseInt(op[i].substring(op[i].lastIndexOf(".")+1));//日
				  selected.add(aday);
				}
		
		        	mc.setSelectedLists(selected);//年月日

   					response.sendRedirect("adminSelectDate.jsp");
   					return;
   				}
   			}
   		}
	 %>

     <form name="form1" method="post" action="adminStepFirst.jsp" onsubmit="return check()">
	 <div id="biaodan1">
	   <%if(poll.getPollType()==1){
	   %>
  <p><span class="special">修改内容(共两步):</span><span>修改标题和说明</span></p>
  <% } else if(poll.getPollType()==2){ 
  %> 
  <p><span class="special">修改时间(共三步):</span><span>修改标题和说明</span></p>
  <%
   }
   %>
    
  <p>标题:<span class="warning" id="titlewarning"></span><br />
    <input type="text" size="62" name="title" value="<%=poll.getTitle()%>" onblur="checkTitle(this)" />
  </p>
  <p>说明(可选项):<span class="warning" id="titlewarning"></span><br /> 
    <textarea name="descrip" cols="60" rows="10" onblur="checkDesc(this)"><%=poll.getDescription()%></textarea>
  </p>
  <p>您的姓名或昵称:<span class="warning" id="titlewarning"></span><br />
    <input type="text"  size="62" name="initialname" value="<%=poll.getInitialName()%>" onblur="checkName(this)" />
  </p>
  <p>您的电子邮箱(可选项):<span class="warning" id="titlewarning"></span><br />
    <input type="text"  size="62" name="email" value= "<%=poll.getEmail()%>" onblur ="checkEmail(this)" />
  </p>
  </div>
    <input type="hidden" name="action" value="add"/>
  <input type="submit" name="submit" value="下一步>>" class="special1">
        <input type="submit" name="submit" value="取消>>" class="special1">
		</form>
		</div>
</div>
</div>
<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>