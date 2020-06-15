<%@ page language="java" contentType="text/html; charset=ISO-8859-1"  pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page session="false" %>
<html>
<head>
	<title>BookDetails</title>
	<style type="text/css">
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
	</style>
</head>
<body>
<h1>
	Add Book Here
</h1>

<c:url var="addAction" value="/allbooks/add" ></c:url>

<form:form action="${addAction}" commandName="book">
<table>
	<c:if test="${!empty book.title}">
	<tr>
		<td>
			<form:label path="bookId">
				<spring:message text="BookId"/>
			</form:label>
		</td>
		<td>
			<form:input path="bookId" readonly="true" size="8"  disabled="true" />
			<form:hidden path="bookId" />
		</td> 
	</tr>
	</c:if>
	<tr>
		<td>
			<form:label path="title">
				<spring:message text="Title"/>
			</form:label>
		</td>
		<td>
			<form:input path="title" />
		</td> 
	</tr>	
	<tr>
		<td>
			<form:label path="price">
				<spring:message text="Price"/>
			</form:label>
		</td>
		<td>
			<form:input path="price" />
		</td> 
	</tr>
	<tr>
		<td>
			<form:label path="volume">
				<spring:message text="Volume"/>
			</form:label>
		</td>
		<td>
			<form:input path="volume" />
		</td> 
	</tr>
	
	<tr>
		<td>
			<form:label path="publishDate">
				<spring:message text="PublishDate"/>
			</form:label>
		</td>
		<td>
			<form:input path="publishDate" />
		</td>
	</tr>	
	<tr>
		<td colspan="2">
			<c:if test="${!empty book.title}">
				<input type="submit"
					value="<spring:message text="Edit Book"/>" />
			</c:if>
			<c:if test="${empty book.title}">
				<input type="submit"
					value="<spring:message text="Add Book"/>" />
			</c:if>
		</td>
	</tr>
</table>	
</form:form>
<br>
<h3>Book List</h3>
<c:if test="${!empty bookList}">
	<table class="tg">
	<tr>
		<th width="60">BookId</th>
		<th width="120">Title</th>
		<th width="80">Price</th>
		<th width="60">Volume</th>
		<th width="120">PublishDate</th>
		<th width="120">Edit</th>
		<th width="120">Delete</th>
	</tr>
	<c:forEach items="${bookList}" var="book">
		<tr>
			<td>${book.bookId}</td>
			<td>${book.title}</td>
			<td>${book.price}</td>
			<td>${book.volume}</td>
			<td>${book.publishDate}</td>
			<td><a href="<c:url value='/allbooks/update/${book.bookId}' />" >Edit</a></td>
			<td><a href="<c:url value='/allbooks/delete/${book.bookId}' />" >Delete</a></td>
		</tr>
	</c:forEach>
	</table>
</c:if>
</body>
</html>