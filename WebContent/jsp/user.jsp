<%-- 
    Document   : user
    Created on : 4 août 2018, 15:11:23
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
        <title>Mon compte</title>
    </head>
    <body>
        
        <%@include file="menu/topMenu.jsp" %>
        
        <div class="container">
            
            <div class="row">
                
                <h1>Bienvenue ${utilisateur.prenom} ${utilisateur.nom}</h1>
                
                
                
            </div>
            
        </div>
        
    </body>
</html>
