<%--
  Created by IntelliJ IDEA.
  User: hue
  Date: 8/9/18
  Time: 11:44 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Delete Product</title>
</head>
<h1>Delete Prodcut</h1>
<body>
<c:if test='${requestScope["mess"]!=null}'>
    <span>${requestScope["mess"]}</span>
</c:if>
<form method="post">
    <fieldset>
        <h3>Are you sure?</h3>
        <p>
            <a href="p">Back to the product list</a>
        </p>
        <legend>Product Information</legend>
        <table>
            <tr>
                <td>Product code:</td>
                <td>${requestScope["delete"].getProduct_code()}</td>
            </tr>
            <tr>
                <td>Product name:</td>
                <td>${requestScope["delete"].getProduct_name()}</td>
            </tr>
            <tr>
                <td>Product price:</td>
                <td>${requestScope["delete"].getProduct_price()}</td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Delete"></td>
            </tr>
        </table>
    </fieldset>
</form>

</body>
</html>
