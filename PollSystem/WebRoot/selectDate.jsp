<%@ page language="java" import="java.util.*,calendars.*,polls.*" pageEncoding="GBK"%>


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

#content{
margin:0px;
padding:0px;
float:left;
width:500px;
border-top:1px dotted #000000;
}
#biaodan{
margin:0px;
padding:10px 0px 0px 51px;
}
#riqi{
margin:0px;
padding:0px;
float:left;
width:200px;
border-top:1px dotted #000000;

}
#riqi1{
margin:80px 40px 0px 0px;
background-color:#D7EBFF;
color:#484848;
padding:10px 15px 10px 25px;
}
span.special{
font-size:22px;
font-weight:bold;
color:#000000;
}
input.special1{
padding:0px 6px 0px 0px;
border:none;
color:#3399FF;
background-color:transparent;
}
span.special5 a{
text-decoration:none;
margin:0px;
color: #ffffff;
padding:4px 10px 4px 10px;
font-size:10px;

}
span.special5 a:link,span.special5 a:visited{
background-color:#8CC6FF;
border-bottom: #004B97 1px solid;
border-right: #004B97 1px solid;
border-top: #eeeeee 1px solid;
border-left: #eeeeee 1px solid;

}
span.special5 a:hover{
padding:5px 8px 3px 12px;
background-color:#2894FF;
color:#000000;
border-left:  #004B97 1px solid;
border-top: #004B97 1px solid;
border-bottom: #eeeeee 1px solid;
border-right: #eeeeee 1px solid;
}
 span.warning{
color:#3399FF;
font-size:12px;
}
span.special3{
margin:0px;
padding:0px 76px 0px 76px;
}
span.special4{
font-weight:bold;
font-size:12px;
padding:0px 4px 0px 4px;
}
span.special5{
padding:10px 10px 10px 10px;
margin:0px;
background-color:#D7EBFF;
}
span.special6{
font-weight:bold;
font-size:12px;
padding:0px 4px 0px 10px;
}
table.table1{
border:0px;
margin:0px;
padding:0px;
}
table.table1 td{
border:0px;
margin:0px;
padding:0px;
}
td input.special7{
width:43px;
height:43px;
margin:0px;
color:#484848;
font-weight:bold;
}
td input.special8{
width:43px;
height:43px;
color:#484848;
font-weight:bold;
background-color: #B0FFB0;
}
td input.special9{
color:#484848;
width:43px;
height:43px;
font-weight:bold;
background-color: #CCCCCC;
}
#biaodan1{
margin:0px;
padding:0px 0px 0px 9px;
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
    <li><a href="index.jsp">咕咕首页</a></li>
    <li><a href="index.jsp?type=1&timeout=new">安排内容</a></li>
    <li><a href="index.jsp?type=2&timeout=new">安排时间</a></li>
    <li class="help"><a href="help.htm">咕咕帮助</a></li>
  </ul>
</div>

<div id="main">
<div id="content">
<div id="biaodan">
<%
	request.setCharacterEncoding("GBK");
	MyCalendar mc = (MyCalendar)session.getAttribute("MyCalendar");
	CalendarMgr mgr = CalendarMgr.getInstance();
	
	if(mc == null){
		mc = mgr.getMyCalendar();
		session.setAttribute("MyCalendar",mc);
	}
	
	Calendar calendar = mc.getCalendar();
	List selectedLists = mc.getSelectedLists();
	
	String action = request.getParameter("action");
	if(action != null && action.equals("monthdown")){
		calendar.add(Calendar.MONTH,-1);
	}else if(action != null && action.equals("monthup")){
		calendar.add(Calendar.MONTH,1);
	}else if(action != null && action.equals("selected")){
		
		int[] day = new int[3];
		day[0] = calendar.get(Calendar.YEAR);
		day[1] = calendar.get(Calendar.MONTH);
		day[2] = Integer.parseInt(request.getParameter("submit"));
		boolean ex = false;
  		for(int i=0; i<selectedLists.size(); i++){
			int[] aday=new int[3];
			aday=(int[]) selectedLists.get(i);
	 		if(aday[0]==day[0] && aday[1]==day[1] && aday[2]==day[2]){
	 			ex=true;
				selectedLists.remove(i);
				break;
		 }
  		}
		  
		 if(!ex){
		 	selectedLists.add(day);
		 }
	}else if(action != null && action.equals("verify")){
		String submitType = request.getParameter("submit");
		int[][] da = (int[][])session.getAttribute("dayarray");
		if (submitType.equals("下一步>>")){
			if(da == null || da.length == 0){
				session.setAttribute("daywarning", "您还没选择日期!");
			}else{
				 if(session.getAttribute("daywarning")!=null)
				 {session.removeAttribute("daywarning");}
				 response.sendRedirect("setTimeDetail.jsp");
				 return;
			}
		}
		if(submitType.equals("<<上一步")){
			response.sendRedirect("stepFirst.jsp");
			return;
		}
		if(submitType.equals("取消")){
			session.invalidate();
			response.sendRedirect("index.jsp");
 			return;
		}
	}
 %>

 <form name="calendarForm" method="post" action="selectDate.jsp">
 <input type="hidden" name="action" value="selected"/>
 <div id="biaodan1">

	   
	  <% if (session.getAttribute("daywarning")!=null){
			String warning = (String)session.getAttribute("daywarning");
		%>
		<span class="warning"><%=warning %></span><br/>
		<%
		}
		%>
