<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- them the vao de co the goi duoc cac input -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>

<html lang="en">

<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>User Page</title>
<!-- Latest compiled and minified CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">

<!-- Latest compiled JavaScript -->
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
<!-- <link href="/css/demo.css" rel="stylesheet"> -->

</head>

<body>
	<div class="container mt-5">
		<div class="row">
			<div class="col-12 mx-auto">
				<div class="d-flex justify-content-between mb-1">
					<h3>Table users</h3>
					<a href="user/create" class="btn btn-primary">Create user</a>
				</div>
				<hr/>
				<table class="table table-striped table-bordered table-hover">
				  <thead>
				    <tr>
				      <th scope="col">ID</th>
				      <th scope="col">Email</th>
				      <th scope="col">Full Name</th>
				      <th scope="col">Action</th>
				    </tr>
				  </thead>
				  <tbody>
				    <c:forEach var="user" items="${listUsers}">
				        <tr>
					      <th scope="row">${user.id}</th>
					      <td>${user.email}</td>
					      <td>${user.fullName}</td>
					      <td>
					      	  <a href="/admin/user/${user.id}" class="btn btn-success">View</a>
					      	  <a href="/admin/user/update/${user.id}" class="btn btn-warning">Update</a>
					      	  <button type="button" class="btn btn-danger">Delete</button>
					      </td>
				        </tr>
				    </c:forEach>
				  </tbody>
				</table>
			</div>
		</div>
	</div>
</body>

</html>