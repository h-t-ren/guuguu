<%@page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt1" %>
<script type="text/javascript" charset="utf-8">
			$(document).ready(function() {
				$('#highlight').dataTable( {
					 "bPaginate": false,
					 "bInfo":false,
					 "bFilter":false,
					 "bReteieve":true,
					 "bDestroy":true
				} );
			} );
		</script>
		<style type="text/css" title="currentStyle">
			.bt { border-top: 1px solid black; }
			.br { border-right: 1px solid black; }
			.bb { border-bottom: 1px solid black; }
			.bl { border-left: 1px solid black; }
		</style>


 <p><span class="special3">活动发起者:${meeting.userName}</span></p>
 <p><span class="special3">地点:${meeting.location}</span></p>
 <p><span class="special3">活动描述:${meeting.description}</span></p>
 <p><span class="warning"><c:if test="${message!=null}">${message}</c:if></span></p>
 <form:form method="post" modelAttribute="meeting">
<fieldset>  	
	<table class="display" id="highlight">
		<thead>
			<tr>
			<th>参与者</th>
			<c:forEach var="meetingTime" items="${meetingTimes}">
				<th>
				<fmt1:formatDate pattern="yyyy-MM-dd" type="date" value="${meetingTime.date}" />
				${meetingTime.timeSlot}</th>
			</c:forEach>
			</tr>
		</thead>
		<tbody>
         <c:forEach var="entry" items="${userHash}">
			 <tr>
				<td>${entry.key}</td>
				<c:forEach var="status" items="${entry.value}">
				  <td>${status}</td>
				</c:forEach>
			 </tr>
		</c:forEach>
		<tr>
				<td>投票数</td>
				<c:forEach var="meetingTime" items="${meetingTimes}">
				 <td><c:if test="${meetingTime.count!=null}">${meetingTime.count}</c:if>
				 <c:if test="${meetingTime.count==null}">0</c:if></td>
				</c:forEach>
		</tr>
		
		<c:if test="${message==null}">
	      <tr>
				<td><input type="text" value="${email}" name="email"></td>
				<c:forEach var="meetingTime" items="${meetingTimes}">
				 <td> <input type="checkbox" name="sel_${meetingTime.id}" value="${meetingTime.id}"></td>
				</c:forEach>
		</tr>
		</c:if>
		</tbody>
	</table>

	</fieldset>
	<c:if test="${message==null}"><input type="submit" value="投票" /></c:if>
</form:form>