<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ page import="java.util.Set" %>
<%@ page import="java.util.HashSet" %>
<%@ page import="com.library.main.model.Book" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html>
<head>
	<title>SubjectDetails</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<!-- Latest compiled JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<style type="text/css">
		
		body{padding-top: 60px;padding-bottom: 40px;}
		  .container{
        width: 80%;
        margin: 0 auto; 
       /* Center the DIV horizontally */
    }
    .fixed-header, .fixed-footer{
        width: 100%;
        position: fixed;        
        background: #333;
        padding: 5px 0;
    }
    .fixed-header{
        top: 0;
    }
    .fixed-footer{
        bottom: 0;
    }    
		.tg  {border-collapse:collapse;border-spacing:0;border-color:#ccc;}
		.tg td{font-family:Arial,sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial,sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9}
		.h2{color:white;font-size:20px;}
		.bg{background-color: #E0E0E0;}
		.btn{border-radius:12px;border:none;padding:10px 25px;text-align:center;text-decoration:none;display:inline-block;font-size:16px;cursor: pointer;color:white;width=35%;background-color:#63B8FF;}
		.h4{font-size:17px;color:black}
		.h5{font-size:15px;color:white}
		th,td{padding:8px;text-align:left;border-bottom:1px solid #ddd;}
		tr:hover {background-color:lightgreen;}
	</style>
</head>

<body>
<div class="fixed-header">
        <div class="container">
           <h2 class="h2">
			Add Subject Here
			</h2>
        </div>
    </div>
<div class="container">

<c:url var="addAction" value="/allsubjects/add" ></c:url>

<form:form action="${addAction}" method ="POST"  modelAttribute="subject">
<table>
			<c:if test="${!empty subject.subjectId}">
				<tr>
					<td><form:label path="subjectId">
							<spring:message text="SubjectId" />
						</form:label>
					</td>
					<td><form:input path="subjectId" readonly="true" size="8" disabled="true"></form:input> 
						<form:hidden path="subjectId" />
					</td>
				</tr>
			</c:if>
			<tr>
				<td>
					<form:label path="subjectTitle" class="h4">
						<spring:message text="SubjectTitle"></spring:message>
					</form:label>
				</td>
				<td>
					<form:input path="subjectTitle"></form:input>
				</td>
			</tr>
			<tr>
				<td><form:label path="durationInHours" class="h4">
						<spring:message text="DurationInHours" />
					</form:label></td>
				<td><form:input path="durationInHours" /></td>
			</tr>
			<%
				Set<Book> bookSet = new HashSet<Book>();
				Book book = new Book();
			//	book.setBookId(bookId);
			%>
			<tr>
				<td><form:label path="references" class="h4">
						<spring:message text="ReferenceBooks" />
					</form:label></td>
				<td><form:input path="references" /></td>
			</tr>
				
			<tr>
				<td colspan="2"><c:if test="${!empty subject.subjectTitle}">
						<input type="submit" class="btn" value="<spring:message text="Edit Subject"/>" />
					</c:if> <c:if test="${empty subeject.subjectTitle}">
						<input type="submit" class="btn" value="<spring:message text="Add Subject"/>" />
					</c:if></td>
			</tr>
		</table>	
</form:form>
<br>
	<h2 class="h2">List Of Subjects</h2>
	<c:if test="${!empty subjectList}">
		<table class="tg">
			<tr>
				<td width="80">SubjectId</td>
				<td width="120">Title</td>
				<td width="80">DurationInHours</td>
				<td width="120">Reference Books</td>
				<td width="80">Query</td>
				<th width="80">Edit</th>
				<th width="80">Delete</th>
			</tr>
			<c:forEach items="${subjectList}" var="subject">
				<tr>
					<td>${subject.subjectId}</td>
					<td>${subject.subjectTitle}</td>
					<td>${subject.durationInHours}</td>
					<td>
					<c:forEach items="${subject.references}" var="bookref" varStatus="loop">
						${bookref.title}
						<c:if test="${not loop.last}">,</c:if>
					</c:forEach>
					</td>
					<td><a href="<c:url value='/allsubjects/${subject.subjectId}' />">Query</a></td>
					<td><a href="<c:url value='/allsubjects/update/${subejct.subjectId}' />">Edit</a></td>
					<td><a href="<c:url value='/allsubjects/delete/${subject.subjectId}' />">Delete</a></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	</div>
	<div class="fixed-footer">
        <div class="h5">Copyright &copy; 2018 Cognizant India</div>        
    </div>
</body>
</html>