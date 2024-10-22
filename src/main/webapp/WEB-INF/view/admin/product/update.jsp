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
    <title>Create User</title>
    <link href="https://cdn.jsdelivr.net/npm/simple-datatables@7.1.2/dist/style.min.css" rel="stylesheet" />
    <link href="/css/styles.css" rel="stylesheet" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
	<script>
		$(document).ready(() => {
		const avatarFile = $("#avatarFile");
		const orgImage = "${updateProduct.image}";
		
		if (orgImage) {
			const urlImage = "/images/product/" + orgImage;
			$("#avatarPreview").attr("src", urlImage);
			$("#avatarPreview").css({ "display": "block" });
		}
		
		avatarFile.change(function (e) {
		const imgURL = URL.createObjectURL(e.target.files[0]);
			$("#avatarPreview").attr("src", imgURL);
			$("#avatarPreview").css({ "display": "block" });
			});
		});
	</script>
</head>
<body class="sb-nav-fixed">
    
    <jsp:include page="../layout/header.jsp" />
    
    <div id="layoutSidenav">
    
        <jsp:include page="../layout/sidebar.jsp" />
        
        <div id="layoutSidenav_content">
            <main>
                <div class="container-fluid px-4">
                    <h1 class="mt-4">Update Product</h1>
                    <ol class="breadcrumb mb-4">
                        <li class="breadcrumb-item active"><a href="/admin" target="">Dashboard</a>/
                        	<a href="/admin/product" target="">Products</a>/Create
                        </li>
                    </ol>
                    
                    <div class="my-auto">
						<div class="row">
							<div class="col-md-6 col-12 mx-auto">
								<h3>Update product : ${updateProduct.name}</h3>
								<hr />
								<form:form method="post" action="/admin/product/update" modelAttribute="updateProduct" class="row" enctype="multipart/form-data" >
										<c:set var="errorPrice">
											<form:errors path="price" cssClass="invalid-feedback" />
										</c:set>
										<c:set var="errorDetailDesc">
											<form:errors path="detailDesc" cssClass="invalid-feedback" />
										</c:set>
										<c:set var="errorShortDesc">
											<form:errors path="shortDesc" cssClass="invalid-feedback" />
										</c:set>
										<c:set var="errorQuantity">
											<form:errors path="quantity" cssClass="invalid-feedback" />
										</c:set>
									<div class="mb-3 col-12 col-md-6" style="display: none">
										<label class="form-label">Id:</label>
										<form:input type="text" class="form-control" path="id" />
									</div>
									<div class="mb-3 col-12 col-md-6">
										<label class="form-label">Name:</label>
										<form:input type="text" class="form-control" path="name" readonly="True"/>
									</div>
									<div class="mb-3 col-12 col-md-6">
										<label class="form-label">Price:</label> 
										<form:input type="number" class="form-control ${not empty errorPrice?'is-invalid':''}" path="price" />
										${errorPrice}
									</div>
									<div class="mb-3 col-12">
										<label class="form-label">Detail description:</label> 
										<form:textarea type="text" class="form-control ${not empty errorDetailDesc?'is-invalid':''}" path="detailDesc" />
										${errorDetailDesc}
									</div>
									<div class="mb-3 col-12 col-md-6">
										<label class="form-label">Short description:</label> 
										<form:input type="text" class="form-control ${not empty errorShortDesc?'is-invalid':''}" path="shortDesc" />
										${errorShortDesc}
									</div>
									<div class="mb-3 col-12 col-md-6">
										<label class="form-label">Quantity:</label> 
										<form:input type="number" class="form-control ${not empty errorQuantity?'is-invalid':''}" path="quantity" />
										${errorQuantity}
									</div>
									<div class="mb-3 col-12 col-md-6">
                                        <label for="avatarFile" class="form-label">Image:</label>
                                        <input class="form-control" type="file" id="avatarFile" accept=".png, .jpg, .jpeg" name="avatarFile" />
                                    </div>
									<div class="col-12 mb-3">
                                        <img style="max-height: 250px; display: none;" alt="avatar preview" id="avatarPreview" />
                                    </div>
									
									<div class="col-12 mb-5">
										<button type="submit" class="btn btn-primary">Update</button>
										<a href="/admin/product/update/${updateProduct.id}" class="btn btn-primary">Cancel</a>
									</div>
								</form:form>
							</div>
						</div>
					</div>
                    
                </div>
            </main>
            
            <jsp:include page="../layout/footer.jsp" />
            
        </div>
    </div>
    <script src="js/scripts.js"></script>
</body>

</html>