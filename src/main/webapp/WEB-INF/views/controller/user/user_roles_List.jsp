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

<div align="left"><b>Users roles List</b></div>

</h3>
<div class="panel-body">
    <c:if test="${empty user_roles_List}">
        There are no roles
    </c:if>
    <c:if test="${not empty user_roles_List}">
        <table class="table table-hover table-bordered">
            <thead style="background-color: #bce8f1;">
            <tr>
                <th>User_role_id</th>
                <th>user_id</th>
                <th>role</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            </thead>
            <tbody>


            <c:forEach items="${user_roles_List}" var="role">
                <tr>
                    <td><c:out value="${role.user_role_id}"/></td>
                    <td><c:out value="${role.user_id}"/></td>
                    <td><c:out value="${role.role}"/></td>

                        <%--<td><a href="editUser?id=${user.id}">Edit</a></td>--%>
                    <td><a href="deleteUser_Role?id=${role.user_role_id}">Delete</a></td>
                </tr>
            </c:forEach>

            </tbody>
        </table>
    </c:if>
</div>
</body>
</html>
