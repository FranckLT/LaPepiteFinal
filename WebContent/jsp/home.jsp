<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="link.jsp" %>
<title>La Pepite - Acceuil</title>
</head>
<body>


	<!--Top menu-->


	<header>

	<div class="background">

		<div class="header">
			<%@ include file="menu/topMenu.jsp"%>
			<div class="row">
				<div class="offset-5 mt-5">
					<a href="/LaPepite/shop" id="shopButton" class="">E-SHOP</a>
				</div>

			</div>
		</div>
	</div>
	</header>

	<c:if test="${not empty errorMessage }">

		<div class="ht-tm-element alert alert-danger" role="alert">
			<h4 class="alert-heading">Erreur</h4>
			${errorMessage }
		</div>
	</c:if>
	<!--Caroussel-->


	<div class="row justify-content-center m-5">
		<div id="carouselImage" class="carousel slide col-7"
			data-ride="carousel">
			<div class="carousel-inner">
				<div class="carousel-item active">
					<img class="d-block w-100" src="./image/foot.jpg" alt="foot">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="./image/breakfast.jpg"
						alt="breakfast">
				</div>
				<div class="carousel-item">
					<img class="d-block w-100" src="./image/pineapple.jpg"
						alt="pineapple">
				</div>
			</div>
		</div>

		<!-- test -->


		<div class="socialNetwork col-2">
			<div class="row topRow col-12">
				<div class="leftBlock col-6">
					<h2>TopLeftBlock</h2>
				</div>

				<div class="rightBlock col-6">
					<h2>TopRightBlock</h2>
				</div>
			</div>
			<div class=" row bottomRow col-12">
				<div class="leftBlock col-6 ">
					<h2>BottomLeftBlock</h2>
				</div>
				<div class="rightBlock col-6">
					<h2>BottomRightBlock</h2>
				</div>
			</div>
		</div>
	</div>

<%@ include file="footer/footer.jsp" %>
	

</body>
</html>