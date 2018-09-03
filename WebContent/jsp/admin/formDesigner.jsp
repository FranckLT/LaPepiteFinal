<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp" %>
<title>Add / Edit Designer</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>



	<div class="container">
	<c:if test="${not empty errorMessage }">

		<div class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3" role="alert">
			<h4 class="alert-heading text-center">Erreur</h4>
			<p class="text-center">${errorMessage }</p>
		</div>
	</c:if>

		<div class="card p-4 mt-4">

			<div class="card-title">

				<h2>Ajouter/Modifier un Designer</h2>

			</div>

			<c:choose>
				<c:when test="${not empty designer}">

					<form
						action="/LaPepite/admin/designers/edit?id=${designer.id_designer}"
						method="post">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" name="nomDesigner"
								value="${designer.nom_designer}">
						</div>
						<div class="form-group">
							<label for="inputNom">Description : </label> <input type="text"
								class="form-control" id="inputNom" name="descriptionDesigner"
								value="${designer.description_designer}">
						</div>

						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
				<c:when test="${empty designer }">

					<form action="/LaPepite/admin/designers/add" method="post">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" placeholder="Nom"
								name="nomDesigner">
								<div class="form-group">
							<label for="inputNom">Description : </label> <input type="text"
								class="form-control" id="inputNom" name="descriptionDesigner">
						</div>

							<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
			</c:choose>


		</div>
	</div>
	
		<%@ include file="../footer/footer.jsp" %>
	


</body>
</html>