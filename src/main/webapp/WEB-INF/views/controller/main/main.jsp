<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>MainController</title>
</head>
<body>
	<div class="container">
		<div class="jumbotron"><h1>${text}</h1></div>
		<br />
		<br />		
		<h2>Przykładowa tabelka</h2>
		<table class="table table-striped table-bordered table-hover table-condensed">
			<tr>
				<td>Panel administratora</td>
				<td>AdminController</td>
			</tr>
			<tr>
				<td>Listy słówek</td>
				<td>WordListController</td>
			</tr>
			<tr>
				<td>Zarządzanie kontem</td>
				<td>UserController</td>
			</tr>
		</table>
		<br /><br />
		<h2>Przykładowy formularz</h2>		
		<form method="POST" action="#" class="form-horizontal">
				<div class="form-group">
					<label class="control-label col-sm-2" for="firstName">First name:</label>
					<div class="col-sm-3">
						<input type="text" id="firstName" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="lastName">Last name:</label>
					<div class="col-sm-3">
						<input type="text" id="lastName" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="password">Password:</label>
					<div class="col-sm-3">
						<input type="password" id="password" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="email">E-mail:</label>
					<div class="col-sm-3">
						<input type="email" id="email" class="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-sm-2" for="status">Status:</label>
					<div class="col-sm-3">
						<select id="status" class="form-control">
							<option value="A">Active</option>
							<option value="I">Inactive</option>
						</select>
					</div>
				</div>
				<div class="form-group">
					<div class="col-sm-offset-2 col-sm-3">
						<input type="submit" value="Edit user" class="btn btn-primary" />
						<a href="${pageContext.request.contextPath}/${tenant}/user/list" class="btn btn-default">Back</a>
					</div>
				</div>
			</form>
	</div>
</body>
</html>
