<%@ page language="java" import="java.util.*,polls.*,calendars.*" pageEncoding="GBK"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>��������������ѡ��</title>
    
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

.special6{
width:100px;

}
.special7{
width:100px;
text-align:center;
}
th.special7{
background-color:#3399FF;
}
th.special6{
background-color:#3399FF;
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
p.special2{
font-size:16px;
font-weight:bold;
color:#000000;
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
    <li><a href="../index.jsp">������ҳ</a></li>
    <li><a href="../index.jsp?type=1&timeout=new">��������</a></li>
    <li><a href="../index.jsp?type=2&timeout=new">����ʱ��</a></li>
    <li class="help"><a href="help.htm">��������</a></li>
  </ul>
</div>

<div id="main">
<div id="links">
 
  <p class="special2">ȷ��Ҫɾ����ѡ�Ĳμ���Ա��</p>
 <p> ����Ϊ��ѡ��Ľ�Ҫ��ɾ���ļ�¼:</p>
  </div>
<div id="content">
<%
	request.setCharacterEncoding("GBK");
	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	PollPartiBean pollParti = (PollPartiBean)session.getAttribute("pollParti");
	PollAdminMgr mgr = PollAdminMgr.getInstance();
	List<Option> options = poll.getOptions();
	int optionNum = options.size();
	String action = request.getParameter("action");
	if(action!=null && action.equals("submit")){
	
		String submitType = request.getParameter("demember");
	    if(submitType.equals("ɾ��")){
			int[][] delSel=(int[][]) session.getAttribute("MemberDelSel");

			mgr.deleteMembers(delSel,poll.getPollId());
			response.sendRedirect("adminEditMembers.jsp");
			return;
		 }
 
 		if(submitType.equals("ȡ��")){
			response.sendRedirect("adminEditMembers.jsp");
			return;
 		}
	
	}
 %>

<form action="adminDeleteMembers.jsp" method="post">
<input type="hidden" name="action" value="submit"/>
<table class="table1">
	
<%  
			
			if(poll.getPollType()==1){
					%>
				<tr>
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
					    timeoption[i] = options.get(i).getOptionContent().trim().substring(options.get(i).getOptionContent().trim().lastIndexOf("time")+4);//T֮��ĵ���λ
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
					monthSize.add("" + ss);//���������м���ѡ�ÿλ�ϵ����ִ���ĳʱ���м����ظ���ѡ��
					groupSize.add("" + dss);
					%>
					<tr>
					<th class="special7">&nbsp;</th>
					<% 
						for(int i=0; i<monthGroup.size(); i++){
						String mmm = ((String)monthGroup.get(i)).substring(0,4) + "��" + ((String)monthGroup.get(i)).substring(5) + "��";							
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
					<td class="special7">&nbsp;</td>
					<% 
					for(int i=0; i<dayGroup.size(); i++){
					    String ddd = (String)dayGroup.get(i);
					    byear = Integer.parseInt(ddd.substring(0,4));
						bmonth = Integer.parseInt(ddd.substring(5,ddd.lastIndexOf(".")));
						bday = Integer.parseInt(ddd.substring(ddd.lastIndexOf(".")+1));
						calendar.set(byear,bmonth-1,bday);//Calendar�����·��Ǵ�0��11
						weekday = calendar.get(Calendar.DAY_OF_WEEK);
						switch(weekday)
						{ case 1:
							wd="������";
							break;
						  case 2:
							wd="����һ";
							break;
						  case 3:
						  	wd="���ڶ�";
							break;
						  case 4:
						  	wd="������";
							break;
						  case 5:
						  	wd="������";
							break;
						  case 6:
						  	wd="������";
							break;			
						  case 7:
						  	wd="������";
							break;					
							}
						%>
							<td colspan="<%=(String)groupSize.get(i) %>">
						<%=bday %>��&nbsp;<%=wd %>
						</td>
						<%																
						}
						%>
						</tr>
						<% 
						if(needtime){
						%>
						<tr>
							<td class="sepcial7">&nbsp;</td>
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
				   int[][] delSel=(int[][]) session.getAttribute("MemberDelSel");
				   int[] pollSize = mgr.getPollSize(poll.getPollId());
				   int[] memberIdList=new int[pollSize[1]];
				   
			   if(pollSize[1]>0){	
				    String[][] memberSet = mgr.getMembersAdmin(pollSize,poll.getPollId());
					int[][] pollResult = pollParti.getPollResult();
											
					for (int i=0; i<memberSet.length;i++) {
							memberIdList[i]=Integer.parseInt(memberSet[i][0]);
						if(delSel[i][1]==1){
							out.println("<tr>");
							out.println("<td class=special7>"+memberSet[i][1]+" </td>");
						
						for(int j=0;j<pollResult[0].length; j++){
							if(pollResult[i][j]==1){
								out.println("<td class=specialtd1>OK</td>");
								
							}
							if(pollResult[i][j]==0){
								out.println("<td class=specialtd3 ></td>");
							}
							if(pollResult[i][j]==2){
								out.println("<td class=specialtd2>?</td>");
							}
						}
						out.println("</tr>");
					} 
					}
					}
				session.setAttribute("MemberIDList",memberIdList);//��������ID	
							
   			%> 

</table>
<br />
 <input class="special1" name="demember" type="submit" value="ɾ��" > 
	 <input  class="special1" name="demember" type="submit" value="ȡ��" >
	  </form> 
		

</div>
</div>



<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
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