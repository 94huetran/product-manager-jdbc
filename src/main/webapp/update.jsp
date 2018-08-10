<%--
  Created by IntelliJ IDEA.
  User: hue
  Date: 8/9/18
  Time: 10:55 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Product</title>
</head>
<body>
<h2>Product</h2>
<c:if test='${requestScope["mess"]!=null}'>
    <span>${requestScope["mess"]}</span>
</c:if>
<p><a href="p">Back to the list product</a></p>

<form method="post">
    <fieldset>
        <legend>Update information of product</legend>
        <table>
            <tr>
                <td>Product code:</td>
                <td><input type="text" name="code" id="code" value="${requestScope["product"].getProduct_code()}"></td>
            </tr>
            <tr>
                <td>Product name:</td>
                <td><input type="text" name="name" id="name" value="${requestScope["product"].getProduct_name()}"></td>
            </tr>
            <tr>
                <td>Product price:</td>
                <td><input type="text" name="price" id="price" value="${requestScope["product"].getProduct_price()}"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Update"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