<span class="special">选择时间（共三步）：</span>设定参加者将能选择的时间方案
<p style="color:#484848;">按选您选择的日期，再点击一次，就可将它取消</p><br />

<span class="special5"><a href="selectDate.jsp?action=monthdown">&lt;&lt;</a>
		<span class="special3">
		<%=calendar.get(Calendar.YEAR) %>年&nbsp;<%=calendar.get(Calendar.MONTH)+1 %>月
		</span>
		<a href="selectDate.jsp?action=monthup">&gt;&gt;</a></span>
<p><span class="special4">星期一</span><span class="special4">星期二</span><span class="special4">星期三</span><span class="special4">星期四</span><span class="special4">星期五</span><span class="special6">星期六</span><span class="special4">星期日</span> </p><table class="table1">
 <%
					Calendar newCalendar = mgr.getNewCalendar();//获得一个时间的实例
					
					mgr.set(newCalendar,calendar);				
					
					int weekday = mgr.getWeekday(newCalendar);
					
					
					int[] nows = new int[3];
					nows = mgr.initNows();
					
					boolean expired = false;
					
					if(calendar.get(Calendar.YEAR)<nows[0] || (calendar.get(Calendar.YEAR) == nows[0] && calendar.get(Calendar.MONTH)< nows[1] )){
					   expired=true;
					}
														
					int lastday=calendar.getActualMaximum(Calendar.DAY_OF_MONTH);//获得某个月的最大天数
					
					List alldays = mc.getSelectedLists();
					
					boolean[] se = new boolean[lastday];
					
					for(int i=0; i<se.length; i++){
						se[i] = false;//全部置F
					}
					
					for(int i=0; i < alldays.size(); i++){
						int[] aday = new int[3];
						 aday = (int[])alldays.get(i);
						if(calendar.get(Calendar.YEAR) == aday[0] && calendar.get(Calendar.MONTH) == aday[1]){
							se[aday[2]-1] = true;
						} 
					}
					%>
					<tr>
					<%
					for(int i=1; i<weekday; i++){
					%>
						<td>&nbsp;</td>
					<% 
					  if(i%5 == 0){
						  %>
						  <td>&nbsp;</td>
						  <% 				
						}
					}	
					for(int i=weekday; i<weekday+lastday; i++){
					 
					  if(expired || (calendar.get(Calendar.YEAR) == nows[0] && calendar.get(Calendar.MONTH) == nows[1] && (i-weekday+1) < nows[2])){
					  %>
					  <td><input name="submit" type="submit" value="<%=(i-weekday+1) %>" disabled="disabled" class="special7" /></td>
					  <%              
				 	  }else {
						  if(se[i-weekday]){
					  %>
					  <td><input name="submit" type="submit" value="<%=(i-weekday+1) %>" class="special8" /></td>
					  <% 
					  }else {
					  %>
					  <td><input name="submit" type="submit" value="<%=(i-weekday+1) %>" class="special9" /></td>
					  <% 
						  }
					  }	
					  if(i%7==5){
					 %>
					 	<td>&nbsp;</td>
					 <%
					  }
					    if(i%7==0){
					  	%>
					  	</tr>
					  	<tr>
					  	<% 
					  }	
					 }
					 
					%>
					</tr>
					</table>
