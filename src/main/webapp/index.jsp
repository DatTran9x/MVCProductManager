<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 12/24/2021
  Time: 2:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Product List</title>
  </head>
  <body>
  <h1>Product List</h1>
  <p>
    <a href="/products?action=create">Create new product</a>
  </p>
  <p>
    <a href="/products?action=find">Find product</a>
  </p>
  </p>
  <table>
    <tr>
      <th>Name</th>
      <th>Description</th>
      <th>Photo</th>
      <th>Edit</th>
      <th>Delete</th>
    </tr>
  <c:forEach items="${requestScope['Product']}" var="Products">
    <tr>
      <td><a href="/products?action=view&id=${Products.getId()}">${Products.getName()}</a></td>
      <td>${Products.getDescription()}</td>
      <td><img src="${Products.getImg()}" width="200" height="auto"></td>
      <td><a href="/products?action=edit&id=${Products.getId()}">Edit</a></td>
      <td><a href="/products?action=delete&id=${Products.getId()}">delete</a></td>
    </tr>
  </c:forEach>
  </table>
  </body>
</html>
