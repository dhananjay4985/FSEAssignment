<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Book Details</title>

	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>	
	
	<style type="text/css">	
		body{padding-top: 60px;padding-bottom: 40px;}
		  .container{
        width: 80%;
        margin: 0 auto; 
       /* Center the DIV horizontally */
    }
    .col-sm-2{
    	font-size:12px;color:black
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
    .navbar-brand:focus, .navbar-brand:hover {
		text-decoration: none
	}
	.navbar-brand>img {
		display: block
	}
	.navbar-brand {
		color: white
	}
	.navbar-default .navbar-brand {
		color: white
	}    
   </style>
</head>
<body>
	<spring:url value="/" var="urlHome" />
	<div class="fixed-header">
	<div class="navbar-header">
			<a class="navbar-brand" href="${urlHome}">GoBack to Home</a>
		</div>
    </div>
    
<div class="container">

	<c:if test="${not empty msg}">
		<div class="alert alert-${css} alert-dismissible" role="alert">
			<button type="button" class="close" data-dismiss="alert" aria-label="Close">
				<span aria-hidden="true">&times;</span>
			</button>
			<strong>${msg}</strong>
		</div>
	</c:if>

	<h2>Book Details</h2>
	<br />

	<div class="row">
		<label class="col-sm-2">BookId</label>
		<div class="col-sm-10">${book.bookId}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Title</label>
		<div class="col-sm-10">${book.title}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Price</label>
		<div class="col-sm-10">${book.price}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">Volume</label>
		<div class="col-sm-10">${book.volume}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">PublishDate</label>
		<div class="col-sm-10">${book.publishDate}</div>
	</div>

	<div class="row">
		<label class="col-sm-2">SubjectId</label>
		<div class="col-sm-10">${book.subjectId}</div>
	</div>
</div>
	<div class="fixed-footer">
        <div class="navbar-brand">Copyright &copy; 2018 Cognizant India</div>        
    </div>
</body>
</html>