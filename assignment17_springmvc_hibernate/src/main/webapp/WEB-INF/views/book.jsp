<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<html>
<head>
	<title>BookDetails</title>
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
		.tg td{font-family:Arial, sans-serif;font-size:14px;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#fff;}
		.tg th{font-family:Arial, sans-serif;font-size:14px;font-weight:normal;padding:10px 5px;border-style:solid;border-width:1px;overflow:hidden;word-break:normal;border-color:#ccc;color:#333;background-color:#f0f0f0;}
		.tg .tg-4eph{background-color:#f9f9f9;}
		.h2{color:white;font-size:20px;}
		.bg{background-color: #E0E0E0;}
		.btn{border-radius: 12px;border:none;padding:10px 25px;text-align:center;text-decoration: none;display:inline-block;font-size:16px;cursor: pointer;color:white;width=35%;background-color: #63B8FF;}
		.h4{font-size:17px;color:black}
		.h5{font-size:15px;color:white}
		th,td{padding: 8px;text-align: left;border-bottom: 1px solid #ddd;}
		tr:hover {background-color:lightgreen;}
	</style>
</head>
<body>
	<div class="fixed-header">
        <div class="container">
           <h2 class="h2">
				Add Book Here
			</h2>
        </div>
    </div>
<div class="container">
<c:url var="addAction" value="/allbooks/add" ></c:url>

<form:form action="${addAction}" method ="POST"  modelAttribute="book">

<table>
			<c:if test="${!empty book.bookId}">
				<tr>
					<td>
						<form:label path="bookId" class="h4">
							<spring:message text="BookId"></spring:message>
						</form:label>
					</td>
					<td>
						<form:input path="bookId" readonly="true" size="8" disabled="true" /> 
						<form:hidden path="bookId" />
					</td>
				</tr>
			</c:if>
			
			<tr>
				<td><form:label path="title" class="h4">
						<spring:message text="Title"></spring:message>
					</form:label>
				</td>
				<td><form:input path="title" /></td>
			</tr>
			
			<tr>
				<td><form:label path="price" class="h4">
						<spring:message text="Price"></spring:message>
					</form:label></td>
				<td><form:input path="price" /></td>
			</tr>
			
			<tr>
				<td><form:label path="volume" class="h4">
						<spring:message text="Volume"></spring:message>
					</form:label></td>
				<td><form:input path="volume" /></td>
			</tr>
			
			<tr>
				<td><form:label path="publishDate" class="h4">
						<spring:message text="PublishDate(dd/MM/yyyy)"></spring:message>
					</form:label></td>
				<td><form:input path="publishDate"></form:input></td>
			</tr>
			
			<tr>
				<td><form:label path="subjectId" class="h4">
						<spring:message text="SubjectId"></spring:message>
					</form:label></td>
				<td><form:input path="subjectId" /></td>
			</tr>
						
			<tr>
				<td colspan="2"><c:if test="${!empty book.title}">
						<input type="submit" class="btn" value="<spring:message text="Edit Book"/>" />
					</c:if> <c:if test="${empty book.title}">
						<input type="submit" class="btn" value="<spring:message text="Add Book"/>" />
					</c:if></td>
			</tr>
		</table>	
</form:form>
<br>
<h2 class="h2">Book List</h2>
<c:if test="${!empty bookList}">
	<table class="tg">
	<tr>
		<th width="80">BookId</th>
		<th width="120">Title</th>
		<th width="80">Price</th>
		<th width="80">Volume</th>
		<th width="120">PublishDate</th>
		<th width="120">SubjectId</th>
		<td width="120">Query</td>	
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
			<td>${book.subjectId}</td>
			<td><a href="<c:url value='/allbooks/${book.bookId}' />">Query</a></td>	
			<td><a href="<c:url value='/allbooks/update/${book.bookId}' />" >Edit</a></td>
			<td><a href="<c:url value='/allbooks/delete/${book.bookId}' />" >Delete</a></td>
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