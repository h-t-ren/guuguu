<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
 <p><span class="special1">安排一个活动</span></p>
 <p><span class="special3"><div style="font-weight: bold;color: #3399FF;display:inline;">1. 概况   </div> >> 2. 选择时间   >> 3. 发送邀请</span></p>
 <form:form method="post" modelAttribute="meeting">
		  	<fieldset>
		  		<legend>概况:</legend>
		  		 <p>标题:<span class="warning" id="titlewarning"></span><br />
                 <form:input type="text" size="62" path="title"  onblur="checkTitle(this)" />
                 </p>
                 <p>地点 (可选填):<br />
                 <form:input type="text" size="62" path="location" />
                 </p>
                 <p>描述(可选填):<br />
                  <form:textarea  path="description" rows="3" cols="80" style="width: 90%" />
                 </p>
                 <p>您的姓名: <span class="warning" id="namewarning"></span><br />
                 <form:input type="text" size="62" path="creator" onblur="checkName(this)" />
                 </p>
                  <p>您的邮箱:<span class="warning" id="emailwarning"></span><br />
                 <form:input type="text" size="62" path="creatorEmail" onblur ="checkEmail(this)"/><br/>
                                                             您可以通过该电子邮箱收到的链接直接查看并管理投票。
                 </p>
		  	</fieldset>
		  	<input type="hidden" name="step1" />
			<p><button type="submit" disabled="disabled">取消</button><button type="submit">下一步</button></p>
</form:form>