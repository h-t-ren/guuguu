<%@ page language="java" import="java.util.*,polls.*,calendars.*" pageEncoding="GBK"%>


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

#links{
width:670px;
color: #484848;
border-top:1px dotted #000000;
margin:0px;
padding:20px 0px 0px 20px;

}
#content{
margin:0px;
padding-left:10px;
text-align: center;
}

#content table.table1{
border:1px solid #005984;
border-collapse:collapse;
text-align:center;
margin:0px auto;
}

table.table1 th{
border:1px solid #005984;
text-align:center;
}
table.table1 td{
border:1px solid #005984;
text-align:center;
}
input.special1{
border:0px;
color:#3399FF;
background-color:transparent;
}

th.special6{
width:100px;
text-align:center;
background-color:#3399FF;
}
.special7{
width:100px;
text-align:center;

}
th.special7{
background-color:#3399FF;
}
.special8{
width:20px;
text-align:center;
}
th.special8{
background-color:#3399FF;
}
table.table1 td.specialtd1{
background-color: #3CFFFF;
font-weight:bold;
}
table.table1 td.specialtd2{
background-color:  #CCCCCC;
font-weight:bold;
}
table.table1 td.specialtd3{
background-color:#FF6262;
}
table.table1 tr.altrow{
background-color: #4242FF;
}

p.special9{
font-size:16px;
font-weight:bold;
color:#000000;
}
p span{
color:#3399FF;
}
.special01{
padding:0px 10px;
background-color:#3399FF;
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
    <li><a href="../index.jsp?type=2&timeout=new">安排时间</a></li>
    <li class="help"><a href="help.htm">咕咕帮助</a></li>
  </ul>
</div>

<div id="main">
<div id="links">
<%	request.setCharacterEncoding("GBK");
	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	PollAdminMgr mgr = PollAdminMgr.getInstance();
	String action = request.getParameter("action");
	if(action!=null && action.equals("submit")){
		String submitType = request.getParameter("editmember");
		
		 if(submitType.equals("全选")){
			boolean selectAllMember = true;
			session.setAttribute("selectAllMember",selectAllMember);
			response.sendRedirect("adminEditMembers.jsp");
			return;
		 }
 
		  if(submitType.equals("取消全选")){
			boolean selectAllMember = false;
			session.setAttribute("selectAllMember",selectAllMember);
			response.sendRedirect("adminEditMembers.jsp");
			return;
		 }
 
		 if(submitType.equals("删除所选")){
			
			int[] IDList = (int[]) session.getAttribute("MemberIDList");
			int[][] delSel = new int[IDList.length][2];
			
			for(int i=0; i<IDList.length; i++){
				delSel[i][0] = IDList[i];//存入MemberID
				if(request.getParameter(Integer.toString(IDList[i]))!=null){//只要进行了选择 VALUE就不为空
			 	  delSel[i][1]=1;
				}else{
					delSel[i][1]=0;
				}
				//out.println(delSel[i][0]+"delete"+delSel[i][1]);
			} 
		    session.setAttribute("MemberDelSel",delSel);	 
			response.sendRedirect("adminDeleteMembers.jsp");
			return;
		 }
		
	}
 %>
  <p class="special9">您好，这里是查看和编辑参与者界面</p>
  <p>当前主题：<span><%=poll.getTitle() %></span>（发起者：<span><%=poll.getInitialName() %>）</span></p>
  <p>说明:<span><%  
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


<form action="adminEditMembers.jsp" method="post">
<input type="hidden" name="action" value="submit"/>
<table class="table1">
	
<%  	List<Option> options = poll.getOptions();
		int optionNum = options.size();
			if(poll.getPollType()==1){
				%>
				<tr>
				<th class="special8">&nbsp;</th>
				<th  class="special7">&nbsp;</th>
				<% 
					for (int i=0; i<options.size();i++) {
					%>
					<th class="special6"><%=options.get(i).getOptionContent() %> </th>
					<% 
					}
				%>
					</tr>
				<%	
			}
		if(poll.getPollType()==2){
					
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
					<th class="special8">&nbsp;</th>
					<th class="special7">&nbsp;</th>
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
					String wd="";
					%>
					<tr>
					<td class="special8">&nbsp;</td>
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
							<td>&nbsp;</td>
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
			   boolean selectAllMember=false;
				if (session.getAttribute("selectAllMember")!=null){
					selectAllMember=(Boolean)session.getAttribute("selectAllMember");
				}else {	
					session.setAttribute("selectAllMember", selectAllMember);//一开始是false
				}
			   
			   int[] pollSize = mgr.getPollSize(poll.getPollId());
			   int[] memberIdList = new int[pollSize[1]];//取得成员的个数
			   
			   if(pollSize[1]>0){	
				    String[][] memberSet = mgr.getMembersAdmin(pollSize,poll.getPollId());//取得成员的编号和名字
					List<PollMember> members = pollParti.getMembers();
					int[][] pollResult = pollParti.getPollResult();
					
											
					for (int i=0; i<memberSet.length;i++) {
					%>
					<tr>
					<%
						try{
							memberIdList[i] = Integer.parseInt(memberSet[i][0]);
						}catch(NumberFormatException e){
							return;
						}
						if(selectAllMember){
							out.println("<th><input name=" +memberSet[i][0]+" type=checkbox checked = true></th>");
						}else{
							out.println("<th><input name=" +memberSet[i][0]+" type=checkbox ></th>");
						}
						out.println("<td class=special7>"+memberSet[i][1]+" </td>");//名字
						for(int j=0;j<pollResult[0].length; j++){
							if(pollResult[i][j]==1){
								out.println("<td class=specialtd1>OK</td>");
								
							}
							if(pollResult[i][j]==0){
								out.println("<td class=specialtd3></td>");
							}
							if(pollResult[i][j]==2){
								out.println("<td class=specialtd2>?</td>");
							}
						}
						%>
						</tr>
						<%
					} 
					}
				session.setAttribute("MemberIDList",memberIdList);//存入所有ID	
							
   			%> 

</table>
<br />
	<input class="special1"  name="editmember" type="submit" value = "全选" >
	<input  class="special1" name="editmember" type="submit" value = "取消全选" >
	<input  class="special1" name="editmember" type="submit" value = "删除所选" >

			</form>

</div>
</div>



<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
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