<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add new Student</title>
</head>
<body>
<h3>Add Student</h3>
<form action="/students/insert" method="post">
    <c:if test="${not empty errorMessage}">
        <h4 class="error">${errorMessage}</h4>
    </c:if>
    <table>
        <tr>
            <td><label>Id</label></td>
            <td><input type="text" name="id" value="${student.id}"/></td>
        </tr>
        <tr>
            <td><label>Name</label></td>
            <td><input type="text" name="name" value="${student.name}"/><c:if test="${not empty errorMessage}"><span> ${errorMessage}</span></c:if></td>
        </tr>
        <tr>
            <td><label>Email</label></td>
            <td><input type="text" name="email" value="${student.email}"/></td>
        </tr>
        <tr>
            <td><label>Phone</label></td>
            <td><input type="text" name="phone" value="${student.phone}"/></td>
        </tr>
        <tr>
            <td><label></label></td>
            <td><input type="submit" value="Save"/></td>
        </tr>
    </table>
</form>
</body>
</html>
