
<script type="text/javascript">
    
    function onDeleteBijoux(nom, code) {
        $("<div id="
                +code
                +" class='overlay' onclick='off()'><div id='text'>Voulez vous vraiment supprimer le bijoux "
                +nom
                +" ?</div><form id='contact-form' method='post' action='/LaPepite/admin/bijoux' role='form'><input type='hidden' name='idToDelete' value="
                +code
                +"><input id='submitButton' type='submit' class='btn btn-danger btn-send col-md-2' value='OUI' onclick='off()'></form></div>").appendTo("body");
        document.getElementById(code).style.display = "block";
    }
    
    function onDeleteCategorie(nom, code) {
        $("<div id="
                +code
                +" class='overlay' onclick='off()'><div id='text'>Voulez vous vraiment supprimer la categories "
                +nom
                +" ?</div><form id='contact-form' method='post' action='/LaPepite/admin/categories' role='form'><input type='hidden' name='idToDelete' value="
                +code
                +"><input id='submitButton' type='submit' class='btn btn-danger btn-send col-md-2' value='OUI' onclick='off()'></form></div>").appendTo("body");
        document.getElementById(code).style.display = "block";
    }
    
    function onDeleteDesigner(nom, code) {
        $("<div id="
                +code
                +" class='overlay' onclick='off()'><div id='text'>Voulez vous vraiment supprimer le Designer "
                +nom
                +" ?</div><form id='contact-form' method='post' action='/LaPepite/admin/designers' role='form'><input type='hidden' name='idToDelete' value="
                +code
                +"><input id='submitButton' type='submit' class='btn btn-danger btn-send col-md-2' value='OUI' onclick='off()'></form></div>").appendTo("body");
        document.getElementById(code).style.display = "block";
    }
    
    function onDeleteUser(nom, prenom, code) {
        $("<div id="
                +code
                +" class='overlay' onclick='off()'><div id='text'>Voulez vous vraiment supprimer  "
                +nom+ prenom
                +" ?</div><form id='contact-form' method='post' action='/LaPepite/admin/users' role='form'><input type='hidden' name='idToDelete' value="
                +code
                +"><input id='submitButton' type='submit' class='btn btn-danger btn-send col-md-2' value='OUI' onclick='off()'></form></div>").appendTo("body");
        document.getElementById(code).style.display = "block";
    }
    
    function onAdminUser(nom, prenom, code) {
        $("<div id="
                +code
                +" class='overlay' onclick='off()'><div id='text'>Voulez vous vraiment passer Administrateur  "
                +nom+ prenom
                +" ?</div><form id='contact-form' method='post' action='/LaPepite/admin/users' role='form'><input type='hidden' name='id' value="
                +code
                +"><input id='submitButton' type='submit' class='btn btn-danger btn-send col-md-2' value='OUI' onclick='off()'></form></div>").appendTo("body");
        document.getElementById(code).style.display = "block";
    }
    

    function off() {
        $('.overlay').css("display","none");
    }
</script>
