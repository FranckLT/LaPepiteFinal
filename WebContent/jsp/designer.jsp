<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="link.jsp" %>
<title>${designer.nom_designer}</title>
</head>
<body>

	<%@ include file="menu/topMenu.jsp"%>


	<div class="container">
		<div class="row">
			<div class="col-lg-8 col-md-10 mx-auto mt-5">
				<div class="site-heading">
					<h1 class="text-center">${designer.nom_designer }</h1>
					<span class="subheading text-muted">${designer.description_designer}</span>
				</div>
			</div>
		</div>
	</div>

	<div class="container">

		<c:if test="${not empty errorMessage }">

			<div
				class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3"
				role="alert">
				<h4 class="alert-heading text-center">Erreur</h4>
				<p class="text-center">${errorMessage }</p>
			</div>
		</c:if>

		<div class="row">

			<div class="col-lg-8 offset-lg-2 mt-4">

				<div class="row">

					<c:forEach items="${ bijouxList }" var="bijoux">

						<div class="card h-10 col-lg-4 col-md-11 m-auto jewelDiv">

							<a href="/LaPepite/shop/product?id=${bijoux.id_bijoux}"><img
								class="card-img-top"
								src="/LaPepite/image/bijoux/${bijoux.image_bijoux}"
								alt="photo d'un bijou"></a>

							<div class="card-body">

								<h4 class="card-title">

									<a href="/LaPepite/shop/product?id=${bijoux.id_bijoux}">${bijoux.nom_bijoux}</a>

								</h4>

								<h5>${ bijoux.prix_bijoux }€</h5>

								<p class="card-text designerName">${ bijoux.designer.nom_designer }</p>

							</div>

							<div class="card-footer">

								<small class="text-muted categorieName">${ bijoux.categorie.nom_categorie }</small>

							</div>

						</div>

					</c:forEach>
				</div>
			</div>
		</div>
	</div>
	
			<%@ include file="footer/footer.jsp" %>
	


</body>
</html>