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

		<a href="/LaPepite/admin/designers/add" class="mb-3 btn btn-success">Ajouter
			un designer</a>

		<h2 class="text-center">Liste des designers</h2>
		
		<div class="row flex-column border border-dark shadow-lg rounded">
			<div class="col-auto border border-dark m-2 shadow-sm rounded">
				<div class="row m-0 flex-row p-4 shadow-sm rounded">

					<div class="col-1 text-primary text-center">N°</div>
					<div class="col-2 text-primary text-center">Nom</div>
					<div class="col-5 text-primary text-center">Description</div>
					<div class="col-4 text-primary text-center">Actions</div>

				</div>

				<c:forEach items="${designersList}" var="designer"
					varStatus="status">


						<div class="row m-0 mt-1 flex-row text-dark">

							<div class="col-12">
								<div class="row">
									<div class="col-1 text-center">${designer.id_designer}</div>
									<div class="col-2 text-center">${designer.nom_designer}</div>
									<div class="col-5 text-center">${designer.description_designer}</div>
									
									<div class="col-4">
										<div class="row">
										<input type="button"
								class="btn btn-danger btn-send col-6" value="Supprimer"
								onClick="onDeleteDesigner('${designer.nom_designer}','${designer.id_designer}')">
										<a href="/LaPepite/admin/categories/edit?id=${designer.id_designer}"
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