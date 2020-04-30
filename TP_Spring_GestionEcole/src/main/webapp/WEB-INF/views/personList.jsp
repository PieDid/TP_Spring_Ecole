<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
	
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
	
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Liste de toutes les personnes de l'école</title>
</head>
<body>

	<!-- Affichage du bouton connexion/déconnexion -->
	<!-- ========================================= -->
	<div align="center">

		<s:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">

			<a href="<c:url value='/logout' />"> Se Déconnecter </a>
		</s:authorize>

		<s:authorize access="hasRole('ROLE_ANONYMOUS')">

			<a href="<c:url value='/login.jsp' />"> Se Connecter </a>
		</s:authorize>
	</div>

	<br />
	<br />

	<!-- Affichage de l'identifiant et du rôle -->
	<!-- ===================================== -->
	<div>


		<h3>
			Bienvenue,
			<s:authentication property="nom" />
			<s:authentication property="prenom" />
		</h3>

		<s:authentication property="authorities" var="authorites" />

		<ul>
			<c:forEach items="${authorites}" var="auth">
				<li>${auth.authority}</li>
			</c:forEach>
		</ul>

	</div>

	<br />
	<br />
	

	<!-- Affichage de la liste des messages -->
	<!-- ================================== -->
	<div align="center">
	<h2>Liste des personne</h2>
	</div>

	<table>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse</th>
			<th>Mail</th>
		</tr>
		<c:forEach items="${attribut_listePersonne}" var="personne">
		<tr>
			<td>${personne.nom}</td>
			<td>${personne.prenom}</td>
			<td>${personne.adresse}</td>
			<td>${personne.email}</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/personUpdate/${personne.identifiant}">Modifier</a>
			</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/personDelete/${personne.identifiant}">Supprimer</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<a href="${pageContext.request.contextPath}/personAdd">Ajouter</a>
			</td>
		</tr>
	</table>


</body>
</html>