<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 22/06/2020
  Time: 2:32 CH
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Customer List</title>
</head>
<body>
<h1>Customer list</h1>
<p>
    <a href="/customers?action=create">Create new customer</a>
</p>
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

<%--<input type="text" name="submitValue" placeholder="Enter your word">--%>
<%--<a href="/customers?action=view"><input type="submit" name="submitView" value="View customer"></a>--%>

</body>
</html>
