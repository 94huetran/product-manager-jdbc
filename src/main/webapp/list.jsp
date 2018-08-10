<%--
  Created by IntelliJ IDEA.
  User: hue
  Date: 8/9/18
  Time: 2:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Products</title>
</head>
<body>
<h1>List product</h1>
<p>
    <a href="p?action=create">Create new product</a>
</p>
<table border="1">
    <tr>
        <td>ID</td>
        <td>Code</td>
        <td>Name</td>
        <td>Price</td>
        <td>Edit</td>
        <td>Delete</td>
    </tr>
<c:forEach items='${requestScope["products"]}' var="product">
   <tr>
       <td>${product.getProduct_id()}</td>
       <td>${product.getProduct_code()}</td>
       <td>${product.getProduct_name()}</td>
       <td>${product.getProduct_price()}</td>
       <td><a href="p?action=update&&id=${product.getProduct_id()}">Edit</a></td>
       <td><a href="p?action=delete&&id=${product.getProduct_id()}">Delete</a></td>
   </tr>
</c:forEach>
</table>
</body>
</html>
