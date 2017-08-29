<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>User details</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
	<script src="<c:url value="/resources/enable_form.js"/>"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<form:form id="user_details" action="id${user.id}" modelAttribute="user"
		cssClass="form-container">
		<div class="form-title">
			<h2>User details.</h2>
		</div>
		
		<form:hidden path="id"/>

		<div class="form-title">
			Login:
			<form:errors path="login" cssClass="error" />
		</div>
		<form:input path="login" cssClass="form-field" disabled="disabled"/>


		<div class="form-title">
			New password:
			<form:errors path="password" cssClass="error" />
		</div>
		<form:password path="password" cssClass="form-field" disabled="disabled"/>

		<div class="form-title">
			Age:
			<form:errors path="age" cssClass="error" />
		</div>
		<form:input path="age" cssClass="form-field" disabled="disabled"/>

		<div class="form-title">
			Country:
			<form:errors path="address.country" cssClass="error" />
		</div>
		<form:input path="address.country" cssClass="form-field" disabled="disabled"/>

		<div class="form-title">
			City:
			<form:errors path="address.city" cssClass="error" />
		</div>
		<form:input path="address.city" cssClass="form-field" disabled="disabled"/>

		<div class="form-title">
			Skills:
			<form:errors path="skills" cssClass="error" />
		</div>

		<c:forEach var="skill" items="${user.skills}" varStatus="status">
			<input disabled="disabled" id="skill[${status.index}]" type="checkbox"
				name="skills[${status.index}].id" value="${skill.id}" 
				<c:if test = "${status.index < selectedSkillsNumber}">checked</c:if>/>
			<input type="hidden" name="skills[${status.index}].skillName"
				value="${skill.skillName}" />
			<label for="skill[${status.index}]">${skill.skillName}</label>
			<br />
		</c:forEach>
		
		<div class="submit-container">
			<input id="update_button" type="submit" value="Update" class="submit-button" style="visibility: hidden"/>
			<button type="button" class="submit-button" onclick="enableForm('user_details', 'update_button')">Edit</button>
		</div>
	</form:form>
	<%@ include file="footer.jsp"%>
</body>
</html>
