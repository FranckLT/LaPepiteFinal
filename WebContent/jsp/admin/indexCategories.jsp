<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp" %>
<title>Categories index</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>
	
	
	
	<div class="container mt-4">
	
		<a href="/LaPepite/admin/categories/add" class="btn btn-success mb-3">Ajouter une catégorie</a>

		<h2 class="col-12 text-center mb-3">Liste des catégories</h2>

		<div class="row flex-column border border-dark shadow-lg rounded">
			<div class="col-auto border border-dark m-2 shadow-sm rounded">
				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-4 text-primary text-center">N° catégorie</div>
					<div class="col-4 text-primary text-center">Nom Catégorie</div>
					<div class="col-4 text-primary text-center">Actions</div>

				</div>

				<c:forEach items="${categoriesList}" var="categorie"
					varStatus="status">


						<div class="row m-0 mt-1 flex-row text-dark">

							<div class="col-12">
								<div class="row">
									<div class="col-4 text-center">${categorie.id_categorie}</div>
									<div class="col-4 text-center">${categorie.nom_categorie}</div>
									
									<div class="col-4">
										<div class="row">
										<input type="button"
								class="btn btn-danger btn-send col-6" value="Supprimer"
								onClick="onDeleteCategorie('${categorie.nom_categorie}','${categorie.id_categorie}')">
										<a href="/LaPepite/admin/categories/edit?id=${categorie.id_categorie}"
								class="btn btn-primary btn-send col-6">Modifier</a>
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
	
		<%@ include file="../footer/footer.jsp" %>
	
		<%@ include file="overlay.jsp"%>
	

</body>
</html>