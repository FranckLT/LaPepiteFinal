<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="topMenu">
			<nav class="col-12">
				<ul>
					<li class="nav-item bijoux col-md-2"><a href="/LaPepite/shop">Bijoux</a></li>
					<li class="nav-item createur col-md-2"><a href="/LaPepite/designers">Cr&eacute;ateur</a>
					</li>
					<li class=" nav-item img col-md-4"><a href="/LaPepite/home"><img
							src="/LaPepite/image/logoPepite.jpg" alt="Logo de LaPepite"></a></li>
					<li class="col-md-2"><a href="/LaPepite/contact">Contact</a></li>

					<c:choose>
						<c:when test="${not empty sessionScope.utilisateur}">

							<c:choose>
								<c:when test="${sessionScope.utilisateur.admin == true}">
									<li class="nav-item dropdown col-md-2 mt-2"><a
										class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
										role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> ${sessionScope.utilisateur.nom_utilisateur} </a>
										<div class="dropdown-menu" aria-labelledby="navbarDropdown">
											<a class="dropdown-item" href="/LaPepite/admin"><i
												class="fas fa-user-cog"></i> Espace Admin</a> <a
												class="dropdown-item" href="/LaPepite/user/logout"><i
												class="fas fa-sign-out-alt"></i> Deconnexion</a>
										</div></li>
								</c:when>
								<c:otherwise>
									<li class="nav-item dropdown col-md-2 mt-2"><a
										class="nav-link dropdown-toggle" href="#" id="navbarDropdown"
										role="button" data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false"> ${sessionScope.utilisateur.nom_utilisateur} </a>
										<div class="dropdown-menu" aria-labelledby="navbarDropdown">
											<a class="dropdown-item" href="/LaPepite/user"><i
												class="fa fa-shopping-cart"></i> ${sessionScope.utilisateur.panier.total_panier} &euro;</a>  <a
												class="dropdown-item" href="/LaPepite/user/logout"><i
												class="fas fa-sign-out-alt"></i> Deconnexion</a>
										</div></li>
								</c:otherwise>
							</c:choose>

						</c:when>
						<c:otherwise>
							<li class="col-md-2"><a href="/LaPepite/login"><i
									class="fa fa-user"></i></a></li>
						</c:otherwise>


					</c:choose>

				</ul>
			</nav>
		</div>
	</div>
</div>

