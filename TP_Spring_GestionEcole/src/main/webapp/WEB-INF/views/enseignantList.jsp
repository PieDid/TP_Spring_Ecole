<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des enseignants</title>
</head>
<body>

	<div align="center">
	<h2>Liste des enseignants</h2>
	</div>

	<table>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse</th>
			<th>Mail</th>
		</tr>
		<c:forEach items="${attribut_listeEnseignant}" var="enseignant">
		<tr>
			<td>${enseignant.nom}</td>
			<td>${enseignant.prenom}</td>
			<td>${enseignant.adresse}</td>
			<td>${enseignant.email}</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/ensUpdate/${enseignant.identifiant}">Modifier</a>
			</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/ensDelete/${enseignant.identifiant}">Supprimer</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<a href="${pageContext.request.contextPath}/ensAdd">Ajouter</a>
			</td>
		</tr>
	</table>

</body>
</html>