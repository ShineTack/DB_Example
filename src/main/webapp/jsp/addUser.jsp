<%--
  Created by IntelliJ IDEA.
  User: shinetack
  Date: 05.03.2020
  Time: 10:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add User</title>
</head>
<body>
<h1>Please add user</h1>
<form action="/addUser" method="post">
    <label for="first-name">First Name</label>
    <input type="text" id="first-name" name="first-name">
    <label for="last-name">Last Name</label>
    <input type="text" id="last-name" name="last-name">
    <input type="submit" value="Add User">
</form>
</body>
</html>
