<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<div id="headerMain">
	<div id="header">
		<a href="<c:url value ="/users/registration"/>" class="main-link">register</a> 
		<a href="<c:url value ="/users"/>" class="main-link">users</a>
		<a href="<c:url value ="/"/>" class="main-link">home</a>
	</div>
</div>
