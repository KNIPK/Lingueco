<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>AdminController</title>
</head>
<body>

<jsp:include page="../include/header.jsp" />

<c:if test="${not action}">
   <jsp:include page="${action}.jsp" />
</c:if>

 <jsp:include page="../include/footer.jsp" />
 
</body>
</html>
