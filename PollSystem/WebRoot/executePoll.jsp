<%@ page language="java" import="java.util.*,polls.*,java.sql.Timestamp,calendars.*" pageEncoding="GBK"%>
<%@page import="java.text.SimpleDateFormat"%>

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
<script language="javascript" src="script/validate.js"></script>

  </head>
<style>
<!--

#links{
width:100%;
color: #484848;
padding-left:30px;

}
#content{
width:100%;
color: #484848;
text-align:center;
padding:0px;
}

span.special1{
font-size:22px;
font-weight:bold;
padding-top:10px;
color:#000000;

}
span.special2{
background-color:#D7EBFF;
padding:10px;
}
span.special3{
color:#484848;
}
p.special4{
margin-top:20px;
}
p.special4 span{
color:#3399FF;
}
p.special5{
margin-left:-10px;
}
table.table1{
border:1px solid #005984;
margin:0px auto 0px auto;
text-align:center;
color:#000000;
border-collapse: collapse;
}
table.table1 caption{
font-size:14px;
color:#000000;
}
table.table1 caption span{
color:#3399FF;
}
table.table1 td{
border:1px solid #005984;
text-align:center;


}
table.table1 th{
border:1px solid #005984;
text-align:center;

}
#remarks{
width:840px;
color: #484848;
background-color: #D7EBFF;
margin-left:20px;
padding-left:10px;
padding-top:10px;
padding-bottom:10px;
}
div.warning{
color:#3399FF;
font-size:12px;

margin-bottom:10px;
}
div.warning a{

text-decoration:none;
}
div.warning a:link,div.warning a:visited{
color:#484848;
}
div.warning a:hover{
text-decoration:line-through;
color:#FF850B;
}
div.warning1{
font-size:12px;
padding:0px;
margin:0px;
}
#warning2{
padding-top:0px;
border:1px solid;
margin:0px;

}

th.special6{
width:100px;
background-color:#3399FF;
}
input.special7{
border:0px;
width:100px;
text-align:center;
}
span.special8{
padding-left:20px;
}
#remarks1{
padding-left:770px;
}
#remarks1 a{
text-decoration:none;

}
#remarks1 a:link,#remarks1 a:visited{
color: #3399FF;
}
#remarks1 a:hover{
color: #FF850B;
text-decoration:underline;
}
td.special11{
font-weight: bold;
}

 input.special0{
 border:0px;
color: #3399FF;
background-color:transparent;
}

