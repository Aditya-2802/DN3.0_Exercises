<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>Customer Registration</h1>
<form:form action="save" method="post" modelAttribute="customer">
<form:hidden path="id"/>
            <p><form:label path="name">Name </form:label> <form:input type="text" path="name"/></p>
            <p><form:label path="emailid">Email: </form:label> <form:input type="email" path="emailid"/></p>
            <p><form:label path="password">Password: </form:label> <form:input type="password" path="password"/></p>
            <input type="submit" value="submit"/>
        </form:form>
</body>
</html>