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
	<%@ include file="header.jsp"%>
	<form:form action="registration" modelAttribute="user"
		cssClass="form-container">
		<div class="form-title">
			<h2>User registration.</h2>
		</div>

		<div class="form-title">
			Login:
			<form:errors path="login" cssClass="error" />
		</div>
		<form:input path="login" cssClass="form-field" />


		<div class="form-title">
			Password:
			<form:errors path="password" cssClass="error" />
		</div>
		<form:password path="password" cssClass="form-field" />

		<div class="form-title">
			Age:
			<form:errors path="age" cssClass="error" />
		</div>
		<form:input path="age" cssClass="form-field" />

		<div class="form-title">
			Country:
			<form:errors path="address.country" cssClass="error" />
		</div>
		<form:select items="${countries}" path="address.country"
			cssClass="form-field" />

		<div class="form-title">
			City:
			<form:errors path="address.city" cssClass="error" />
		</div>
		<form:input path="address.city" cssClass="form-field" />

		<div class="form-title">
			Skills:
			<form:errors path="skills" cssClass="error" />
		</div>

		<c:forEach var="skill" items="${user.skills}" varStatus="status">
			<input id="skill[${status.index}]" type="checkbox"
				name="skills[${status.index}].id" value="${skill.id}"
				<c:if test = "${status.index < selectedSkillsNumber}"> checked</c:if> />
			<input type="hidden" name="skills[${status.index}].skillName"
				value="${skill.skillName}" />
			<label for="skill[${status.index}]">${skill.skillName}</label>
			<br />
		</c:forEach>
		<div class="submit-container">
			<input type="submit" value="Submit" class="submit-button" />
		</div>
	</form:form>

	<%@ include file="footer.jsp"%>
</body>
</html>
