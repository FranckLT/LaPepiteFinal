<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp" %>
<title>Admin Commandes Index</title>
</head>
<body>

<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>
	
	
	<div class="container mt-4">

		<h2>Liste des commandes</h2>

		</br>

		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th>Id</th>
						<th class="col-md-2"></th>
						<th>Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${commandesList}" var="commande" varStatus="status">
						<tr>
							<th>${commande.id_commande}
							</th>
							<td>${commande.nom_categorie}</td>
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
	
	

</body>
</html>