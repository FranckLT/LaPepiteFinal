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
<title>Ajouter un Bijoux</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>



	<div class="container">

		<div class="card p-4 mt-4">

			<div class="card-title">

				<h2>Ajouter un produit</h2>

			</div>

			<c:choose>
				<c:when test="${not empty bijoux}">

					<form action="/LaPepite/admin/bijoux/edit?id=${bijoux.id_bijoux}"
						method="post" enctype="multipart/form-data">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" name="nomBijoux"
								value="${bijoux.nom_bijoux}">
						</div>
						<div class="form-group">
							<label for="inputRef">Ref : </label> <input type="text"
								class="form-control" id="inputName" name="refBijoux"
								value="${bijoux.ref_bijoux}">
						</div>
						<div class="form-group">
							<label for="inputPrix">Prix : </label> <input type="number"
								class="form-control" id="inputPrix" name="prixBijoux"
								value="${bijoux.prix_bijoux}">
						</div>
						<div class="form-group">
							<label for="inputPrix">Description</label> <input type="text"
								class="form-control" id="inputPrix" name="descriptionBijoux"
								value="${bijoux.description_bijoux}">
						</div>
						<div class="form-group">
							<label for="inputStock">Quantité en stock : </label> <input
								type="number" class="form-control" id="inputStock"
								name="stockBijoux" value="${bijoux.stock_bijoux}">
						</div>
						<div class="form-group">
							<label for="selectDesigner">Designer</label> <select
								class="form-control" id="selectDesigner" name="designerBijoux">
								<c:forEach items="${listDesigners}" var="designer">
									<c:choose>
										<c:when
											test="${designer.id_designer == bijoux.designer.id_designer }">
											<option value="${designer.id_designer}" selected="true">${designer.nom_designer}</option>
										</c:when>
										<c:when test="${designer.id_designer != bijoux.designer.id_designer }">
											<option value="${designer.id_designer}">${designer.nom_designer}</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="selectCategorie">Categorie</label> <select
								class="form-control" id="selectCategorie" name="categorieBijoux">
								<c:forEach items="${listCategories}" var="categorie">
									<c:choose>
										<c:when
											test="${categorie.id_categorie == bijoux.categorie.id_categorie }">
											<option value="${categorie.id_categorie}" selected="true">${categorie.nom_categorie}</option>
										</c:when>
										<c:when
											test="${categorie.id_categorie != bijoux.categorie.id_categorie }">
											<option value="${categorie.id_categorie}">${categorie.nom_categorie}</option>
										</c:when>
									</c:choose>
								</c:forEach>
							</select>
						</div>

						<!--  <fieldset>
							<legend>Chargement de l'image (Ne pas utiliser)</legend>
							<div class="form-group">
								<label for="description">Description du fichier : </label> <input
									type="text" id="description" name="description" value="" class="form-control"/>
							</div>
							<div class="form-group">
								<label for="fichier">Emplacement du fichier : </label> <input class="btn btn-outline-primary" type="file" id="fichier"
									name="fichier" />
							</div>

					
							<br />
						</fieldset>-->
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
				<c:when test="${empty bijoux }">

					<form action="/LaPepite/admin/bijoux/add" method="post">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" placeholder="Nom"
								name="nomBijoux">
						</div>
						<div class="form-group">
							<label for="inputRef">Ref : </label> <input type="text"
								class="form-control" id="inputRef" placeholder="Ref"
								name="refBijoux">
						</div>
						<div class="form-group">
							<label for="inputPrix">Prix : </label> <input type="number"
								class="form-control" id="inputPrix" placeholder="Prix"
								name="prixBijoux">
						</div>
						<div class="form-group">
							<label for="inputDescription">Description</label> <input type="text"
								class="form-control" id="inputDescription" placeholder="Description"
								name="descriptionBijoux">
						</div>
						<div class="form-group">
							<label for="inputStock">Quantité en stock : </label> <input
								type="number" class="form-control" id="inputStock"
								placeholder="Stock" name="stockBijoux">
						</div>
						<div class="form-group">
							<label for="selectDesigner">Designer</label> <select
								class="form-control" id="selectDesigner" name="designerBijoux">
								<c:forEach items="${listDesigners}" var="designer">
									<option value="${designer.id_designer}">${designer.nom_designer}</option>
								</c:forEach>
							</select>
						</div>
						<div class="form-group">
							<label for="selectCategorie">Categorie</label> <select
								class="form-control" id="selectCategorie" name="categorieBijoux">
								<c:forEach items="${listCategories}" var="categorie">
									<option value="${categorie.id_categorie }">${categorie.nom_categorie}</option>
								</c:forEach>
							</select>
						</div>

						<!-- 	<fieldset>
							<legend>Envoi de fichier</legend>

							<label for="description">Description du fichier</label> <input
								type="text" id="description" name="description" value="" /> <br />

							<label for="fichier">Emplacement du fichier <span
								class="requis">*</span></label> <input type="file" id="fichier"
								name="fichier" /> <br />

							
							<br />
						</fieldset>-->

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
			</c:choose>


		</div>
	</div>

</body>
</html>
