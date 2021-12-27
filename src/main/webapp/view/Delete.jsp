<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/24/2021
  Time: 4:00 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete product</title>
</head>
<body>
<h1>Delete product</h1>
<p>
    <a href="/products">Back to customer list</a>
</p>
<form method="post">
    <fieldset>
        <legend>Product information</legend>
        <table>
            <tr>
                <th>Name</th>
                <td><input type="text" name="name" id="name" value="${requestScope["Product"].getName()}"></td>
            </tr>
            <tr>
                <th>Description</th>
                <td><input type="text" name="description" id="description"
                           value="${requestScope["Product"].getDescription()}"></td>
            </tr>
            <tr>
                <th>Img</th>
                <td><input type="text" name="img" id="img" value="${requestScope["Product"].getImg()}"></td>
            </tr>
            <tr>
                <input type="submit" value="Delete product">
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
