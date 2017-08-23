<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Registration</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
</head>
<body>

	<form:form action="confirm" modelAttribute="user"
		cssClass="form-container">
		<div class="form-title">
			<h2>User registration.</h2>
		</div>
		<div class="form-title">Login:</div>
		<form:input path="login" cssClass="form-field" />
		<div class="form-title">Password:</div>
		<form:password path="password" cssClass="form-field" />
		<div class="form-title">Age:</div>
		<form:password path="age" cssClass="form-field" />
		<div class="form-title">Country:</div>
		<form:select items="${countries}" path="address.country"
			cssClass="form-field" />
		<div class="form-title">City:</div>
		<form:input path="address.city" cssClass="form-field" />
		<div class="form-title">Skills:</div>
		<c:forEach var="skill" items="${user.skills}" varStatus="status">
			<input id="skill[${status.index}]" type="checkbox"
				name="skills[${status.index}].id" value="${skill.id}" />
			<input type="hidden" name="skills[${status.index}].skillName"
				value="${skill.skillName}" />
			<label for="skill[${status.index}]">${skill.skillName}</label>
			<br />
		</c:forEach>
		<div class="submit-container">
			<input type="submit" value="Submit" class="submit-button" />
		</div>
	</form:form>
</body>
</html>
