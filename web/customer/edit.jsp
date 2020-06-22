<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: VCOM
  Date: 22/06/2020
  Time: 4:36 CH
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit Customer</title>
    <style>
        .message{
            color: green;
        }
    </style>
</head>
<body>
<h1>Edit Customer</h1>
<p>
    <c:if test="${requestScope['message']!=null}">
        <span class="message">${requestScope['message']}</span>
    </c:if>
</p>

<p>
    <a href="/customers">Back to customer list</a>
</p>

<form method="post">
    <fieldset>
        <legend>Customer Information</legend>
        <table>
            <tr>
                <th>Name:</th>
                <td><input type="text" name="name" id="name" value="${requestScope['customer'].getName()}"></td>
            </tr>
            <tr>
                <th>Email:</th>
                <td><input type="text" name="email" id="email" value="${requestScope['customer'].getEmail()}"></td>
            </tr>
            <tr>
                <th>Address:</th>
                <td><input type="text" name="address" id="address" value="${requestScope['customer'].getAddress()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update customer"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
