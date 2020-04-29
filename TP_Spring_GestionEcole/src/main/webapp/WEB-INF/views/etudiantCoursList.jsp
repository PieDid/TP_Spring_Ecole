<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des association Etudiant-Cours</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<h2>Liste des relations Etudiant-Cours</h2>

	<table>
		<tr>
			<th>Id de la relation</th>
			<th>Absence</th>
			<th>Motif</th>
			<th>ID de l'étudiant</th>
			<th>ID du cours</th>
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
			<td colspan="3"><a href="${pageContext.request.contextPath}/etudiantCoursAdd">Ajouter</a></td>
		</tr>
	</table>
	<hr />
	
	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>