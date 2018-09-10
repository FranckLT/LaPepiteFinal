<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="../link.jsp"%>
<title>Admin - User Index</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>
	
	
	
	<div class="container mt-4">
	
	<c:if test="${not empty errorMessage }">

		<div class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3" role="alert">
			<h4 class="alert-heading text-center">Erreur</h4>
			<p class="text-center">${errorMessage }</p>
		</div>
	</c:if>

		<h2 class="text-center">Liste des utilisateurs</h2>

		<div class="row flex-column border border-dark shadow-lg rounded">
			<div class="col-auto border border-dark m-2 shadow-sm rounded">
				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-1 text-primary text-center">N° Utilisateur</div>
					<div class="col-2 text-primary text-center">Nom </div>
					<div class="col-2 text-primary text-center">Prénom</div>
					<div class="col-2 text-primary text-center">Email</div>
					<div class="col-2 text-primary text-center">Adresse</div>
					<div class="col-1 text-primary text-center">Admin</div>
					<div class="col-2 text-primary text-center">Actions</div>
				</div>

				<c:forEach items="${utilisateursList}" var="utilisateur"
					varStatus="status">

						<div class="row m-0 mt-1 flex-row h-auto text-dark">

							<div class="col-12">
								<div class="row">
									<div class="col-1 text-center">${utilisateur.id_utilisateur}</div>
									<div class="col-2 text-center">${utilisateur.nom_utilisateur}</div>
									<div class="col-2 text-center">${utilisateur.prenom_utilisateur}</div>
									<div class="col-2 text-center">${utilisateur.mail_utilisateur}</div>
									<div class="col-2 text-center">${utilisateur.adresse_utilisateur}</div>
									<div class="col-1 text-center">${utilisateur.admin}</div>
									<div class="col-2">
										<div class="row">
										<input type="button"
								class="btn btn-primary btn-send col-12" value="Admin"
								onClick="onAdminUser('${utilisateur.nom_utilisateur}','${utilisateur.prenom_utilisateur}','${utilisateur.id_utilisateur }')">
										</div>	
									</div>
								</div>
							</div>
						</div>

						<hr>
				</c:forEach>
			</div>
		</div>
	</div>

	<%@ include file="../footer/footer.jsp"%>
	<%@ include file="overlay.jsp" %>

</body>
</html>