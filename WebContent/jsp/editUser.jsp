<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ include file="link.jsp"%>

<title>${sessionScope.utilisateur.nom_utilisateur}
	${sessionScope.utilisateur.prenom_utilisateur} - Edit Account</title>
</head>
<body>

	<%@include file="menu/topMenu.jsp"%>
	<%@include file="menu/userMenu.jsp"%>

	<div class="container">

		<c:if test="${not empty errorMessage }">

			<div
				class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3"
				role="alert">
				<h4 class="alert-heading text-center">Erreur</h4>
				<p class="text-center">${errorMessage }</p>
			</div>
		</c:if>

		<div class="card p-4 mt-4">

			<div class="card-title">

				<h2>Modifier mon compte</h2>

			</div>

			<form action="/LaPepite/user/edit" method="post">
				<div class="form-group">
					<label for="inputNom">Nom : </label> <input type="text"
						class="form-control" id="inputNom" name="nomUtilisateur"
						value="${sessionScope.utilisateur.nom_utilisateur}">
				</div>
				<div class="form-group">
					<label for="inputPrenom">Prenom : </label> <input type="text"
						class="form-control" id="inputPrenom" name="prenomUtilisateur"
						value="${sessionScope.utilisateur.prenom_utilisateur}">
				</div>
				<div class="form-group">
					<label for="inputAdresse">Adresse : </label> <input type="text"
						class="form-control" id="inputAdresse" name="adresseUtilisateur"
						value="${sessionScope.utilisateur.adresse_utilisateur}">
				</div>
				<div class="form-group">
					<label for="inputMail">Email :</label> <input type="email"
						class="form-control" id="inputMail" name="mailUtilisateur"
						value="${sessionScope.utilisateur.mail_utilisateur}">
				</div>
				<div class="form-group">
					<label for="inputPassword1">Ancien Password : </label> <input
						type="password" class="form-control" id="inputOldPassword"
						name="passwordUtilisateur" value="">
				</div>
				<div class="form-group">
					<label for="inputNewPassword1">Nouveau Password : </label> <input
						type="password" class="form-control" id="inputNewPassword1"
						name="newpassword1" value="">
				</div>
				<div class="form-group">
					<label for="inputNewPassword2">Nouveau Password : </label> <input
						type="password" class="form-control" id="inputNewPassword2"
						name="newpassword2" value="">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>


		</div>
	</div>

	<%@ include file="footer/footer.jsp"%>

</body>
</html>