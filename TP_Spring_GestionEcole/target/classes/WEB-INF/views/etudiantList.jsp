<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
       <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des étudiants</title>

<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />

<spring:url value="/assets/styles/ListStyle.css" var="ListCss" />
<link rel="stylesheet" href="${ListCss}" />


<spring:url value="/assets/scripts/jquery-3.4.1.js" var="JQuery" />
<script type="text/javascript" src="${JQuery}"></script>
</head>
<body>

	<jsp:include page="/WEB-INF/fragments/entete.jsp" /> 

	<div align="center">
	<h2>Liste des Etudiants</h2>
	</div>

	<table  class="stable table-striped">
		<tr>
			<th>Photo</th>
			<th>Nom</th>
			<th>Prénom</th>
			<th>Adresse</th>
			<th>Mail</th>
			<th>Date de Naissance</th>
			<th>Promotion</th>
			<th colspan="4">Actions</th>
		</tr>
		<c:forEach items="${attribut_listeEtudiant}" var="etudiant">
		<tr>
			<td><img alt="image.png" src="assets/images/trombi/${etudiant.photo}" width="70px" height="80px"/> </td>
			<td>${etudiant.nom}</td>
			<td>${etudiant.prenom}</td>
			<td>${etudiant.adresse}</td>
			<td>${etudiant.email}</td>
			<td>${etudiant.dateDeNaissance}</td>
			<td>${etudiant.promotion.libelle}</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/etuUpdate/${etudiant.identifiant}">Modifier</a>
			</td>

			<td colspan="2">
				<a href="${pageContext.request.contextPath}/etuDelete/${etudiant.identifiant}">Supprimer</a>
			</td>
		</tr>
		</c:forEach>
		<tr>
			<td colspan="10" class="AjoutRow">
				<a href="${pageContext.request.contextPath}/etuAdd">Ajouter</a>
			</td>
		</tr>
	</table>
	
	<div align="center">
		<button type="button" class="btn btn-lg btn-info btn-aide">Aide</button>
		<p class="alert alert-info p-aide">${attribut_aide}
		<p>
	</div>
	<hr />

	<script type="text/javascript">
	//Bouton Aide
		$(".btn-aide").click(function() {
			$(".p-aide").toggle();
		});
	</script>

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>