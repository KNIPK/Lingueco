<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Listy słówek</title>
</head>
<body>
	<h1>Twoje listy</h1>

	<table>
	
	<c:forEach items="${wordLists}" var="wordList">
	            <tr> <td> <a href="${pageContext.request.contextPath}/wordlists/${wordList.name}">${wordList.name}</a> </td> </tr>
	 </c:forEach>
	</table>

</br>
<b><a href="${pageContext.request.contextPath}/wordlists/add">	Dodaj!</a></b>
</body>
</html>
