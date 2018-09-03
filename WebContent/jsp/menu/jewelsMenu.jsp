<div class="row">
	<div class="col-lg-3 col-md-5 col-sd-12 offset-lg-2">
		<label for="rooms" class="align-middle col-12">Catégories : </label> <select
			id="categoriesSelect" class="js-example-basic-multiple col-5"
			name="rooms" multiple="multiple">
			<c:forEach items="${categoriesList}" var="categorie"
				varStatus="status">
				<option value="${categorie.id_categorie }">${categorie.nom_categorie}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-lg-3 col-md-5 col-sd-12">
		<label for="rooms" class="align-middle col-12">Designer : </label> <select
			id="designersSelect" class="js-example-basic-multiple col-5"
			name="category" multiple="multiple">
			<c:forEach items="${designersList}" var="designer" varStatus="status">
				<option value="${designer.id_designer }">${designer.nom_designer}</option>
			</c:forEach>
		</select>
	</div>
	<div class="col-lg-2 col-md-1 col-sd-12">
		<button class="btn btn-primary px-4 mt-4" onClick="sort()">Sort</button>
	</div>
</div>