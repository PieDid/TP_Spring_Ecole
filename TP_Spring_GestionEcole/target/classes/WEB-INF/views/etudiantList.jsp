<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<div align="center">
	<h2>Liste des Etudiants</h2>
	</div>

	<table>
		<tr>
			<th>Photo</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse</th>
			<th>Mail</th>
			<th>Date de Naissance</th>
		</tr>
		<c:forEach items="${attribut_listeEtudiant}" var="etudiant">
		<tr>
			<td>${etudiant.photo}</td>
			<td>${etudiant.nom}</td>
			<td>${etudiant.prenom}</td>
			<td>${etudiant.adresse}</td>
			<td>${etudiant.email}</td>
			<td>${etudiant.dateDeNaissance}</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/etuUpdate/${administrateur.identifiant}">Modifier</a>
			</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/etuDelete/${administrateur.identifiant}">Supprimer</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<a href="${pageContext.request.contextPath}/etuAdd">Ajouter</a>
			</td>
		</tr>
	</table>

</body>
</html>