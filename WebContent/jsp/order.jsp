
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="link.jsp" %>
<title>Valider la commande</title>
</head>
<body>

	<%@include file="menu/topMenu.jsp"%>

	<div class="container-fluid p-5">

		<div class="row flex-column border border-dark shadow-lg rounded">

			<h1 class="col-md-12 m-2 mb-5">Votre commande</h1>

			<div class="border border-dark m-2 shadow-sm rounded">

				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-2 text-primary">Nom bijoux</div>
					<div class="col-1 text-primary"></div>
					<div class="col-3 text-primary text-right">P.U</div>
					<div class="col-3 text-primary text-right">Qte</div>
					<div class="col-3 text-primary text-right">Total</div>

				</div>

				<c:forEach items="${sessionScope.utilisateur.panier.listProduit}"
					var="lignePanier" varStatus="status">


					<div class="row m-0 mt-1 flex-row h-auto">

						<div class="col-12" style="height: 80px">



							<div class="row">

								<div class="col-2 h-30">${lignePanier.bijoux.nom_bijoux}</div>
								<div class="col-1">
									<img class="img-thumbnail h-auto"
										src="/LaPepite/image/bijoux/${lignePanier.bijoux.image_bijoux}">
								</div>
								<div class="col-3 text-right">${lignePanier.bijoux.prix_bijoux}€</div>
								<div class="col-3 text-right">${lignePanier.quantite_lignepanier}</div>
								<div class="col-3 text-right">${lignePanier.quantite_lignepanier * lignePanier.bijoux.prix_bijoux}
									€</div>


							</div>
						</div>
					</div>

				</c:forEach>
			</div>


			<div
				class="col-lg-2 offset-lg-8 col-md-11 offset-md-1 m-md-auto shadow-lg rounded border border-dark ">

				<div class="row mt-2">
					<div class="col-6 text-center">
						<p>HT</p>
					</div>
					<div class="col-6 text-right">
						<p>${sessionScope.utilisateur.panier.total_panier}€</p>
					</div>

				</div>
				<div class="row">
					<div class="col-6 text-center">
						<p>TVA (20%)</p>
					</div>
					<div class="col-6 text-right">
						<p>${sessionScope.utilisateur.panier.tva_panier}€</p>
					</div>
				</div>
				<div class="row">
					<div class="col-6 text-center">
						<p>TTC</p>
					</div>
					<div class="col-6 text-right">
						<p>${sessionScope.utilisateur.panier.total_panier + sessionScope.utilisateur.panier.tva_panier}€</p>
					</div>
				</div>


			</div>

		</div>

		<div class="row mt-5">

			<div class="col-lg-5 col-md-12">
				<div class="row m-2">
					<h3>Livraison</h3>
				</div>
				<div class="row m-2">
					<p>Nous vous informons que pour le moment seul le retrait en
						magasin est disponible.</p>
				</div>
			</div>

			<div class="col-2 offset-5">

				<div class="row">

					<form method="post" action="/LaPepite/user/order">
						<input type="submit" value="Valider la commande"
							class="btn btn-outline-dark">
					</form>


				</div>
			</div>

		</div>



	</div>

		<%@ include file="footer/footer.jsp" %>




</body>
</html>