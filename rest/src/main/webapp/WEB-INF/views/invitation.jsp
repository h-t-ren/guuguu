<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <p><span class="special1">安排一个活动</span></p>
 <p><span class="special3"><div style="display:inline;">1. 概况 </div> >> 2. 选择时间    >> <div style="font-weight: bold;color: #3399FF;display:inline;">3. 发送邀请 </div></span></p>
 <form:form method="post" modelAttribute="meeting">
		  	<fieldset>
		  		<legend>通过guuguu发出邀请</legend>
		  		
                 <p>请输入联系人电子邮箱地址:<br />
                  <form:textarea  path="invitations" rows="3" cols="80" style="width: 90%" />
                  <br/>
                                                             用分号";"来分隔多位联系人
                 </p>
		  	</fieldset>
		  	<input type="hidden" name="finish" />
		  	 <form:input type="hidden" id="eventsJson" path="eventsJson"/>
             <form:input type="hidden" path="title"/>
             <form:input type="hidden" path="location"/>
             <form:input type="hidden" path="description"/>
             <form:input type="hidden" path="creator"/>
             <form:input type="hidden" path="creatorEmail"/>
		    <p><input type="submit" name="cancel" value="取消" /><input type="submit" value="发出邀请并结束" /></p>
</form:form>