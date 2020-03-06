<%--
  Created by IntelliJ IDEA.
  User: shinetack
  Date: 06.03.2020
  Time: 16:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>User List</title>
</head>
<body>
    <h2>Add User</h2>
    <form action="/users" method="post">
        <label for="firstName">First Name</label>
        <input type="text" id="firstName" name="firstName">
        <label for="lastName">Last Name</label>
        <input type="text" id="lastName" name="lastName">
        <input type="submit" value="Add User">
    </form>
    <h3>Users :</h3>
    <table>
        <tr>
            <th>ID</th>
            <th>First Name</th>
            <th>Last Name</th>
        </tr>
        <c:forEach items="${usersFromServer}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.firstName}</td>
                <td>${user.lastName}</td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
