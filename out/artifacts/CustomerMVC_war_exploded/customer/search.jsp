<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 24/06/2020
  Time: 12:25 SA
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search Customer</title>
</head>
<body>
<h1>Customer list</h1>
<p><a href="/customers?action=create">Create new customer</a></p>
<p><a href="/customers">Back to customer list</a></p>

<table border="1" style="border-collapse: collapse">
    <tr>
        <th>Name</th>
        <th>Email</th>
        <th>Address</th>
        <th>Edit</th>
        <th>Delete</th>
        <th>View</th>
    </tr>
    <c:forEach items="${requestScope['customers']}" var="customer">
        <tr>
            <td>${customer.getName()}</td>
            <td>${customer.getEmail()}</td>
            <td>${customer.getAddress()}</td>
            <td><a href="/customers?action=edit&id=${customer.getId()}"><button type="button">Edit</button></a></td>
            <td><a href="/customers?action=delete&id=${customer.getId()}"><button type="button">Delete</button></a></td>
            <td><a href="/customers?action=view&id=${customer.getId()}"><button type="button">View</button></a></td>
        </tr>
    </c:forEach>
</table>
<br><br>

<form method="post" action="/customers?action=search">
    <input type="text" name="searchValue" placeholder="Enter your word">
    <input type="submit" name="submitView" value="Search customer">
</form>
</body>
</html>
