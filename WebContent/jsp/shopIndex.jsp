<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp" %>
<title>acceuil</title>
</head>
<body>
	<%@ include file="sortForm.jsp"%>
	<%@ include file="menu/topMenu.jsp"%>
	<div class="container">
		<div class="row">
			<div class="col-12 mt-3">
				<%@ include file="menu/jewelsMenu.jsp"%>
			</div>
		</div>

	</div>

	<br>


	<div class="container">

		<div class="row offset-1 jewel">

			<c:forEach items="${ listBijoux }" var="bijoux">

				<div class="card h-10 col-xl-3 col-md-12 m-4 jewelDiv">

					<a href="shop/product?id=${bijoux.id_bijoux}"><img
						class="card-img-top" src="./image/bijoux/${bijoux.image_bijoux}"
						alt="photo d'un bijou"></a>

					<div class="card-body">

						<h4 class="card-title">

							<a href="shop/product?id=${bijoux.id_bijoux}" class="text-dark">${bijoux.nom_bijoux}</a>

						</h4>

						<h5 class="prixBijoux">${ bijoux.prix_bijoux }€</h5>

						<p class="card-text designerName">${ bijoux.designer.nom_designer }</p>

					</div>

					<div class="card-footer">

						<small class="text-muted categorieName">${ bijoux.categorie.nom_categorie }</small>

					</div>

				</div>

			</c:forEach>

		</div>

	</div>

		<%@ include file="footer/footer.jsp" %>




</body>
</html>
