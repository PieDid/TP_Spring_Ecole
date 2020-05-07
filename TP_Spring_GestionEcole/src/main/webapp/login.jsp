<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>   
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'authentification perso)</title>


    <!-- Bootstrap core CSS -->
    <spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
	<link href="${bootstrapCss}" rel="stylesheet"/>


    <style>
      .bd-placeholder-img {
        font-size: 1.125rem;
        text-anchor: middle;
        -webkit-user-select: none;
        -moz-user-select: none;
        -ms-user-select: none;
        user-select: none;
      }

      @media (min-width: 768px) {
        .bd-placeholder-img-lg {
          font-size: 3.5rem;
        }
      }
    </style>
    <!-- Custom styles for this template -->
    <spring:url value="/assets/styles/signin.css" var="signinCss" />
    <link href="${signinCss}" rel="stylesheet">
  </head>
  
  
  <body class="text-center">
  	<c:url value="login" var="loginUrl" />
    <form class="form-signin" action="${loginUrl}" method="post">
	  <img class="mb-4" src="assets/images/identification.jpg" alt="" width="72" height="72">
	  <h1 class="h3 mb-3 font-weight-normal">Connexion</h1>
	  
	  <label for="inputEmail" class="sr-only">adresse mail</label>
	  <input type="email" id="inputEmail" name="u_identifiant" class="form-control" placeholder="adresse mail" required autofocus">
	  
	  <label for="inputPassword" class="sr-only">Mot de passe</label>
	  <input type="password" id="inputPassword" name="u_motdepasse" class="form-control" placeholder="Mot de passe" required>
	  <div class="checkbox mb-3">
	    <label>
	      <input type="checkbox" value="remember-me"> Se souvenir de moi
	    </label>
	  </div>
	  <button class="btn btn-lg btn-primary btn-block" type="submit">Se connecter</button>
	  <p class="mt-5 mb-3 text-muted">&copy; 2019-2020</p>
</form>
</body>
</html>