</div> </form><p></p>
 <form name="controlform" method="post" action="selectDate.jsp">
 <input type="hidden" name="action" value="verify" />
		  		<input type="submit" name="submit" value="<<上一步" class="special1" /> 
		  		<input type="submit" name="submit" value="下一步>>" class="special1" /> 
                <input type="submit" name="submit" value="取消" class="special1" />	
	  </form>
</div>
</div>
<div id="riqi">
<div id="riqi1">选择的日期:<br /><br />
       <%
      if(session.getAttribute("dayarray")!=null){
		
					int[][] dayarray1=(int[][]) session.getAttribute("dayarray");
					int panduan1 = dayarray1.length;
					int panduan2 = selectedLists.size();
					
					int[][] dayarray = new int[panduan2][3];
					
				    for(int i=0; i<selectedLists.size(); i++){
						int[] aday = new int[3];
						 aday = (int[]) selectedLists.get(i);
						dayarray[i] = aday;
					}
					
					Calendar a =  mgr.getNewCalendar();
					Calendar b =  mgr.getNewCalendar();
					
					int[] mid = new int[3];
					
					for(int i=0; i<dayarray.length; i++){
						a.set(dayarray[i][0],dayarray[i][1],dayarray[i][2]);
						for(int j=i+1; j<dayarray.length;j++){
							b.set(dayarray[j][0],dayarray[j][1],dayarray[j][2]);
							if(b.before(a)){//Calendar的比较时间方法
								mid=dayarray[i];
								dayarray[i]=dayarray[j];
								a.set(dayarray[j][0],dayarray[j][1],dayarray[j][2]);
								dayarray[j]=mid;
								//a=b;用到冒泡法
							} 
						}
							
					}
					
					for(int i=0; i<dayarray.length; i++){
						out.println(dayarray[i][0]+"-"+(dayarray[i][1]+1)+"-"+dayarray[i][2] + "<br/>");
					}
					if(panduan1>=panduan2){
						session.setAttribute("dayarray",dayarray);
					}else{ 
						
			              int num = ((PollInformationBean)session.getAttribute("poll")).getOptionNum();
			             
			             String[][] timeov1 = new String[num][panduan1]; 

			             String[][] optionValues = new String[num][panduan2];
						 for(int ii=1;ii<=num;ii++)
						 {
						 for(int jj=0;jj<panduan1;jj++)
						 { 
						   if(timeov1[ii-1][jj]==null)
						    { optionValues[ii-1][jj]="";}
							else
							{ optionValues[ii-1][jj]=timeov1[ii-1][jj];}
						 }
						 }
							for(int ii=1;ii<=num;ii++)
							{
								for(int jjj=panduan1;jjj<panduan2;jjj++)
									{ optionValues[ii-1][jjj]="";}
							}
							
							// poll.setTimeov(optionValues);
							session.setAttribute("dayarray",dayarray);
							
						}
					}else{
						     int[][] dayarray = new int[selectedLists.size()][3];
			      			 for(int i=0; i<selectedLists.size(); i++){
								int[] aday = new int[3];
								 aday = (int[]) selectedLists.get(i);
								dayarray[i] = aday;
							}
							
							Calendar a =  mgr.getNewCalendar();
							Calendar b =  mgr.getNewCalendar();
							
							int[] mid = new int[3];
							
							for(int i=0; i<dayarray.length; i++){
								a.set(dayarray[i][0],dayarray[i][1],dayarray[i][2]);
								for(int j=i+1; j<dayarray.length;j++){
									b.set(dayarray[j][0],dayarray[j][1],dayarray[j][2]);
									if(b.before(a)){//Calendar的比较时间方法
										mid=dayarray[i];
										dayarray[i] = dayarray[j];
										a.set(dayarray[j][0],dayarray[j][1],dayarray[j][2]);
										dayarray[j] = mid;
										//a=b;用到冒泡法
									} 
								}		
							}
							
							for(int i=0; i<dayarray.length; i++){
								out.println(dayarray[i][0]+"-"+(dayarray[i][1]+1)+"-"+dayarray[i][2]+"<br/>");
							}
							session.setAttribute("dayarray",dayarray);
								
								
						
					}
        %>
</div>
</div>
</div>
<div id="footer"> <a href="about.htm">关于</a>&nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>
