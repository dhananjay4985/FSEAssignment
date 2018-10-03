<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
		<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
		<title>List of Students</title>
		<style>
			tr:first-child{
				font-weight: bold;
				background-color: #C6C9C4;
			}
		</style>
</head>
	
<body>
	<h1>List Of Students</h1>
	<table>
	<tr>
		<td>Name</td><td>Entering date</td><td>Nationality</td><td>Code</td>
	</tr>
	<c:forEach items="$students" var="student">
		<tr>
			<td>${student.name}</td>
			<td>${student.enteringDate}</td>
			<td>${student.nationality}</td>
			<td><a href="<c:url value='/edit-${student.code}-student'/>">$student.code</td>
			<td><a href="<c:url value='/delete-${student.code}-student'/>">delete</td>
		</tr>
	</c:forEach>		
	</table>
	</br>
	<a href="<c:url value='/new' />">Add New Student</a>
</body>
</html>