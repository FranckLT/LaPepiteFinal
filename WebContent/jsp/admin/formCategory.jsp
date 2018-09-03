<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../link.jsp" %>
<title>Add / Edit Category</title>
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

				<h2>Ajouter/Modifier une categorie</h2>

			</div>

			<c:choose>
				<c:when test="${not empty categorie}">

					<form action="/LaPepite/admin/categories/edit?id=${categorie.id_categorie}"
						method="post">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" name="nomCategorie"
								value="${categorie.nom_categorie}">
						</div>
											
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:when>
				<c:otherwise>

					<form action="/LaPepite/admin/categories/add" method="post">
						<div class="form-group">
							<label for="inputNom">Nom : </label> <input type="text"
								class="form-control" id="inputNom" placeholder="Nom"
								name="nomCategorie">
					
						<button type="submit" class="btn btn-primary">Submit</button>
					</form>

				</c:otherwise>
			</c:choose>


		</div>
	</div>
	
		<%@ include file="../footer/footer.jsp" %>
	


</body>
</html>