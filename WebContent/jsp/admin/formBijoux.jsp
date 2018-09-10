<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../link.jsp"%>
<title>Add / Edit Bijoux</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>



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

				<h2>Ajouter un produit</h2>

			</div>

			<c:choose>
				<c:when test="${not empty bijoux}">

					<form action="/LaPepite/admin/bijoux/edit?id=${bijoux.id_bijoux}"
						method="post">
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
								step="any" class="form-control" id="inputPrix" name="prixBijoux"
								value="${bijoux.prix_bijoux}">
						</div>
						<div class="form-group">
							<label for="inputDescription">Description</label> <input
								type="text" class="form-control" id="inputDescription"
								name="descriptionBijoux" value="${bijoux.description_bijoux}">
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
										<c:when
											test="${designer.id_designer != bijoux.designer.id_designer }">
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
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
				<c:when test="${empty bijoux }">

					<form action="/LaPepite/admin/bijoux/add" method="post"
						enctype="multipart/form-data">
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
								step="0.01" class="form-control" id="inputPrix"
								placeholder="Prix" name="prixBijoux">
						</div>
						<div class="form-group">
							<label for="inputDescription">Description</label> <input
								type="text" class="form-control" id="inputDescription"
								placeholder="Description" name="descriptionBijoux">
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

						<div class="custom-file">
							<input type="file" name="fichier" class="custom-file-input" id="customFile">
							<label class="custom-file-label" for="customFile">Choose
								file</label>
						</div>
						
						<hr>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
			</c:choose>


		</div>
	</div>

	<%@ include file="../footer/footer.jsp"%>

</body>
</html>
