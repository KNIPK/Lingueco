<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Nowa lista</title>
</head>
<body>
	<h1>Utwórz nową listę:</h1>
	<form method="POST" action="${pageContext.request.contextPath}/wordlists/add/">
		<table>
			<tr>
				<td>Nazwa listy:</td>
				<td><input name="name" /></td>
			</tr>
			<tr>
				<td>Opis listy:</td>
				<td><input name="desc" /></td>
			</tr>
			<tr>
				<td>Język ojczysty:</td>
				<td><select name="langA">
						<option value="POL">Polski</option>
						<option value="ENG">Angielski</option>
						<option value="GER">Niemiecki</option>
						<option value="FRE">Francuski</option>
						<option value="SRP">Serbski</option>
				</select></td>
			</tr>
			<tr>
				<td>Język którego się uczysz:</td>
				<td><select name="langB">
						<option value="POL">Polski</option>
						<option value="ENG">Angielski</option>
						<option value="GER">Niemiecki</option>
						<option value="FRE">Francuski</option>
						<option value="SRP">Serbski</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" />
	</form>

</body>
</html>
