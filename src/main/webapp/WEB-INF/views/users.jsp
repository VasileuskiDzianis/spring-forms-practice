<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Users</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
</head>
<body>
	<%@ include file="header.jsp"%>
	<c:forEach var="user" items="${users}" varStatus="status">
		<form class="line-item" action="<c:url value="/users/deletion" />"
			method="post">
			<span class="column-item">${user.login} from ${user.address.country}, ${user.address.city}  | </span> 
			<a class="details-link" href="<c:url value="/users/details/id${user.id}" />">details</a> 
			<input type="hidden" name="userId" value="${user.id}" /> 
			<input type="submit" value="Delete" class="submit-button" />
		</form>
	</c:forEach>
	<%@ include file="footer.jsp"%>
</body>
</html>
