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
<h4>${wordlist.desc}</h4>

<form method="POST" action="${pageContext.request.contextPath}/wordlists/${wordlist.name}/">
		<table>
				<tr>
					<td><b>${wordlist.langA}</b></td><td><b>${wordlist.langB}</b></td>
				</tr>
				<c:forEach items="${words}" var="word">
				            <tr>
				            	<td>${word.key.value}</td>
				            	<td>
				            		<c:forEach items="${word.value}" var="translation">
				            			${translation.value}  
				            		</c:forEach>
				            	</td>
				            <tr>
				</c:forEach>

			<tr>
				<td><input name="valA" /></td>
				<td><input name="valB" /></td>
				<td><input type="submit" /></td>
			</tr>
		</table>
		<input type="hidden" name="name" value="${wordlist.name}" />
</form>

</body>
</html>