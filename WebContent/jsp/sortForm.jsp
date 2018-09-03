<script>
	function sort(){
		
		var listCategories = $(".categorieName"); 
		
		var listDesigners = $(".designerName");
		
		var selectedCategories = $("#categoriesSelect").select2("data");
		
		var selectedDesigners =  $("#designersSelect").select2("data");
		
		var listCategoriesSelected =[];
		
		var listDesignersSelected = [];
		
		listCategories
		.each(function(element) {
			listCategories[element].parentNode.parentNode.style.display = "block"
		});
		
		listDesigners
		.each(function(element) {
			listDesigners[element].parentNode.parentNode.style.display = "block"
		});
		
		for(j=0;j<selectedCategories.length;j++){
			const name = selectedCategories[j].text;
			listCategoriesSelected.push(name)
		}
		for(j=0;j<selectedDesigners.length;j++){
			const name = selectedDesigners[j].text;
			listDesignersSelected.push(name)
		}
		
		if(listCategoriesSelected.length>0){
			
			for(let i = 0; i<listCategories.length; i++){
				
				if(listCategoriesSelected.includes(listCategories[i].innerHTML)){
					
					listCategories[i].parentNode.parentNode.style.display = "block";
					
				} else {
					
					listCategories[i].parentNode.parentNode.style.display = "none";
					
				}
				
			}
			
		}
		
		if(listDesignersSelected.length>0){
			
			for(let i = 0; i<listDesigners.length; i++){
				
				if(listDesignersSelected.includes(listDesigners[i].innerHTML)){
					
					listDesigners[i].parentNode.parentNode.style.display = "block";
					
				} else {
					
					listDesigners[i].parentNode.parentNode.style.display = "none";
					
				}
				
			}
			
		}
		
		
		
	}
	
</script>

<script>

$(document).ready(function() {
    $('.js-example-basic-multiple').select2();
});
</script>