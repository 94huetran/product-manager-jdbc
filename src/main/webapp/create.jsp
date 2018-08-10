<%--
  Created by IntelliJ IDEA.
  User: hue
  Date: 8/9/18
  Time: 4:27 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create new product</title>
</head>
<body>
<c:if test='${requestScope["mess"]!= null}'>
    <span>${requestScope["mess"]}</span>
</c:if>
<p>
    <a href="p">Back to list product</a>
</p>
<form method="post">
    <fieldset>
        <legend><a href="p?action=create">Create new product</a></legend>
        <table>
            <tr>
                <td>Product code:</td>
                <td><input type="text" name="code" id="code"></td>
            </tr>
            <tr>
                <td>Name:</td>
                <td><input type="text" name="name" id="name"></td>
            </tr>
            <tr>
                <td>Price:</td>
                <td><input type="text" name="price" id="price"></td>
            </tr>
            <tr>
                <td></td>
                <td><input type="submit" value="Create product"></td>
            </tr>
        </table>
    </fieldset>
</form>
</body>
</html>
