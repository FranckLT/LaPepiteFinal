<%-- 
    Document   : panier
    Created on : 3 août 2018, 11:19:01
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp" %>
<title>Votre panier</title>
</head>
<body>



	<%@ include file="menu/topMenu.jsp"%>



	<c:if test="${not empty listLignePanier}">
		<c:forEach items="${listLignePanier}" var="ligne">

			<div class="row">

				<div class="card-body">
					<h3 class="card-title">${ligne.bijoux.nom}</h3>
					<h4>${ligne.bijoux.prix}€</h4>
					<p class="card-text">${ligne.quantite}</p>
					<p>${ligne.bijoux.prix * ligne.quantite}</p>
					<div class="row justify-content-between"></div>
				</div>
			</div>

		</c:forEach>
	</c:if>


		<%@ include file="footer/footer.jsp" %>

</body>
</html>
