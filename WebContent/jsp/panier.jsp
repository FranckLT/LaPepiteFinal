<%-- 
    Document   : panier
    Created on : 3 août 2018, 11:19:01
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://fonts.googleapis.com/css?family=Open+Sans:400,600,300,700" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link href="/LaPepite/css/lapepite.css" rel="stylesheet">
        <link href="/LaPepite/css/topMenu.css" rel="stylesheet">
        <title>Votre panier</title>
    </head>
    <body>
        
        <div class="container">
      
            <%@ include file="menu/topMenu.jsp" %>
                
        </div>
        
            <c:if test="${not empty listLignePanier}">
                <c:forEach items="${listLignePanier}" var="ligne">
                    
                    <div class="row">
                        
                        <div class="card-body">
                            <h3 class="card-title">${ligne.bijoux.nom}</h3>
                            <h4>${ligne.bijoux.prix} €</h4>
                            <p class="card-text">${ligne.quantite}</p>
                            <p>${ligne.bijoux.prix * ligne.quantite}</p>
                            <div class="row justify-content-between">
                            </div>
                        </div>
                    </div>
                
                </c:forEach>
            </c:if>
        
        
    </body>
</html>
