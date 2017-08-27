<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Confirmation</title>
</head>
<body>
	Registered user:
	<br /> id: ${storedUser.id}
	<br /> login: ${storedUser.login}
	<br /> password: ${storedUser.password}
	<br /> age: ${storedUser.age}
	<br /> locale: ${storedUser.locale}
	<br /> country: ${storedUser.address.country}
	<br /> city: ${storedUser.address.city}
	<br /> skills:
	<c:forEach var="skill" items="${user.skills}">${skill.skillName} </c:forEach>
	<br />
	<a href="home">home</a>
</body>
</html>
