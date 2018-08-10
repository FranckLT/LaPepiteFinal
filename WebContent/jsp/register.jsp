<%-- 
    Document   : register
    Created on : 2 août 2018, 15:53:54
    Author     : Sammy Guergachi <sguergachi at gmail.com>
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registering Page</title>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
                integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
        <script
            src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
            integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
        <script
            src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
            integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/js/select2.min.js"></script>
        <link
            href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.1/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-WskhaSGFgHYWDcbwN70/dfYBj47jz9qbsMId/iRN3ewGhXQFZCSftd1LZCfmhktB"
            crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.6-rc.0/css/select2.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="/LaPepite/css/topMenu.css" type="text/css"/>
    </head>
    <body>
        
        <div class="container">
            <%@include file="menu/topMenu.jsp" %>
        </div>
        
        
        <div class="pricing-header px-3 py-3 pt-md-5 pb-md-4 mx-auto text-center">
            
            <h1 class="display-5">Register</h1>
            
            
        </div>
        
        <div class="container">
            
            <div class="row">
                <div class="col-md-6 col-md-offset-3 mx-auto text-center">
                    <div class="panel panel-login">
                        <div class="panel-heading">
                            <hr>
                        </div>
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <form id="regster-form" action="/LaPepite/register" method="post" role="form" style="display: block;">
                                        <div class="form-group">
                                            <input type="text" name="nom" id="username" tabindex="1" class="form-control" placeholder="Nom" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="prenom" id="prenom" tabindex="1" class="form-control" placeholder="Prenom" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="email" name="email" id="email" tabindex="1" class="form-control" placeholder="Email" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="text" name="adresse" id="adresse" tabindex="1" class="form-control" placeholder="Adresse Postale" value="">
                                        </div>
                                        <div class="form-group">
                                            <input type="password" name="password" id="password" tabindex="2" class="form-control" placeholder="Password">
                                        </div>
                                        
                                        <div class="form-group">
                                            <div class="row">
                                                <div class="col-sm-6 col-sm-offset-3">
                                                    <input type="submit" id="login-submit" tabindex="4" class="form-control btn btn-primary">
                                                </div>
                                            </div>
                                        </div>
                                        
                                    </form>
                                    
                                    <a href="/LaPepite/login">Deja membre ?</a>
                                    
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>    </body>
</html>
