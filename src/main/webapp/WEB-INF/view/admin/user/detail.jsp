<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- them the vao de co the goi duoc cac input -->
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta name="description" content="laptopshop" />
    <meta name="author" content="HG" />
    <title>User Detail ${id}</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://use.fontawesome.com/releases/v6.3.0/js/all.js" crossorigin="anonymous"></script>
</head>
<body class="sb-nav-fixed">
    
    <jsp:include page="../layout/header.jsp" />
    
    <div id="layoutSidenav">
    
        <jsp:include page="../layout/sidebar.jsp" />
        
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Information</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active"><a href="/admin" target="">Dashboard</a>/
                        	<a href="/admin/user" target="">Users</a>/Detail
                        </li>
                    </ol>
                    
                    <div class="my-auto">
						<div class="row">
							<div class="col-12 mx-auto">
								<div class="d-flex justify-content-between">
									<h3>User Details</h3>
									<a href="/admin/user" class="btn btn-success mt-2">Back</a>
								</div>
								<hr/>
							 	<div class="card" style="width: 60%;">
								  <div class="card-header">
								    User Information
								  </div>
								  <ul class="list-group list-group-flush">
								    <li class="list-group-item">ID: ${userDetail.id}</li>
								    <li class="list-group-item">Email: ${userDetail.email}</li>
								    <li class="list-group-item">Full Name: ${userDetail.fullName}</li>
								    <li class="list-group-item">Address: ${userDetail.address}</li>
								  </ul>
								</div>
							</div>
						</div>
					</div>
                    
                </div>
            </main>
            
            <jsp:include page="../layout/footer.jsp" />
            
        </div>
    </div>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"
        crossorigin="anonymous"></script>
    <script src="js/scripts.js"></script>
</body>

</html>
