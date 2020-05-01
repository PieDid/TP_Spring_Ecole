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
	<h2>Liste des Administrateurs</h2>
	</div>

	<table>
		<tr>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse</th>
			<th>Mail</th>
		</tr>
		<c:forEach items="${attribut_listeAdmin}" var="administrateur">
		<tr>
			<td>${administrateur.nom}</td>
			<td>${administrateur.prenom}</td>
			<td>${administrateur.adresse}</td>
			<td>${administrateur.email}</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/adminUpdate/${administrateur.identifiant}">Modifier</a>
			</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/adminDelete/${administrateur.identifiant}">Supprimer</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="3">
				<a href="${pageContext.request.contextPath}/adminAdd">Ajouter</a>
			</td>
		</tr>
	</table>

</body>
</html>