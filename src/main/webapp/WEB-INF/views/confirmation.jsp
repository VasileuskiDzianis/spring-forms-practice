<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Confirmation</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2 class="home_content">Stored user data:</h2>
<p class="home_content">
	<br /> id: ${storedUser.id}
	<br /> login: ${storedUser.login}
	<br /> password: ${storedUser.password}
	<br /> age: ${storedUser.age}
	<br /> locale: ${storedUser.locale}
	<br /> country: ${storedUser.address.country}
	<br /> city: ${storedUser.address.city}
	<br /> skills:
	<c:forEach var="skill" items="${user.skills}">[${skill.skillName}]</c:forEach>
	<br />
</p>
	<%@ include file="footer.jsp"%>
</body>
</html>
