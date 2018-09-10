<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="link.jsp"%>
<title>Commande n° ${commande.id_commande}</title>
</head>
<body>

	<%@include file="menu/topMenu.jsp"%>
	<%@include file="menu/userMenu.jsp"%>

	<div class="container">

		<div class="row flex-column border border-dark shadow-lg rounded">

			<h1 class="col-md-12 m-2 mb-5">Commande n°
				${commande.id_commande}</h1>
				<h4 class="col-12 m-2 mb-3"> Date : ${commande.date_commande}</h4>

			<div class="border border-dark m-2 shadow-sm rounded">

				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-3 text-primary">Nom bijoux</div>
					<div class="col-3 text-primary">Ref bijoux</div>
					<div class="col-1 text-primary text-right">Qte</div>
					<div class="col-2 text-primary text-right">P.U</div>
					<div class="col-3 text-primary text-right">Total</div>

				</div>

				<c:forEach items="${ligneCommandesList}" var="ligneCommande"
					varStatus="status">


					<div class="row m-0 mt-1 flex-row h-auto">

						<div class="col-12" style="height: 80px">



							<div class="row">

								<div class="col-3 h-30">${ligneCommande.bijoux.nom_bijoux}</div>
								<div class="col-3">${ligneCommande.bijoux.ref_bijoux}</div>
								<div class="col-1 text-right">${ligneCommande.quantite_lignecommande}</div>
								<div class="col-2 text-right">${ligneCommande.bijoux.prix_bijoux}
									€</div>
								<div class="col-3 text-right">${ligneCommande.quantite_lignecommande * ligneCommande.bijoux.prix_bijoux}
									€</div>


							</div>
						</div>
					</div>

				</c:forEach>
			</div>


			<div
				class="offset-md-8 col-auto mr-lg-2 mb-lg-2 m-md-2 shadow-lg rounded border border-dark ">

				<div class="row mt-2">
					<div class="col-6 text-center">
						<p>HT</p>
					</div>
					<div class="col-6 text-right">
						<p>${commande.totalHT_commande}€</p>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-6 text-center">
						<p>TVA (20%)</p>
					</div>
					<div class="col-6 text-right">
						<p>${commande.TVA_commande}€</p>
					</div>
				</div>
				<hr>
				<div class="row">
					<div class="col-6 text-center">
						<p>TTC</p>
					</div>
					<div class="col-6 text-right">
						<p>${commande.totalTTC_commande}€</p>
					</div>
				</div>


			</div>
		</div>

	</div>

	<%@ include file="footer/footer.jsp"%>


</body>
</html>