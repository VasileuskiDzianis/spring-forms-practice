<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Message</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<h2 class="home_content">${message}</h2>
	<%@ include file="footer.jsp"%>
</body>
</html>
