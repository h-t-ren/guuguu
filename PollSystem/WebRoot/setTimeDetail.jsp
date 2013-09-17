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
<link href="css/guuguustyle.css" rel="stylesheet" type="text/css" />

  </head>
  
  <style>
<!--
#content{
width:700px;
margin:0px;
color:#484848;
border-top:1px dotted #000000;
padding:0px;
text-align: left;
}
#biaodan{
margin:0px;
padding:10px 0px 0px 51px;
}
p span.special{
font-size:22px;
font-weight:bold;
color:#000000;
}
input.special1{
padding:0px 8px 0px 0px;
border:none;
color:#3399FF;
background-color:transparent;
}
input.special2{
border:1px #3399FF solid;
color:#484848;
background-color:transparent;
padding:3px;
}
input.input1{
width:60px;
}
 span.warning{
color:#3399FF;
font-size:12px;
}
#biaodan1{
margin:0px;
padding:0px 0px 0px 9px;
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
text-align:center;
}
table.table1 td.special3{
padding:3px;
color:#000000;
background-color:#D7EBFF;
}
table.table1 td.special4{
color: #484848;
}

table.table1 td input.special5{
width:60px;
border:1px solid #3399FF;
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
    <li><a href="index.jsp?type=1&timeout=newpoll">安排内容</a></li>
    <li><a href="index.jsp?type=2&timeout=newpoll">安排时间</a></li>
    <li class="help"><a href="help.htm">咕咕帮助</a></li>
  </ul>
</div>
  <%!
  	public boolean checkRepeat(String str,List<Option> options){
  		boolean flag = false;
  		for(int i=0;i<options.size();i++){
  			if(str.equals(options.get(i).getOptionContent())){
  				flag = true;
  				break;
  			}
  		}
  		return flag;
  	}
   %>

<div id="main">  <div id="content">
<div id="biaodan">
<%
	request.setCharacterEncoding("GBK");
	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
	int[][] dayarray = (int[][]) session.getAttribute("dayarray");
	String action = request.getParameter("action");
	if(action!=null && action.equals("submit")){
		String submitType = request.getParameter("submit");
		int i = 1;
		int num = poll.getOptionNum();
		int nd = dayarray.length;
		if(submitType.equals("复制并粘贴第一列")){
			String[][] optionValues = new String[num][nd];
			String optionPara;
			while(i<=num){
				optionPara="option"+i+"_"+0;
				for(int j=0; j<nd; j++){
					optionValues[i-1][j] =  request.getParameter(optionPara);
				}		
					i++;		
			}
			poll.setTimeov(optionValues);
			response.sendRedirect("setTimeDetail.jsp");
			return;
	
		}
		if(submitType.equals("增加选项数目")){
			String[][] optionValues = new String[num+2][nd];
			String optionPara;	
			while(i<=num){
				for(int j=0; j<nd; j++){
					optionPara = "option"+i+"_"+j;
					optionValues[i-1][j] =  request.getParameter(optionPara);
			    }		
				i++;		
			}
			poll.setTimeov(optionValues);
			poll.setOptionNum(num + 2);
			response.sendRedirect("setTimeDetail.jsp");
			return;
		}
		if(submitType.equals("取消")){
			session.invalidate();
			response.sendRedirect("index.jsp");
 			return;
		}
		if(submitType.equals("<<上一步")){
			String[][] optionValues = new String[num][nd];
			String optionPara;	
			
			while(i<=num){
				for(int j=0; j<nd; j++){
					optionPara="option"+i+"_"+j;
					optionValues[i-1][j] =  request.getParameter(optionPara);
				}		
				i++;		
			}
			poll.setTimeov(optionValues);
			response.sendRedirect("selectDate.jsp");
			return;
		}
		if(submitType.equals("完成并提交>>")){
			String[][] optionValues = new String[num][nd];
			String optionPara;
				
			while(i<=num){
				for(int j=0; j<nd; j++){
					optionPara="option"+i+"_"+j;
					//out.println(optionPara);
					optionValues[i-1][j] =  request.getParameter(optionPara).trim();
					//out.println(optionValues[i-1]);
				}		
				i++;		
			}
			
		
		
	        String[] optionlist = new String[(num+1)*nd];
			int nn=0;
			boolean warning=false;
			String warn="您输入的时间有误,请重新输入!";
			 for(int j=0; j<nd;j++){
			    optionlist[nn]=""+dayarray[j][0]+"."+(dayarray[j][1]+1)+"."+dayarray[j][2]+"time";
				nn++;
				for(int k=0; k<num;k++){
				    
					//String optiontime = optionValues[k][j];
					
					try{
						int atime = Integer.parseInt(optionValues[k][j]);
						
						if(optionValues[k][j].length()==4){
								if(atime>=0 && atime<=2400){
									optionValues[k][j]=optionValues[k][j].substring(0,2)+":"+optionValues[k][j].substring(2,4);
								 }
								 else { warning=true;}
						}
						
						if(optionValues[k][j].length()==3){
							if(atime>=0 && atime<=2400){
								optionValues[k][j]="0"+optionValues[k][j].substring(0,1)+":"+optionValues[k][j].substring(1,3);
			 			     }
			 			      else { warning=true;}
							}
						
						if(optionValues[k][j].length()==2){
								if(atime>=0 && atime<=24){
								optionValues[k][j]=atime+":00";
			 			     }
							  else { warning=true;}
							  }
							  
							  if(optionValues[k][j].length()==1){
							if(atime>=0 && atime<10)
							 { optionValues[k][j]="0"+atime+":00";}
							  else { warning=true;}
							  }
			 			
							if(warning){
							session.setAttribute("warn",warn);
						 response.sendRedirect("settime.jsp");
						   return;
						  }
						
					  }catch (Exception e){
					
					}
					
				if(optionValues[k][j].equals("")){
					optionlist[nn]="";
				}else{
					optionlist[nn]=""+dayarray[j][0]+"."+(dayarray[j][1]+1)+"."+dayarray[j][2]+"time"+optionValues[k][j];
				}
				
				
				if(optionlist[nn].length()>32){
				 optionlist[nn]=optionlist[nn].substring(0,32);
			   }
				//out.println("nn="+nn+":"+optionlist[nn]);
				nn++;
			}
		
		}
        	for(int j=0; j<nd; j++){
			for(int k=0; k<num; k++){
				if(!optionValues[k][j].equals("")){//是否非空？有
					optionlist[j*(num+1)]="";
				}	
			}
    	}
	    	List<Option> options = new ArrayList<Option>();
	    	for(int j=0;j<optionlist.length;j++){
	    		if(!optionlist[j].equals("")){
	    			boolean flag = checkRepeat(optionlist[j],options);
	    			if(!flag){
		    			Option o = new Option();
		    			o.setOptionContent(optionlist[j]);
		    			options.add(o);
	    			}
	    		}
	    	}
	    	poll.setOptions(options);
	    	response.sendRedirect("pollCreated.jsp");
			return;
	}	
		
		
}
 %>

 <form name="form1" method="post" action="setTimeDetail.jsp">
 <input type="hidden" name="action" value="submit"/>
 <div id="biaodan1">
  <%if(session.getAttribute("warn")!=null){
 %><span class="warning">
       <% out.print((String)session.getAttribute("warn"));}
  	  %> </span>
  <p><span class="special">选择时间（共三步）：</span><span style="color:#000000">您还可以设定每天的具体时间</span><br />您的输入、例如8、 15:30、 
    2034、03及22，系统将默认为上午8点(08:00)，<br />下午3点30分(15:30)，下午8点34分(20:34)，
    上午3点(03:00)，下午10点(22:00) </p>
  <p>您也可以输入间隔时间或地点：   例如，07:30-10:20  、12点至13点、14:00会议等 </p>
  <p>您也可以不填写具体时段，直接点击下一步即可。</p>
   		<table class="table1">
   		<tr><td>&nbsp;</td>
		    <% 
		   // System.out.println(dayarray.length);
			for(int i=0; i<dayarray.length; i++){
			%>
			<td class="special3"><%=dayarray[i][0] %>-<%=dayarray[i][1]+1 %>-<%=dayarray[i][2] %></td>
			<% 
			}
			%>
			</tr>
			<% 
			int index = 1;
			String optionName, optionContent;
			String[][] timeov = poll.getTimeov();
			if(timeov!=null){
				if(timeov[0].length < dayarray.length){
				timeov = new String[poll.getOptionNum()][dayarray.length]; 
				}
			}else if(timeov == null){
				timeov = new String[poll.getOptionNum()][dayarray.length];
			}
			
			while(index <= poll.getOptionNum()){ 
			%>
			<tr>
			<td class="special4">时间（段）<%=index %>:</td>
			<% 
		  	for(int j=0; j<dayarray.length; j++){
		    	optionContent = "";
		  	 	if(timeov[index-1][j]==null){
					optionContent = "";
				}else{
					optionContent = timeov[index-1][j];
				}
			
			optionName = "option"+index+"_"+j;
			%>
			<td><input type="text" class="special5" name="<%=optionName %>" value="<%=optionContent %>"/></td>
			<% 
			}
			index ++;
			%>
			</tr>
			<% 
			}
			%>
			</table>
			<p>
		  <input type="submit" name="submit" value="复制并粘贴第一列" class="special2">
		  <input type="submit" name="submit" value="增加选项数目" class="special2"></p>
		  </div>
		  <p><input type="submit" name="submit" value="<<上一步" class="special1"> 
		  <input type="submit" name="submit" value="完成并提交>>" class="special1"> 
		  <input type="submit" name="submit" value="取消" class="special1"></p>
		   <p style="font-size:12px; color:#484848; padding-left:8px;">（空白的选项将被忽略,重复的选项将只记录一次。）</p>
 </form>
 </div>
</div>
</div>
<div id="footer"> <a href="about.htm">关于</a> &nbsp;·&nbsp;<a href="rule.htm">规则</a> &nbsp;·&nbsp;广告&nbsp;·&nbsp; <a href="contact.htm">联系我们</a></div>
</div>
</body>
</html>