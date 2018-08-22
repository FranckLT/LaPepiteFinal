<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
	integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
	integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
	crossorigin="anonymous"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
	integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
	crossorigin="anonymous"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
<link
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
	crossorigin="anonymous">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link href="/LaPepite/css/topMenu.css" rel="stylesheet">
<link href="/LaPepite/css/overlay.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<link href="/LaPepite/css/lapepite.css" rel="stylesheet">
<link href="/LaPepite/css/topMenu.css" rel="stylesheet">
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

											<td>${lignePanier.quantite_lignepanier * lignePanier.bijoux.prix_bijoux}€</td>


										</tr>
									</c:forEach>

								</c:when>

							</c:choose>

						</tbody>
					</table>
				</div>
			</div>


		</div>

	</div>

	</div>

	</div>

</body>
</html>



