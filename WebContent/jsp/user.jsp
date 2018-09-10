<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp"%>
<title>Mon compte</title>
</head>
<body>

	<%@include file="menu/topMenu.jsp"%>
	<%@include file="menu/userMenu.jsp"%>

	<div class="container">

		<div>


			<div class="row">



				<h2 class="col-12 text-center mt-5">
					Bienvenue <span class="nomUtilisateur">${sessionScope.utilisateur.prenom_utilisateur}
						${sessionScope.utilisateur.nom_utilisateur}</span>
				</h2>

			</div>

			<div class="row">

				<h1>Panier</h1>

			</div>

			<c:choose>
				<c:when
					test="${sessionScope.utilisateur.panier.listProduit.size() < 1 }">

					<h4 class="col-12 text-center">
						Votre panier est vide <i class="fa fa-shopping-cart"></i>
					</h4>

				</c:when>
				<c:when
					test="${ not empty sessionScope.utilisateur.panier.listProduit}">

					<div class="row flex-column border border-dark shadow-lg rounded">
						<div class="col-auto border border-dark m-2 shadow-sm rounded">
							<div class="row m-0 flex-row p-4 shadow-sm rounded">

								<div class="col-3 text-primary text-center">Bijoux</div>
								<div class="col-2 text-primary text-center">Prix</div>
								<div class="col-1 text-primary text-center">Qte</div>
								<div class="col-4 text-primary text-center">Ajouter /
									Supprimer</div>
								<div class="col-2 text-primary text-center">Total</div>


							</div>

							<c:forEach items="${sessionScope.utilisateur.panier.listProduit}"
								var="lignePanier" varStatus="status">


								<div class="row m-0 mt-1 flex-row text-dark">

									<div class="col-12">
										<div class="row">
											<div class="col-3 text-center">${lignePanier.bijoux.nom_bijoux}</div>
											<div class="col-2 text-center">${lignePanier.bijoux.prix_bijoux}
												€</div>
											<div class="col-1 text-center">${lignePanier.quantite_lignepanier}</div>
											<div class="col-4 text-center">
												<div class="row">
													<form method="post" action="/LaPepite/user">
														<input type="hidden" name="dropOne"
															value=${lignePanier.bijoux.id_bijoux }> <input
															type="submit" value="-" class="col-md-6">
													</form>
													<form method="post" action="/LaPepite/user">
														<input type="hidden" name="addOne"
															value=${lignePanier.bijoux.id_bijoux }> <input
															type="submit" value="+" class="col-md-6">
													</form>
												</div>
											</div>
											<div class="col-2 text-center">${lignePanier.quantite_lignepanier * lignePanier.bijoux.prix_bijoux}
												€</div>

										</div>


									</div>
								</div>


								<hr>
							</c:forEach>
						</div>
						<div class="row">

								<div class="col-sm-10"></div>
								<div class="col-sm-2">

									<a href="${pageContext.servletContext.contextPath}/user/order"
										class="btn btn-primary">Commander</a>

								</div>


							</div>
					</div>


				</c:when>

			</c:choose>




		</div>

	</div>

	<%@ include file="footer/footer.jsp"%>


</body>
</html>



