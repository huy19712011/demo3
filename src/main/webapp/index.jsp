<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
    <form action="filterInput">
        <input type="text" name="name">
        <br>
        <input type="text" name="password">
        <br>
        <button type="submit">Submit</button>
    </form>
</h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
</body>
</html>
