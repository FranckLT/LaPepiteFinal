<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,300,700" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css"/>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous"/>
        <link rel="stylesheet" href="./css/topMenu.css" type="text/css"/>
        <link rel="stylesheet" href="./css/lapepite.css" type="text/css"/>
        <title>La Pepite - Acceuil</title>
    </head>
    <body>
       
        
        <!--Top menu-->
        
        
        <header>
            
        <div class="background">
            <div class="container">
                <div class="header">
                    <%@ include file="menu/topMenu.jsp"  %>
                    <div class="row">
                        <div class="shopButton col 1 center-block">
                            <a href="/LaPepite/shop">E-SHOP</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </header>
        
        <!--Caroussel-->
        
        
        <div class="row justify-content-center m-5">
            <div id="carouselImage" class="carousel slide col-7" data-ride="carousel">
                <div class="carousel-inner">
                    <div class="carousel-item active">
                        <img class="d-block w-100" src="./image/foot.jpg" alt="foot">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="./image/breakfast.jpg" alt="breakfast">
                    </div>
                    <div class="carousel-item">
                        <img class="d-block w-100" src="./image/pineapple.jpg" alt="pineapple">
                    </div>
                </div>
            </div>
                           
            <!-- test -->
            
            
            <div class="socialNetwork col 2">
                <div class="row topRow col 12">
                    <div class="leftBlock col 6"><h2>TopLeftBlock</h2></div>
                                                    
                    <div class="rightBlock col 6"><h2>TopRightBlock</h2></div>
                </div>
                <div class=" row bottomRow col 12">
                    <div class="leftBlock col 6 "><h2>BottomLeftBlock</h2></div>
                    <div class="rightBlock col 6"><h2>BottomRightBlock</h2></div>
                </div>
            </div>
        </div>
                            
        <div>
            <p>
            </p>
        </div>
                            
    <!--Footer-->      
    
    
    <footer id="site-footer">
        <div class="container">
            <div id="get-in-touch">
                <h5>Qui sommes nous ?</h5>
                <p>Petite boutique Toulousaine qui souhaite vous faire découvrir des créateurs français. Vous trouverez des bijoux en argent et plaqué or. A vous de découvrir !</p>
            </div>
            <div id="stay-in-touch">
                <h5>Newsletter</h5>
                <form action="">
                    <input type="text" name="email" id="email-field" placeholder="Email">
                    <button type="submit" name="button">
                        <i class="fa fa-check"></i>
                    </button>
                </form>
            </div>
            <div class="ressources">
                <ul>
                    <li>
                        <a href="#">A propos</a>
                    </li>
                    <li>
                        <a href="#">Mentions légales</a>
                    </li>
                </ul>
            </div>
            <div class="ressources">
                <ul>
                    <li>
                        <a href="#">Modes de livraison</a>
                    </li>
                    <li>
                        <a href="#">Moyens de paiement</a>
                    </li>
                </ul>
            </div>
            <div class="ressources">
                <ul>
                    <li>
                        <a href="#">Conditions Générales de Ventes</a>
                    </li>
                    <li>
                        <a href="#">Nous contacter</a>
                    </li>
                </ul>
            </div>
        </div>
    </footer>
                            
                            
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
</body>
</html>