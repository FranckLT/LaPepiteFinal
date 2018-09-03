<%-- 
    Document   : login
    Created on : 26 juil. 2018, 14:24:13
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<%@include file="link.jsp" %>
<title>Login</title>
</head>
<body>

		<%@include file="menu/topMenu.jsp"%>




	<div
		class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">

		<h1 class="display-5">Login</h1>



	</div>
	
	

	<div class="container">
	
	<c:if test="${not empty errorMessage }">

		<div class="ht-tm-element alert alert-danger col-lg-6 col-md-12 offset-lg-3" role="alert">
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
								<form id="login-form" action="/LaPepite/login" method="post"
									role="form" style="display: block;">
									<div class="form-group">
										<input type="email" name="email" id="email" tabindex="1"
											class="form-control" placeholder="Email" value="">
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

								<a href="/LaPepite/register">Pas encore membre ?</a>

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
