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
							<span class="col-12"><i class="fas fa-chess-queen f-s-40 color-primary"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>${bijouxNumber}</h2>
							<p class="m-b-0">Nb Bijoux</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span class="col-12"><i class="fas fa-user-ninja f-s-40 color-success"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>${designersNumber}</h2>
							<p class="m-b-0">Nb Designer</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span class="col-12"><i class="fa fa-archive f-s-40 color-warning"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>${categoriesNumber}</h2>
							<p class="m-b-0">Nb Categorie</p>
						</div>
					</div>
				</div>
			</div>
			<div class="col-md-3">
				<div class="card p-30">
					<div class="media">
						<div class="media-left meida media-middle">
							<span class="col-12"><i class="fas fa-file-invoice-dollar f-s-40 color-danger"></i></span>
						</div>
						<div class="media-body media-text-right">
							<h2>${commandesNumber}</h2>
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
