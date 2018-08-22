<%@page import="java.util.ArrayList"%>
<%@ page pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-Compatible" content="ie=edge">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,300,700" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="/LaPepite/css/lapepite.css" rel="stylesheet">
        <link href="/LaPepite/css/topMenu.css" rel="stylesheet">
        <title>acceuil</title>
    </head>
    <body>
        
        <div class="container">
            <div class="row">
                <div class="col-12">
                    <%@ include file="menu/topMenu.jsp" %>
                </div>
                <div class="col-12">
                    <%@ include file="menu/jewelsMenu.jsp" %>
                </div>
            </div>
            
        </div>
                
                <br>
        
        
        <div class="container">
        
            <div class="row jewel">
            
                <c:forEach items="${ listBijoux }" var ="bijoux" >
                    
                    <div class="card h-10 col-xl-3 col-md-12 m-5 jewelDiv ">
                    
                        <a href="shop/product?id=${bijoux.id_bijoux}"><img class="card-img-top" src="./image/bijoux/${bijoux.image_bijoux}" alt="photo d'un bijou"></a>
                        
                        <div class="card-body">
                        
                            <h4 class="card-title">
                            
                                <a href="shop/product?id=${bijoux.id_bijoux}">${bijoux.nom_bijoux}</a>
                                
                            </h4>
                            
                            <h5>${ bijoux.prix_bijoux } €</h5>
                            
                            <p class="card-text">${ bijoux.designer.nom_designer }</p>
                            
                        </div>
                        
                        <div class="card-footer">
                        
                            <small class="text-muted">${ bijoux.categorie.nom_categorie }</small>
                            
                        </div>
                        
                    </div>
                    
                </c:forEach>
                
            </div>
            
        </div>
        
        
    </body>
</html>
