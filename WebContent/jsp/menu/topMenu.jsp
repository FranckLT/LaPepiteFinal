<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<div class="container">
	<div class="row">
		<div class="topMenu">
			<nav class="col-12">
				<ul>
					<li class="bijoux col-md-2"><a href="/LaPepite/shop">Bijoux</a></li>
					<li class="createur col-md-2"><a href="/LaPepite/designers">Cr&eacute;ateur</a>
						</li>
					<li class="img col-md-4"><a href="/LaPepite/home"><img
							src="/LaPepite/image/logoPepite.jpg" alt="Logo de LaPepite"></a></li>
					<li class="col-md-2"><a href="#">Contact</a></li>

					<c:choose>
					<c:when test="${not empty sessionScope.utilisateur}">
					
							<c:choose>
								<c:when test="${sessionScope.utilisateur.admin == true}">
									<li class="col-md-2"><a href="/LaPepite/admin"><i
										class="fas fa-user-cog"></i> ${sessionScope.utilisateur.nom_utilisateur}</a></li>
								</c:when>
								<c:otherwise>
									<li class="col-md-2"><a href="/LaPepite/user"><i
										class="fa fa-shopping-cart"></i> ${sessionScope.utilisateur.panier.total_panier} &euro;</a></li>
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

