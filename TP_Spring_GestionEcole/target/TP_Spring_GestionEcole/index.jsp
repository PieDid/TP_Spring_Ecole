<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'accueil</title>


<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />

<spring:url value="/assets/scripts/jquery-3.4.1.js" var="JQuery" />
<script type="text/javascript" src="${JQuery}"></script>

<spring:url value ="/assets/images/universite_photo_bis.jpg" var="photo_fac" />

<style>
.col {
	margin: 10px;
	border-radius: 10px; background-color : lightblue;
	border: solid 2px blue;
	font-size: 25px;
	background-color: lightblue;
}

a:hover {
	color: orange;
}
hr{ 
	width: 90%;
	background-color: #FFA500;
}

.image_universite{
	background-image: url("assets/images/universite_photo_bis.jpg");
	background-size: 100%;
	height : 335px;
	
}

.name_univ{
	width : 40%;
	margin : 0 auto;
	margin-top: 10px;
	margin-bottom: 40px;
	border : gold 2px solid;
	background-color: lightyellow;
	color: orange;
	
	font: italic "Fira Sans", serif;
}

.logo{
width : 100px;
margin-top : 40px;

}
</style>

</head>
<body>

	<%-- Redirection vers l'URL '' (appel du Controller MessageListController) --%>
	<%
		//response.sendRedirect("");
	%>

	<!-- ======================================================== -->
	<!-- ========= Affichage du message de déconnexion ========== -->
	<!-- ======================================================== -->

	<c:if test="${not empty param.logout_message}">
		<font color="green;"> Déconnexion avec succès </font>

	</c:if>

	<!-- ============================================================= -->
	<!-- ======== affichage du bouton connexion/deconnexion ========== -->
	<!-- ============================================================= -->
	<div align="center">
		<!-- condition sur le rôle de l'utilisateur -->
		<s:authorize access="hasAnyRole('ROLE_ENS,ROLE_ETU,ROLE_ADMIN')">
			<!-- bouton se déconnecter -->
			<a href="<c:url value='/logout'/>">Se Déconnecter</a>
		</s:authorize>

		<s:authorize access="hasRole('ROLE_ANONYMOUS')">
			<!-- bouton se Connecter -->
			<a href="<c:url value='/login.jsp'/>">Se Connecter</a>

		</s:authorize>
	
		<!-- =========================================================== -->
		<!-- ======== affichage de l'identifiant et des rôles ========== -->
		<!-- =========================================================== -->
		<div style="border: 1px dotted red; width: 600px; border-radius: 10px;">
			Vous êtes maintenant connecté en tant qu'
			<s:authentication property="authorities" var="authorites"/>
			<c:forEach items="${authorites}" var="auth">
				<c:if test="${auth.authority == 'ROLE_ADMIN'}">administrateur</c:if>
				<c:if test="${auth.authority == 'ROLE_ENS'}">enseignant</c:if>
				<c:if test="${auth.authority == 'ROLE_ETU'}">étudiant</c:if>
				<c:if test="${auth.authority == 'ROLE_ANONYMOUS'}">anonyme</c:if>
			</c:forEach>
		</div>

		<div style="text-align: center">
			<img src="assets/images/logo_univ.png" class="logo">
			<h1 class="name_univ">Université Charlotte de Galles</h1>
		</div>

		<div class="image_universite"></div>
		

		<div class="container">
		<hr />
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/adminList">Liste
						des Admins</a>
				</div>
				<div class="col">
					<a href="${pageContext.request.contextPath}/aideList">Liste des
						aides</a>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/coursList">Liste
						des cours</a>
				</div>
				<div class="col">
					<a href="${pageContext.request.contextPath}/ensList">Liste
						des enseignants</a>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/etuList">Liste
						des étudiants</a>
				</div>
				<div class="col">
					<a href="${pageContext.request.contextPath}/matiereList">Liste
						des matières</a>
				</div>
			</div>
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/personList">Liste
						des personnes</a>
				</div>
				<div class="col">
					<a href="${pageContext.request.contextPath}/promotionList">Liste
						des promotions</a>
				</div>
				
			</div>
			<div class="row">
				<div class="col">
					<a href="${pageContext.request.contextPath}/adresseList">Liste
						des Adresses</a>
				</div>
				<div class="col">
					<a href="${pageContext.request.contextPath}/enseigneList">Liste
						des Enseignes</a>
				</div>
				
			</div>
<hr />
			</div>
			</div>
			
</body>
</html>