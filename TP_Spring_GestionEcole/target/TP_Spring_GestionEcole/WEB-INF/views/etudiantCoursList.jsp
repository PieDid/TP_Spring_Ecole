<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des association Etudiant-Cours</title>


<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />

<spring:url value="/assets/styles/ListStyle.css" var="ListCss" />
<link rel="stylesheet" href="${ListCss}" />


<spring:url value="/assets/scripts/jquery-3.4.1.js" var="JQuery" />
<script type="text/javascript" src="${JQuery}"></script>

</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<h2>Liste des Absences</h2>

	<table class="stable table-striped">
		<tr>
			<th>Id de la relation</th>
			<th>Absence</th>
			<th>Motif</th>
			<th>ID de l'étudiant</th>
			<th>ID du cours</th>
			<th colspan="4">Actions</th>
		</tr>
		
		<c:forEach items="${attribut_listeEtudiantCours}" var="etudiantCours">

			<tr>
				<td>${etudiantCours.idEtudiantCours}</td>
				<td>${etudiantCours.absence}</td>
				<td>${etudiantCours.motif}</td>
				<td>${etudiantCours.etudiant.identifiant}</td>
				<td>${etudiantCours.cours.idCours}</td>


				<td colspan="2"><a href="${pageContext.request.contextPath}/etudiantCoursUpdate/${etudiantCours.idEtudiantCours}">Modifier</a>
				</td>

				<td colspan="2"><a href="${pageContext.request.contextPath}/etudiantCoursDelete/${etudiantCours.idEtudiantCours}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="9"><a href="${pageContext.request.contextPath}/etudiantCoursAdd">Ajouter</a></td>
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