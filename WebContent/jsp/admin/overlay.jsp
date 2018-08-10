
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

    function off() {
        $('.overlay').css("display","none");
    }
</script>
