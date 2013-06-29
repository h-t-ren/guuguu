<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<title>Guuguu</title>
<body>
	<div id="formsContent">
		<h2>Forms</h2>
		<form:form method="post" modelAttribute="meetingForm">

		  	<fieldset>
		  		<legend>Meeting</legend>
		  		<form:input path="title" />
		  		</fieldset>
			<p><button type="submit">Submit</button></p>
		</form:form>

</body>
</html>
