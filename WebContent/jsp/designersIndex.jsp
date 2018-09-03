<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="link.jsp" %>
</head>
<body>

	<%@ include file="menu/topMenu.jsp"%>

	<div class="container">

		<div class="row offset-2 jewel">

			<c:forEach items="${ designersList }" var="designer">



				<div class="card h-10 col-8 m-4 jewelDiv">
					<a href="designers/designer?id=${designer.id_designer }">

						<div class="card-body">

							<h4 class="card-title col-12">

								<p class="text-center">${designer.nom_designer}</p>

							</h4>

							<p class="card-text designerName text-muted">${ designer.description_designer }</p>

						</div>

					</a>
				</div>


			</c:forEach>

		</div>

	</div>
		<%@ include file="footer/footer.jsp" %>


</body>
</html>