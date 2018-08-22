<%-- 
    Document   : adminIndexBijoux
    Created on : 7 août 2018, 14:58:09
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

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
<link href="/LaPepite/css/overlay.css" rel="stylesheet">
<link
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css"
	rel="stylesheet" />
<title>Admin - Index Bijoux</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>



	<div class="container mt-4">

		<a href="/LaPepite/admin/bijoux/add" class="m-5 btn btn-success">Ajouter
			un bijoux</a>

		<h2>Liste des bijoux existants</h2>

		</br>

		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th class="col-md-2">Nom</th>
						<th class="col-md-2">Ref</th>
						<th class="col-md-2">Prix</th>
						<th class="col-md-2">Designer</th>
						<th class="col-md-2">Categorie</th>
						<th class="col-md-2">Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${listBijoux}" var="bijoux" varStatus="status">
						<tr>
							<th>${bijoux.nom_bijoux}
							</td>
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

	<%@ include file="overlay.jsp"%>


</body>
</html>
