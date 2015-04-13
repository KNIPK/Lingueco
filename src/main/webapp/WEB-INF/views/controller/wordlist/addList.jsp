<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<html>
<head>
<title>Create new list</title>
</head>
<body>
	<h1>Create new list:</h1>
	<form method="POST" action="${pageContext.request.contextPath}/wordlists/add/">
		<table>
			<tr>
				<td>List name:</td>
				<td><input name="name" /></td>
			</tr>
			<tr>
				<td>List desc:</td>
				<td><input name="desc" /></td>
			</tr>
			<tr>
				<td>First language:</td>
				<td><select name="langA">
						<option value="PL">Polish</option>
						<option value="ENG">English</option>
						<option value="GER">German</option>
				</select></td>
			</tr>
			<tr>
				<td>Second language:</td>
				<td><select name="langB">
						<option value="ENG">English</option>
						<option value="GER">German</option>
						<option value="PL">Polish</option>
				</select></td>
			</tr>
		</table>
		<input type="submit" />
	</form>

</body>
</html>
