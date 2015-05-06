<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 2015-04-28
  Time: 22:05
  To change this template use File | Settings | File Templates.
--%>
<html>
<head>
    <title></title>
</head>
<body>
<h1>REGISTER</h1>
<form:form commandName="user" action="/user/saveUser" method="post">
    <table>
        <form:hidden path="user_id"/>


        <tr>
            <td><label>username : </label></td>
            <td> <form:input path="username"/></td>
        </tr>

        <tr>
            <td><label>password </label></td>
            <td><form:input  path="password"/></td>
        </tr>

        <tr>
            <td colspan="2"><input type="submit" value="Save"/></td>
        </tr>
    </table>

    <H1>Or  <a href="/user/login">LOGIN</a> </H1>
    <H1>Get ALL USERS and DELETE <a href="/user/getAllUsers">GET&DELETE</a> </H1>
</form:form>

</body>
</html>
