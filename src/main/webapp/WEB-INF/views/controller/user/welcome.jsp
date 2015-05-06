<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>WELCOME TO SECURE AREA</title>
</head>
<body>
<h1>Message : ${message}</h1>
<h1>Author : ${author}</h1>


<b><a href="/user/admin">admin</a></b>
<b><a href="/user/index">user</a></b>



<h3><a href='<c:url value="/j_spring_security_logout" />' > Logout</a></h3>
</body>
</html>
