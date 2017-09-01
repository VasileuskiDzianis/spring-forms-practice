<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page session="false"%>
<html>
<head>
<title>Users</title>
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/form.css"/>" />
<link rel="stylesheet" type="text/css"
	href="<c:url value="/resources/confirmation_window.css"/>" />
	<script src="<c:url value="/resources/confirm_deletion.js"/>"></script>
</head>
<body>
	<%@ include file="header.jsp"%>
	<c:if test="${deletedUser ne null}">
		<div class="line-item">
			<span id="user-deleted">User &quot;${deletedUser}&quot; was successfully deleted</span>
		</div>
	</c:if>	
		
	<c:forEach var="user" items="${users}" varStatus="status">
		<div class="line-item">
			<span class="column-item">${user.login} from ${user.address.country}, ${user.address.city} | </span> 
				<a class="details-link" href="<c:url value="/users/details/id${user.id}" />">details</a> 
				<input type="hidden" name="userId" value="${user.id}" /> 
				<input type="button" value="x" onclick="confirmDeletion('${user.id}','${user.login} from ${user.address.country}, ${user.address.city}')" class="del-button"/>
		</div>
	</c:forEach>

	<%@ include file="footer.jsp"%>
	
  <div id="prompt-form-container">
    <form id="prompt-form" action="<c:url value="/users/deletion" />" method="post">
      <div id="prompt-message"></div>
      <input id="deleting_user" type="hidden" name="userId" />
      <input type="submit" value="Ok">
      <input type="button" name="cancel" value="Cancel">
    </form>
  </div>
  
</body>
</html>
