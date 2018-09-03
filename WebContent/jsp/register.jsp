<%-- 
    Document   : register
    Created on : 2 août 2018, 15:53:54
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp" %>
<title>Registering Page</title>

</head>
<body>


	<%@include file="menu/topMenu.jsp"%>



	<div
		class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

		<h1 class="display-5">Register</h1>


	</div>

	<div class="container">

		<c:if test="${not empty errorMessage }">

			<div
				class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3"
				role="alert">
				<h4 class="alert-heading text-center">Erreur</h4>
				<p class="text-center">${errorMessage }</p>
			</div>
		</c:if>

		<div class="row">
			<div class="col-md-6 col-md-offset-3 mx-auto text-center">
				<div class="panel panel-login">
					<div class="panel-heading">
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">

								<form id="register-form" action="/LaPepite/register"
									method="post" style="display: block;">

									<div class="form-group">
										<input type="text" name="nom" id="username" tabindex="1"
											class="form-control" placeholder="Nom" value="">
									</div>
									<div class="form-group">
										<input type="text" name="prenom" id="prenom" tabindex="1"
											class="form-control" placeholder="Prenom" value="">
									</div>
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1"
											class="form-control" placeholder="Email" value="">
									</div>
									<div class="form-group">
										<input type="text" name="adresse" id="adresse" tabindex="1"
											class="form-control" placeholder="Adresse Postale" value="">
									</div>
									<div class="form-group">
										<input type="password" name="password" id="password"
											tabindex="2" class="form-control" placeholder="Password">
									</div>

									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" id="login-submit" tabindex="4"
													class="form-control btn btn-primary">
											</div>
										</div>
									</div>

								</form>

								<a href="/LaPepite/login">Deja membre ?</a>

							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
			<%@ include file="footer/footer.jsp" %>
	
</body>
</html>
