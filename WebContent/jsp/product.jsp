
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp"%>
<title>${bijoux.nom_bijoux}</title>
</head>
<body>


	<%@ include file="menu/topMenu.jsp"%>



	<div class="container">



		<c:if test="${not empty errorMessage }">

			<div
				class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3 mt-5"
				role="alert">
				<h4 class="alert-heading text-center">Erreur</h4>
				<p class="text-center">${errorMessage }</p>
			</div>
		</c:if>

		<c:if test="${ not empty bijoux}">

			<div class="col-lg-8 offset-lg-2">

				<div class="card mt-4">
					<img class="card-img-top img-fluid"
						src="/LaPepite/image/bijoux/${bijoux.image_bijoux}"
						alt="image de bijoux">
					<div class="card-body">
						<h3 class="card-title">${bijoux.nom_bijoux}</h3>
						<h4 class="prixBijoux pl-3">${bijoux.prix_bijoux} €</h4>
						<p class="card-text">${bijoux.description_bijoux}</p>
						<div class="row justify-content-between">
							<form id="product-form"
								action="/LaPepite/shop/product?id=${bijoux.id_bijoux}"
								method="post" role="form" style="display: block;" class="mr-5">
								<select name="numberOfProduct" size="1" class="mx-3">
									<option selected="true">1</option>
									<option>2</option>
									<option>3</option>
									<option>4</option>
								</select> <input type="submit" class="btn btn-primary" value="Commander">
							</form>
						</div>


					</div>
				</div>
				<!-- /.card -->

				<!-- Commentaires  -->

				<div class="card card-outline-secondary my-4">
					<div class="card-header">Product Reviews</div>
					<div class="card-body">

						<c:forEach items="${ commentairesList }" var="commentaire">
							<p>${commentaire.texte_commentaire }</p>
							<small class="text-muted">Ecrit par 
								${commentaire.utilisateur.nom_utilisateur } ${commentaire.utilisateur.prenom_utilisateur} le
								${commentaire.date_commentaire }</small>
							<hr>
						</c:forEach>


						<h4 class="text-center">Donnez votre avis !</h4>
						<form method="post" action="/LaPepite/commentaire">

							<input type="text" name="commentaire" class="form-control">
							<input type="hidden" value="${bijoux.id_bijoux }" name="idBijoux"> <input
								type="submit" class="form-control btn btn-primary mt-1" value="Envoyer">

						</form>
					</div>
				</div>
				<!-- /.card -->

			</div>

		</c:if>

	</div>
	
			<%@ include file="footer/footer.jsp" %>
	

</body>
</html>
