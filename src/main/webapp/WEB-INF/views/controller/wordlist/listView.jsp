<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


<h1>${wordlist.name} (${wordlist.wordsAmount})</h1>
<c:if test="${not empty add}"> Utworzono liste! Zacznij dodawac do niej slowka!</c:if>
<c:if test="${privacy == 0}"> (Lista prywatna)</c:if>

<h2>${wordlist.desc}</h2>
<h3>( ${wordlist.langA} > ${wordlist.langB} )</h3>

<c:if test="${empty wordlist.name}"> NIE ZNALEZIONO LISTY!</c:if> 

	<c:forEach items="${words}" var="word">
	            ${word.value} <br>
	 </c:forEach>

<form method="POST" action="${pageContext.request.contextPath}/wordlists/${wordlist.name}/">
		<table>
			<tr>
				<td>${wordlist.langA}:</td><td>${wordlist.langB}:</td>
			</tr>
			<tr>
				<td><input name="valA" /></td>
				<td><input name="valB" /></td>
			</tr>
		</table>
		<input type="submit" />
		<input type="hidden" name="name" value="${wordlist.name}" />
</form>

</body>
</html>