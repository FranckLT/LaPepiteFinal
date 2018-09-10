<%-- 
    Document   : adminIndexBijoux
    Created on : 7 août 2018, 14:58:09
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../link.jsp" %>
<title>Admin - Index Bijoux</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>
	
	
	<div class="container mt-4">
	
		<a href="/LaPepite/admin/bijoux/add" class="btn btn-success mb-3">Ajouter un bijoux</a>

		<h2 class="col-12 text-center mb-3">Liste des bijoux</h2>

		<div class="row flex-column border border-dark shadow-lg rounded">
			<div class="col-auto border border-dark m-2 shadow-sm rounded">
				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-1 text-primary text-center">N° Bijoux</div>
					<div class="col-2 text-primary text-center">Nom Bijoux</div>
					<div class="col-1 text-primary text-center">Ref Bijoux</div>
					<div class="col-1 text-primary text-center">Prix Bijoux</div>
					<div class="col-2 text-primary text-center">Designer</div>
					<div class="col-2 text-primary text-center">Catégories</div>
					<div class="col-3 text-primary text-center">Actions</div>

				</div>

				<c:forEach items="${listBijoux}" var="bijoux"
					varStatus="status">


						<div class="row m-0 mt-1 flex-row text-dark">

							<div class="col-12">
								<div class="row">
									<div class="col-1 text-center">${bijoux.id_bijoux}</div>
									<div class="col-2 text-center">${bijoux.nom_bijoux}</div>
									<div class="col-1 text-center">${bijoux.ref_bijoux}</div>
									<div class="col-1 text-center">${bijoux.prix_bijoux}</div>
									<div class="col-2 text-center">${bijoux.designer.nom_designer}</div>
									<div class="col-2 text-center">${bijoux.categorie.nom_categorie}</div>
									
									<div class="col-3">
										<div class="row">
										<input type="button"
								class="btn btn-danger btn-send col-6" value="Supprimer"
								onClick="onDeleteBijoux('${bijoux.nom_bijoux}','${bijoux.id_bijoux}')">
								
										<a href="/LaPepite/admin/bijoux/edit?id=${bijoux.id_bijoux}"
								class="btn btn-primary col-6">Modifier</a>
								
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
