<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Formulaire d'authentification perso)</title>
</head>
<body>
	
		<!-- ======================================================== -->
		<!-- ========== Affichage des messages d'erreurs ============ -->
		<!-- ======================================================== -->
		<!-- en cas d'échec de l'authentification -->
		<c:if test="${not empty param.error}">
			<font color="red;">
				Erreur d'authentification.Identifiant ou mot de passe invalide. <br/>
				Raison : ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
			</font>
		</c:if>

		<!-- ================================================= -->
		<!-- =======Formulaire d'authentification perso======= -->
		<!-- ================================================= -->
		
		<fieldset>
		
		<legend>Formulaire d'authentification</legend>
		
		<!-- -construction de l'url effectuant l'authetification -->
		
		<c:url value="login" var="loginUrl" />
		
		<form action="${loginUrl}" method="post">
		
		<table>
		
		<tr>
		<td>Identifiant : </td>
		<td> <input type="text" name="u_identifiant"/></td>
		</tr>
		
		
			<tr>
		<td>Mot De Passe : </td>
		<td> <input type="text" name="u_motdepasse"/></td>
		</tr>
		
		
		<tr>
				<td>
					<input type="submit" value="Se Connecter"/>
							
				</td>
		</tr>
		
		</table>
		
		</form>
		
		</fieldset>

		<br/>
		<!-- lien vers la page d'acceuil après déconnexion -->

<%-- 	<c:if test="${not empty param.logout_message}"> --%>
	
<%-- 	<a href="${pageContext.request.contextPath}/index"> --%>
<!-- 	Page d'accueil -->
<!-- 	</a> -->
	
<%-- 	</c:if> --%>
		

</body>
</html>