<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Dane z Neo4j</title>
</head>
<body>
	<h1>
		Dane z uzytkownika
	</h1>
	
	<P>  Dane z Neo4j ${tomek.name} </P>
	
	<P>  Uzywane narzedzia: </P>
	<c:forEach items="${tomek.usedTools}" var="tool">
	            ${tool}
	 </c:forEach>
</body>
</html>
