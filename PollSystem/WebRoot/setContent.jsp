<%@ page language="java" import="java.util.*,polls.*" pageEncoding="GBK"%>


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
 
  <%
  	request.setCharacterEncoding("GBK");
  	PollInformationBean poll = (PollInformationBean)session.getAttribute("poll");
 	String action = request.getParameter("action");
 	List<Option> options = poll.getOptions();
 	int index = 0;
 	if(action!=null && action.equals("submit")){
 		Integer count = (Integer)session.getAttribute("count");
 		int countValue = count.intValue();
 		options.clear();
//System.out.println(countValue);
		for(int i=0;i<countValue+1;i++){
			String optionContent = request.getParameter("option"+(i+1));
			if(optionContent!=null){
				optionContent = optionContent.trim();
				if(!optionContent.equals("")){
					boolean flag = checkRepeat(optionContent,options);
					if(!flag){
						Option o = new Option();
						o.setOptionContent(optionContent);
						options.add(o);
					}else{
						//donoting;
					}
				}
			}
		}
			 
 		String submitType = request.getParameter("submit");
	 		if(submitType.equals("����ѡ����Ŀ")){
	 			poll.setOptionNum(poll.getOptionNum() + 2);
	 			response.sendRedirect("setContent.jsp");
	 			return;
	 		}else if(submitType.equals("<<��һ��")){
	 			response.sendRedirect("stepFirst.jsp");
	 			return;
	 		}else if(submitType.equals("ȡ��")){
	 			session.invalidate();
	 			response.sendRedirect("index.jsp");
	 			return;
	 		}else if(submitType.equals("��ɲ��ύ>>")){
	 			response.sendRedirect("pollCreated.jsp");
	 			return;
	 		}
 		
 			
 			
 	}
   %>
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
color: #484848;
}
p span{
color:#000000;
}
p span.special{
font-size:22px;
font-weight:bold;
color:#000000;

}
input.special1{
padding:0px 8px 0px 0px;
color:#3399FF;
background-color:transparent;
border:none;
}

input.special2{
border:1px #3399FF solid;
color: #484848;
background-color:transparent;
padding:3px;
}
 span.warning{
color:#3399FF;
font-size:12px;
}
#biaodan1{
margin:0px;
padding:0px 0px 0px 9px;
}
input.special3
{
border:1px solid  #3399FF;
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
    <li><a href="index.jsp">������ҳ</a></li>
    <li><a href="index.jsp?type=1&timeout=new">��������</a></li>
    <li><a href="index.jsp?type=2&timeout=new">����ʱ��</a></li>
    <li class="help"><a href="help.htm">��������</a></li>
  </ul>
</div>

<div id="main">
<div id="content">
<div id="biaodan">


     <form name="form1" method="post" action="setContent.jsp" onsubmit="return true">
     <input type="hidden" name="action" value="submit"/>
	 <div id="biaodan1">

  <span class="warning" id="optionwarning"></span>
	   <p><span class="special">�������ݣ�����������</span><span>�趨��ÿ���μ��߽���ѡ���ʺ���/���ķ���</span></p>
	   	
		  
		  <%
			while(index < options.size()){
			%>
				<p>ѡ��<%=index+1 %>
				<input type="text" id = "optionid" class = "special3" name = "option<%=index+1 %>" value="<%=options.get(index).getOptionContent() %>" /></p>
			<%
				index ++ ;
			}
			while(index < poll.getOptionNum()){
			%>
				<p>ѡ��<%=index+1 %>
				<input type="text" id = "optionid" class = "special3" name = "option<%=index+1 %>" value="" /></p>
			<%
				index ++ ;
			}
			session.setAttribute("count",new Integer(index)); 
			%>
	
	   <p><input type="submit" name="submit" value="����ѡ����Ŀ" class="special2"></p>
		</div><p class="special0">
		  <input type="submit" name="submit" value="<<��һ��" class="special1"> 
		  <input type="submit" name="submit" value="��ɲ��ύ>>" class="special1" onclick="return checkOptions()"> 
		  <input type="submit" name="submit" value="ȡ��" class="special1">
		 </p>
		 <p style="font-size:12px; color: #484848;padding-left:8px">���հ׵�ѡ�������,����25���ֵ�ѡ�ֻ����ǰ25����,�ظ���ѡ�ֻ��¼һ�Ρ���</p>
     </form>
</div>
</div>
</div>
<div id="footer"> <a href="about.htm">����</a> &nbsp;��&nbsp;<a href="rule.htm">����</a> &nbsp;��&nbsp;���&nbsp;��&nbsp; <a href="contact.htm">��ϵ����</a></div>
</div>
</body>
</html>
