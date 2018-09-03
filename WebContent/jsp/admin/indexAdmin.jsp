<%-- 
    Document   : indexAdmin
    Created on : 7 août 2018, 14:27:00
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="../link.jsp" %>
<title>Index - Administrateur</title>
</head>
<body>

	<%@include file="../menu/topMenu.jsp"%>
	<%@include file="../menu/adminMenu.jsp"%>


	<div class="container">
		<!-- Start Page Content -->
		<div class="row mt-4">
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-usd f-s-40 color-primary"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>568120</h2>
							<p class="m-b-0">Total Bijoux</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-shopping-cart f-s-40 color-success"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>1178</h2>
							<p class="m-b-0">Total Designer</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-archive f-s-40 color-warning"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>25</h2>
							<p class="m-b-0">Total Categorie</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span><i class="fa fa-user f-s-40 color-danger"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>847</h2>
							<p class="m-b-0">Nb commande</p>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
		<%@ include file="../footer/footer.jsp" %>
	

</body>
</html>
