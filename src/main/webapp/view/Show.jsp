<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/24/2021
  Time: 4:35 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Show Product</title>
</head>
<body>
<h1>Show product</h1>
<p>
    <a href="/products?action=create">Create new product</a>
</p>
<p>
    <a href="/products">Back to product list</a>
</p>
<table>
    <tr>
        <th>Name</th>
        <th>Description</th>
        <th>Img</th>
    </tr>
    <tr>
        <td><p>${requestScope["Product"].getName()}</p></td>
        <td><p>${requestScope["Product"].getDescription()}</p></td>
        <td><img src="${requestScope["Product"].getImg()}" width="250" height="auto"></img></td>
    </tr>
</table>
</body>
</html>
