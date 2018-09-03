<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp" %>
<title>Designer Index</title>
</head>
<body>


	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>
	
	
	<div class="container mt-4">

		<a href="/LaPepite/admin/designers/add" class="m-5 btn btn-success">Ajouter
			un designer</a>

		<h2>Liste des designers existants</h2>

		</br>

		<div class="table-responsive">
			<table class="table table-striped table-sm">
				<thead>
					<tr>
						<th >Id</th>
						<th >Nom</th>
						<th class="col-md-2">Description</th>
						<th >Action</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${designersList}" var="designer" varStatus="status">
						<tr>
							<th>${designer.id_designer}
							</th>
							<td>${designer.nom_designer}</td>
							<td>${designer.description_designer}</td>
							<td class="col-md-4"><input type="button"
								class="btn btn-danger btn-send" value="Supprimer"
								onClick="onDeleteDesigner('${designer.nom_designer}','${designer.id_designer}')">
								<a href="/LaPepite/admin/designers/edit?id=${designer.id_designer}"
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