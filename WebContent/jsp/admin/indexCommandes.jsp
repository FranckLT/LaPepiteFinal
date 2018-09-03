<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp"%>
<title>Admin - Commandes Index</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>

	<div class="container mt-4">

		<h2>Liste des commandes</h2>

		<div class="row flex-column border border-dark shadow-lg rounded">
			<div class="col-auto border border-dark m-2 shadow-sm rounded">
				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-2 text-primary text-center">N° commande</div>
					<div class="col-2 text-primary text-center">Date</div>
					<div class="col-2 text-primary text-center">N° Client</div>
					<div class="col-3 text-primary text-center">Nom Client</div>
					<div class="col-1 text-primary text-center">HT</div>
					<div class="col-1 text-primary text-center">TVA</div>
					<div class="col-1 text-primary text-center">TTC</div>

				</div>

				<c:forEach items="${commandesList}" var="commande"
					varStatus="status">

					<a
						href="/LaPepite/admin/commandes/commande?id=${commande.id_commande }">

						<div class="row m-0 mt-1 flex-row h-auto text-dark">

							<div class="col-12">
								<div class="row">
									<div class="col-2 text-center">${commande.id_commande}</div>
									<div class="col-2 text-center">${commande.date_commande}</div>
									<div class="col-2 text-center">${commande.utilisateur.id_utilisateur}</div>
									<div class="col-3 text-center">${commande.utilisateur.nom_utilisateur}
										${commande.utilisateur.prenom_utilisateur}</div>
									<div class="col-1 text-center">${commande.totalHT_commande}
										€</div>
									<div class="col-1 text-center">${commande.TVA_commande}€</div>
									<div class="col-1 text-center">${commande.totalTTC_commande}
										€</div>


								</div>
							</div>
						</div>

						<hr>
					</a>
				</c:forEach>
			</div>
		</div>
	</div>

	<%@ include file="../footer/footer.jsp"%>

</body>
</html>