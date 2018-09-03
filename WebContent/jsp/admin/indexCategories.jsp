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

		<a href="/LaPepite/admin/categories/add" class="m-5 btn btn-success">Ajouter
			une categorie</a>

		<h2>Liste des categories existants</h2>

		</br>

		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>Id</th>
						<th class="col-md-2">Nom</th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${categoriesList}" var="categorie" varStatus="status">
						<tr>
							<th>${categorie.id_categorie}
							</td>
							<td>${categorie.nom_categorie}</td>
							<td class="col-md-4"><input type="button"
								class="btn btn-danger btn-send" value="Supprimer"
								onClick="onDeleteCategorie('${categorie.nom_categorie}','${categorie.id_categorie}')">
								<a href="/LaPepite/admin/categories/edit?id=${categorie.id_categorie}"
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