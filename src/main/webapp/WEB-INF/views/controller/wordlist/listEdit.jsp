<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>${wordlist.name}</title>
</head>
<body>

<h1>${wordlist.name}</h1>
	<form method="POST" action="${pageContext.request.contextPath}/wordlists/${wordlist.name}/edit/">
		<table>
			<tr>
				<td>Nazwa listy:</td>
				<td><input name="newName" value=${wordlist.name} /></td>
			</tr>
			<tr>
				<td>Opis listy:</td>
				<td><input name="newDesc" value=${wordlist.desc} /></td>
			</tr>
		</table>
				<input type="hidden" name="oldName" value="${wordlist.name}" />
		<input type="submit" value="Dodaj"/>
	</form>


<a href="${pageContext.request.contextPath}/wordlists/${wordlist.name}/delete/">Usun liste!</a>
</body>
</html>
