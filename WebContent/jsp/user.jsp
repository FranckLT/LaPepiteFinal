<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp" %>
<title>Mon compte</title>
</head>
<body>

	<%@include file="menu/topMenu.jsp"%>
	<%@include file="menu/userMenu.jsp"%>

	<div class="container">

		<div>


			<div class="row">



				<h1>Bienvenue ${sessionScope.utilisateur.prenom_utilisateur}
					${sessionScope.utilisateur.nom_utilisateur}</h1>

			</div>

			<div class="row">

				<h2>Panier</h2>

			</div>

			<div class="row">

				<div class="table-responsive">
					<table class="table table-striped table-sm">
						<thead>
							<tr>
								<th class="col">Bijoux</th>
								<th class="col">Prix</th>
								<th class="col">Quantite</th>
								<th class="col">Ajouter/Supprimer</th>

								<th class="col">Total</th>
							</tr>
						</thead>
						<tbody>

							<c:choose>
								<c:when
									test="${ empty sessionScope.utilisateur.panier.listProduit}">
									
							

								</c:when>
								<c:when
									test="${ not empty sessionScope.utilisateur.panier.listProduit}">

									<c:forEach
										items="${sessionScope.utilisateur.panier.listProduit}"
										var="lignePanier" varStatus="status">
										<tr>
											<td>${lignePanier.bijoux.nom_bijoux}</td>
											<td>${lignePanier.bijoux.prix_bijoux}€</td>
											<td>${lignePanier.quantite_lignepanier}</td>
											<td>
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
											</td>

											<td> <div class="totalLigne">${lignePanier.quantite_lignepanier * lignePanier.bijoux.prix_bijoux}€</div></td>


										</tr>
									</c:forEach>

								</c:when>

							</c:choose>

						</tbody>
					</table>
					
					<div class="row">
						
						<div class="col-sm-10"></div>
						<div class="col-sm-2">
						
						<a href="${pageContext.servletContext.contextPath}/user/order" class="btn btn-primary">Commander</a>
						
						</div>
						
					
					</div>
					
				</div>
			</div>


		</div>

	</div>

		<%@ include file="footer/footer.jsp" %>
	

</body>
</html>



