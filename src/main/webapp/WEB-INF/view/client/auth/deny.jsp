<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8" />
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no" />
<meta name="description" content="" />
<meta name="author" content="" />
<title>Access-Deny</title>
<link href="css/styles.css" rel="stylesheet" />
</head>
<body class="bg-primary">
	<h2>Sorry, you do not have permission to view this page.</h2>

	Click <a href="<c:url value="/" /> ">here</a> to go back to the Homepage.

	<script src="js/scripts.js"></script>
</body>
</html>