form{

margin:0px;
}
table.table1 td.specialtd1{
background-color: #3CFFFF;
font-weight:bold;
}
table.table1 td.specialtd2{
background-color: #CCCCCC;
font-weight:bold;
}
table.table1 td.specialtd3{
background-color:#FF6262;
}
table.table1 tr.altrow{
background-color: #4242FF;
}
.special01{
padding:0px 10px;
background-color:#3399FF;
}
table.table2{

margin:0px auto;

}
table.table2 td.dingwei{
text-align:right;

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
<%
	request.setCharacterEncoding("GBK");
	String pollId = request.getParameter("pollId");
	if(pollId != null && !(pollId.trim().equals(""))){
		pollId = pollId.trim();
	}else{
		response.sendRedirect("index.jsp");
	}
	
	PollMgr mgr = PollMgr.getInstance();
	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = null;
	if(poll == null){
		poll = mgr.getPollById(pollId);
		session.setAttribute("poll",poll);
	}
	if(poll.getPollCreateTime() != null){
		pollParti = (PollPartiBean)session.getAttribute("pollParti");
		if(pollParti == null){
			pollParti = mgr.getPollPartiById(pollId);
			session.setAttribute("pollParti",pollParti);
		}
	}else{
		pollParti = new PollPartiBean();
	}
	
	if(poll.isDeleted()){
		response.sendRedirect("pollDeleted.jsp");
		return;
	}
	List<Option> options = poll.getOptions();	
	int optionNum = options.size();
	List<PollMember> pollMembers = pollParti.getMembers();
	
	String action = request.getParameter("action");
		if(action!=null && action.equals("vote")){
			boolean repeated = false;
			boolean noChoose = false;

			for(int i=0; i < pollMembers.size(); i++){
				if((request.getParameter("parName").trim()).equals(pollMembers.get(i).getMemberName().trim())){
					repeated = true;
					break;
				}
			}
	 		int[] choose = new int[optionNum]; 
		
			for(int i=0; i<optionNum; i++){
				if(request.getParameter("Choose"+(i+1))!=null){
					choose[i] = 1;
				}else{
					choose[i] = 0;
				}
			}
	  		int k = 0;
			for(int j=0;j<optionNum;j++){
				if(choose[j]==0){
					k = k+1;
				}
			}
			if(k == optionNum){ 
				noChoose=true;
			}else{
				pollParti.setChoose(choose);
			}

			if(request.getParameter("parName").equals("") || request.getParameter("parName").equals("您的姓名") || repeated || request.getParameter("parName").length()>10){
		   		if(repeated){
		   			session.setAttribute("pollStatus","nameRepeated");
				}
				if(request.getParameter("parName").equals("") || request.getParameter("parName").equals("您的姓名")){ 
					session.setAttribute("pollStatus","noName");
				}
				if(request.getParameter("parName").length()>10){
					session.setAttribute("pollStatus","nameLong");
				}
				response.sendRedirect("executePoll.jsp?pollId=" + pollId);
				return;
			}else{ 
				if(noChoose){
					session.setAttribute("pollStatus","nochoose");
					response.sendRedirect("executePoll.jsp?pollId=" + pollId);
					return;
				}else{
		 			mgr.inputVote(pollParti,request.getParameter("parName"),options);
					session.setAttribute("pollStatus","succeed");
					session.removeAttribute("pollParti");
					PollPartiBean newPollParti = mgr.getPollPartiById(pollId);
					session.setAttribute("pollParti",newPollParti);
					response.sendRedirect("executePoll.jsp?pollId=" + pollId);
					return;
						
 				}
			}
		}
	
 %>
	<br />
		<div class="warning" id="warning">
		<% 
		
			if( session.getAttribute("pollStatus")!=null){
				if(((String) session.getAttribute("pollStatus")).equals("noName")){
					out.println("您的投票没有成功提交,因为您没输入姓名,请输入姓名和做出选择后重新提交。");
				}
				if(((String) session.getAttribute("pollStatus")).equals("succeed")){
					out.println("您的投票已经成功提交。");
				}
				if(((String) session.getAttribute("pollStatus")).equals("nameRepeated")){
					out.println("您的投票没有成功提交,因为您输入的投票名字已经投过票,如果您还没有投票,请选用一个没在此投票中使用过的名字。");
				}
				if(((String) session.getAttribute("pollStatus")).equals("nameLong")){
					out.println("您的投票没有成功提交,因为您输入的投票名字太长了。您输入的名字的长度不能超过10个字");
				}
				if(((String) session.getAttribute("pollStatus")).equals("nochoose")){
					out.println("您还没有做出选择,如果没有您支持的选项请到右下方的<a href=comment.jsp?pollID="+ pollId +">评论处</a>进行评论！");
				}
			}
			
	%>
		</div>
		<%
			Timestamp updateTime = poll.getUpdateTime();
			if(updateTime != null){
			%>
			<div class="warning1">（此投票内容在<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(updateTime) %>被重新编辑过,一些信息可能已不准确。）</div>
			<% 
			}
		%>
		

<span class="special1">您好，欢迎来到咕咕投票页面 </span>
<p class="special5"><span class="special2">摘要：<span class="special3"> <%=pollParti.getMembers().size() %>个参加者，
		<%=pollParti.getCommentNum() %>条评论，
		<% if(poll.getLastVoteTime() == null){
			 {out.print( "还没人参加投票");}
		}else{ 
			%>
			<%=new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(poll.getLastVoteTime()) %>有人投票
			<%
		}
		%></span></span></p>
  		<p class="special4">说明：<span>
  		<%  
  			String desc = poll.getDescription();
            if(!desc.trim().equals("")){ 
                desc = desc.replaceAll("\n","<br>");
            %>
            <%=desc %>
            <%
            }else{
            	out.print("暂无");
            } 
            %></span></p>

</div>
<div id="content">
<form name = "form1" method = "post" action = "executePoll.jsp" onsubmit="return checkwarning()" >
<input name="action" value="vote" type="hidden"/>
<input name="pollId" value="<%=pollId %>" type="hidden"/>

<table class="table2">

	<tr><td>
  	<table class="table1">
   <caption>
   	当前主题：<span><%=poll.getTitle() %></span>（
   	发起者：<span><%=poll.getInitialName() %>）</span>
   </caption>
    		<% 	
				
				if(poll.getPollType() ==1 ){
					%>
					<tr>
						<th class="special6">&nbsp;</th>
					<%
					for (int i=0; i<optionNum;i++) {
					%>
						<th class="special6"><%=options.get(i).getOptionContent() %></th>
					<% 
					}
					%>
					</tr>
					<%
				}
				if(poll.getPollType() == 2){
					
					List monthGroup= new LinkedList();
				    List monthSize= new LinkedList();
					
					List dayGroup= new LinkedList();
				    List groupSize= new LinkedList();
				
					Calendar calendar = CalendarMgr.getInstance().getNewCalendar();			
					String option1 = options.get(0).getOptionContent();
					
					String month = option1.trim().substring(0,option1.trim().lastIndexOf("."));
					monthGroup.add(month);
					
					String day = option1.trim().substring(0,option1.trim().indexOf("time"));
					dayGroup.add(day);
					int ss = 0, dss = 0;
					
					boolean needtime = false;
					String[] timeoption = new String[optionNum];
					
					for (int i=0; i<optionNum;i++) {
					    timeoption[i] = options.get(i).getOptionContent().trim().substring(options.get(i).getOptionContent().trim().lastIndexOf("time")+4);//T之后的第四位
						if(timeoption[i].equals("")){
							timeoption[i] = "-";
						}else{
							needtime = true;
						}
						
						String amonth = options.get(i).getOptionContent().trim().substring(0,options.get(i).getOptionContent().trim().lastIndexOf("."));
					
						if(month.equals(amonth)){
							ss ++;
						}else{
							monthSize.add(""+ss);
							monthGroup.add(amonth);
							month = amonth;
							ss=1;
						}
						
						String aday = options.get(i).getOptionContent().trim().substring(0,options.get(i).getOptionContent().trim().indexOf("time"));
						if(day.equals(aday)){
							dss++;
						}else{
							groupSize.add(""+dss);
							dayGroup.add(aday);
							day = aday;
							dss = 1;
						}
						
					}
					monthSize.add("" + ss);//个数代表有几个选项，每位上的数字代表某时间有几个重复的选项
					groupSize.add("" + dss);
					%>
					<tr>
					<th class="special01">&nbsp;</th>
					<% 
						for(int i=0; i<monthGroup.size(); i++){
						String mmm = ((String)monthGroup.get(i)).substring(0,4) + "年" + ((String)monthGroup.get(i)).substring(5) + "月";							
					%>
						<th class = "special01" colspan = "<%=(String)monthSize.get(i) %>"><%=mmm %></th>
					<%		
					}
					%>
					</tr>
					<% 
					int byear, bmonth, bday;
					int weekday;
					String wd = "";
					%>
					<tr>
					<td >&nbsp;</td>
					<% 
					for(int i=0; i<dayGroup.size(); i++){
					    String ddd = (String)dayGroup.get(i);
					    byear = Integer.parseInt(ddd.substring(0,4));
						bmonth = Integer.parseInt(ddd.substring(5,ddd.lastIndexOf(".")));
						bday = Integer.parseInt(ddd.substring(ddd.lastIndexOf(".")+1));
						calendar.set(byear,bmonth-1,bday);//Calendar类中月份是从0到11
						weekday = calendar.get(Calendar.DAY_OF_WEEK);
						switch(weekday)
						{ case 1:
							wd="星期日";
							break;
						  case 2:
							wd="星期一";
							break;
						  case 3:
						  	wd="星期二";
							break;
						  case 4:
						  	wd="星期三";
							break;
						  case 5:
						  	wd="星期四";
							break;
						  case 6:
						  	wd="星期五";
							break;			
						  case 7:
						  	wd="星期六";
							break;					
							}
						%>
						<td colspan="<%=(String)groupSize.get(i) %>">
						<%=bday %>号&nbsp;<%=wd %>
						</td>
						<%																
						}
						%>
						</tr>
						<% 
						if(needtime){
						%>
						<tr>
							<td >&nbsp;</td>
						<%	
							for (int i=0; i<optionNum;i++) {
						%>
						<td><%=timeoption[i] %></td>
						<% 	  
						}
						%>
						</tr>
						<% 
						}	
					}				          
					%> 
					<% 
					  	int memberNum = 0;
					 	if(pollMembers.size()>0){	//统计的是投票的人数
							memberNum = pollMembers.size();
							int[][] pollResult = pollParti.getPollResult();
							int[] sumOption = new int[pollResult[0].length];		
							for (int i=0; i<pollMembers.size();i++) {
							%>
							<tr>
							<th><%=pollMembers.get(i).getMemberName() %></th>
							<% 
								for(int j=0;j<pollResult[0].length; j++){
									if(pollResult[i][j] == 1){
								%>
									<td class="specialtd1">OK</td>
								<% 
									sumOption[j] = sumOption[j]+1;
									}
									if(pollResult[i][j] == 0){
								%>
									<td class="specialtd3"></td>	
								<% 
									}
									if(pollResult[i][j] == 2){
								%>
									<td class="specialtd2">?</td>
								<% 
									}
								}
								%>
								</tr>
								<%
								}
								%>
								<tr>
								<td>小计</td>
					<% 
							int bestNum = 0;
							
							for(int i=0; i<sumOption.length; i++){
						%>
						<td class="special11"><%=sumOption[i] %></td>
						<% 	
							if(sumOption[i] > sumOption[bestNum]){
								bestNum = i;
							}
						}
						%>
						</tr>
						<%
						  pollParti.setBestOption(options.get(bestNum).getOptionContent());
						   if((bestNum+1) < sumOption.length){	
							for(int i=bestNum+1; i<sumOption.length; i++){
								if(sumOption[i]==sumOption[bestNum]){
									pollParti.setBestOption(pollParti.getBestOption() + "和" + options.get(i).getOptionContent());
								}
							}
						}	
						
					}
					%>     				   
				      <th><input type="text"  name="parName" value= "您的姓名" onFocus="this.value=(this.value=='您的姓名') ? '' : this.value;" onBlur="this.value=(this.value=='') ? '您的姓名' : this.value;" class="special7"></th>
					    <%
					    if(pollParti.getChoose() != null && pollParti.getChoose().length == optionNum) {
						   int[] choose= pollParti.getChoose();
						  
						    for (int i=0; i<optionNum;i++) {
								 if(choose[i]==1){
								%>
								<td ><input name="Choose<%=i+1 %>" type="checkbox" checked = "true"></td>
								<% 
								 }else{
								 
								 %>
								 <td ><input name="Choose<%=i+1 %>" type="checkbox" ></td>
								 <%
								 }
							}
						
						}else{
		   
							for (int i=0; i<optionNum;i++) {
							%>
								<td ><input name="Choose<%=i+1 %>" type="checkbox" ></td>
							<% 
							}
						}			
	          %>     	       
  </table></td></tr>
  
  <tr ><td class="dingwei">
  <input class="special0" name="Submit" type="submit" value="提交投票>>">
   
   </td></tr>
   
   </table>
   </form>
  
</div>
<div id="remarks">
  <span>主题创建时间:<span style="color:#484848;"><%= new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(poll.getPollCreateTime()) %></span></span><span class="special8">得票最多选项：<span style="color:#484848;"><%
					if(poll.getPollType()==2){
						pollParti.setBestOption(pollParti.getBestOption().replaceAll("time","号"));
					}
				%>
				<%=pollParti.getBestOption() %>
				</span></span><br />
 
  
  </div>
   <div  id="remarks1">
   		【<a href="comment.jsp?pollId=<%=pollId %>">查看及发表评论</a>】
   </div>
</div>
<div id="footer"> <a href="index.jsp">主页</a> &nbsp;·&nbsp;<a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
<script language="javascript">
var rows=document.getElementsByTagName("tr");
for(var i=0;i<rows.length;i++)
{
rows[i].onmouseover=function(){
this.className+="altrow";
}
rows[i].onmouseout=function(){
this.className=this.className.replace("altrow","");
}
}



</script>
</body>
</html>