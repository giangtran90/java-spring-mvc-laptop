<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!-- dung de format number -->
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Cart - laptopshop</title>

<!-- Google Web Fonts -->
<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link
	href="https://fonts.googleapis.com/css2?family=Open+Sans:wght@400;600&family=Raleway:wght@600;800&display=swap"
	rel="stylesheet">

<!-- Icon Font Stylesheet -->
<link rel="stylesheet"
	href="https://use.fontawesome.com/releases/v5.15.4/css/all.css" />
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.4.1/font/bootstrap-icons.css"
	rel="stylesheet">

<!-- Libraries Stylesheet -->
<link href="/client/lib/lightbox/css/lightbox.min.css" rel="stylesheet">
<link href="/client/lib/owlcarousel/assets/owl.carousel.min.css"
	rel="stylesheet">


<!-- Customized Bootstrap Stylesheet -->
<link href="/client/css/bootstrap.min.css" rel="stylesheet">

<!-- Template Stylesheet -->
<link href="/client/css/style.css" rel="stylesheet">

</head>
<body>

	<!-- Spinner Start -->
	<div id="spinner"
		class="show w-100 vh-100 bg-white position-fixed translate-middle top-50 start-50  d-flex align-items-center justify-content-center">
		<div class="spinner-grow text-primary" role="status"></div>
	</div>
	<!-- Spinner End -->


	<!-- Navbar start -->

	<jsp:include page="../layout/header.jsp" />

	<!-- Navbar End -->


	<!-- Modal Search Start -->
	<div class="modal fade" id="searchModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog modal-fullscreen">
			<div class="modal-content rounded-0">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">Search by
						keyword</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body d-flex align-items-center">
					<div class="input-group w-75 mx-auto d-flex">
						<input type="search" class="form-control p-3"
							placeholder="keywords" aria-describedby="search-icon-1">
						<span id="search-icon-1" class="input-group-text p-3"><i
							class="fa fa-search"></i></span>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal Search End -->

	<!-- Cart Page Start -->
	<div class="container-fluid py-5">
		<div class="container py-5">
			<div>
				<ol class="breadcrumb">
					<li class="breadcrumb-item active"><a href="/" target="">Home</a>/Sản
						phẩm trong giỏ hàng</li>
				</ol>
			</div>
			<c:set var="val" value="${flag}"/>
			<c:choose>
				<c:when test="${val == 1}">
					<div class="table-responsive">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Hình ảnh</th>
									<th scope="col">Tên</th>
									<th scope="col">Giá</th>
									<th scope="col">Số lượng</th>
									<th scope="col">Tổng</th>
									<th scope="col">Xử lý</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="cartDetail" items="${cartDetails}">
									<tr>
										<th scope="row">
											<div class="d-flex align-items-center">
												<img src="images/product/${cartDetail.product.image}"
													class="img-fluid me-5 rounded-circle"
													style="width: 80px; height: 80px;" alt="">
											</div>
										</th>
										<td>
											<p class="mb-0 mt-4">
												<a href="/product/${cartDetail.product.id}">${cartDetail.product.name}</a>
											</p>
										</td>
										<td>
											<p class="mb-0 mt-4"><fmt:formatNumber type="number" value="${cartDetail.price}" /> đ</p>
										</td>
										<td>
											<div class="input-group quantity mt-4" style="width: 100px;">
												<div class="input-group-btn">
													<button
														class="btn btn-sm btn-minus rounded-circle bg-light border">
														<i class="fa fa-minus"></i>
													</button>
												</div>
												<input type="text"
													class="form-control form-control-sm text-center border-0"
													value="${cartDetail.quantity}"
													data-cart-detail-id="${cartDetail.id}"
													data-cart-detail-price="${cartDetail.price}">
												<div class="input-group-btn">
													<button
														class="btn btn-sm btn-plus rounded-circle bg-light border">
														<i class="fa fa-plus"></i>
													</button>
												</div>
											</div>
										</td>
										<td>
											<p class="mb-0 mt-4" data-cart-detail-id="${cartDetail.id}"><fmt:formatNumber type="number" value="${cartDetail.price*cartDetail.quantity}" /> đ</p>
										</td>
										<td>
											<button class="btn btn-md rounded-circle bg-light border mt-4">
												<i class="fa fa-times text-danger"></i>
											</button>
										</td>
			
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
					
					<div class="row g-4 justify-content-start">
				<div class="col-12 col-sm-8 col-md-8 col-lg-6 col-xl-4">
					<div class="bg-light rounded">
						<div class="p-4">
							<h1 class="display-6 mb-4">
								Tổng <span class="fw-normal">đơn hàng</span>
							</h1>
							<div class="d-flex justify-content-between mb-4">
								<h5 class="mb-0 me-4">Giá trị đơn hàng
								:</h5>
								<p class="mb-0" data-cart-total-price="${total}"><fmt:formatNumber type="number" value="${total}" /> đ</p>
							</div>
							<div class="d-flex justify-content-between">
								<h5 class="mb-0 me-4">Phí vận chuyển</h5>
								<div class="">
									<p class="mb-0"><fmt:formatNumber type="number" value="0" /> đ</p>
								</div>
							</div>
						</div>
						<div
							class="py-4 mb-4 border-top border-bottom d-flex justify-content-between">
							<h5 class="mb-0 ps-4 me-4">Tổng cộng</h5>
							<p class="mb-0 pe-4" data-cart-total-price="${total}"><fmt:formatNumber type="number" value="${total}" /> đ</p>
						</div>
						<button
							class="btn border-secondary rounded-pill px-4 py-3 text-primary text-uppercase mb-4 ms-4"
							type="button">Thanh toán</button>
					</div>
				</div>
			</div>
				</c:when>    
			    <c:otherwise>
			        Chưa có sản phẩm trong giỏ hàng
			        <br />
			    </c:otherwise>
			</c:choose>
		</div>
	</div>
	<!-- Cart Page End -->

	<jsp:include page="../layout/footer.jsp" />


	<!-- Back to Top -->
	<a href="#"
		class="btn btn-primary border-3 border-primary rounded-circle back-to-top"><i
		class="fa fa-arrow-up"></i></a>


	<!-- JavaScript Libraries -->
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js"></script>
	<script src="/client/lib/easing/easing.min.js"></script>
	<script src="/client/lib/waypoints/waypoints.min.js"></script>
	<script src="/client/lib/lightbox/js/lightbox.min.js"></script>
	<script src="/client/lib/owlcarousel/owl.carousel.min.js"></script>

	<!-- Template Javascript -->
	<script src="/client/js/main.js"></script>
</body>
</html>