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

		<a href="/LaPepite/admin/bijoux/add" class="m-5 btn btn-success">Ajouter
			un bijoux</a>

		<h2>Liste des bijoux existants</h2>

	

		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th class="col">Nom</th>
						<th >Reférence</th>
						<th >Prix</th>
						<th >Designer</th>
						<th >Categorie</th>
						<th >Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listBijoux}" var="bijoux" varStatus="status">
						<tr>
							<th>${bijoux.nom_bijoux}
							</th>
							<td>${bijoux.ref_bijoux}</td>
							<td>${bijoux.prix_bijoux}€</td>
							<td>${bijoux.designer.nom_designer}</td>
							<td>${bijoux.categorie.nom_categorie}</td>
							<td class="col-md-4"><input type="button"
								class="btn btn-danger btn-send" value="Supprimer"
								onClick="onDeleteBijoux('${bijoux.nom_bijoux}','${bijoux.id_bijoux}')">
								<a href="/LaPepite/admin/bijoux/edit?id=${bijoux.id_bijoux}"
								class="btn btn-primary btn-send">Modifier</a></td>

						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<%@ include file="../footer/footer.jsp" %>

	<%@ include file="overlay.jsp"%>


</body>
</html>
