<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Artur
  Date: 2015-05-05
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<div align="left"><b>Users List</b></div>

</h3>
<div class="panel-body">
    <c:if test="${empty userList}">
        There are no Users
    </c:if>
    <c:if test="${not empty userList}">
        <table class="table table-hover table-bordered">
            <thead style="background-color: #bce8f1;">
            <tr>
                <th>Id</th>
                <th>Name</th>
                <th>Password</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>


            <c:forEach items="${userList}" var="user">
                <tr>
                    <td><c:out value="${user.user_id}"/></td>
                    <td><c:out value="${user.username}"/></td>
                    <td><c:out value="${user.password}"/></td>

                    <%--<td><a href="editUser?id=${user.id}">Edit</a></td>--%>
                    <td><a href="deleteUser?id=${user.user_id}">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>
<H1><a href="/user">MENU</a> </H1>
</body>
</html>
