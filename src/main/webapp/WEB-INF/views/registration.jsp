<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Registration</title>
</head>
<body>
	<form:form action="confirm" modelAttribute="user">
		Login: <form:input path="login" />
		<br />
		Password: <form:password path="password" />
		<br />
		Country: <form:select items="${countries}" path="address.country"/>
		<br />
		City: <form:input path="address.city"/>
		<br />
		<c:forEach var="skill" items="${user.skills}" varStatus="status">
			<input id="skill[${status.index}]" type="checkbox"
				name="skills[${status.index}].id" value="${skill.id}" />
			<input type="hidden" name="skills[${status.index}].skillName"
				value="${skill.skillName}" />
			<label for="skill[${status.index}]">${skill.skillName}</label>
			<br />
		</c:forEach>
		<input type="submit" value="Submit" />
	</form:form>
</body>
</html>
