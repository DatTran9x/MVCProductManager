<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/24/2021
  Time: 3:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create product</title>
</head>
<body>
<h1>Create product</h1>
<p>
    <a href="/products">Back to products list</a>
</p>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<a href="/products">Back to product list</a>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <th>Description</th>
                <td><input type="text" name="description" id="description"></td>
            </tr>
            <tr>
                <th>Img</th>
                <td><input type="text" name="img" id="img"></td>
            </tr>
            <tr>
                <input type="submit" value="Create new product">
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
