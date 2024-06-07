<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page isErrorPage='true' %>
<%-- isErrorPage="true" 설정 시 exception 내장 객체를 사용할 수 있다... --%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Error Page...</title>
</head>

<body>
	<h1>Error Page...</h1>
	<p>뭔가 에러가 있는데?</p>
	<p>Error code: <%= response.getStatus() %></p>
	<p>Exception Type: <%= exception.getClass().getSimpleName() %></p>
	<p>Message: <%= exception.getMessage() %></p>
</body>
</html>