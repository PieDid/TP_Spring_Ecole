<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des Assigmements</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<h2>Liste des assignements</h2>

	<table>
		<tr>
			<th>Id de l'assignement</th>
			<th>Enseignant</th>
			<th>Promotion</th>
			<th>Matière</th>
		</tr>
		
		<c:forEach items="${attribut_listeEnsigne}" var="enseigne">

			<tr>
				<td>${enseigne.id}</td>
				<td>${enseigne.enseignant}</td>
				<td>${enseigne.promotion}</td>
				<td>${enseigne.matiere}</td>


				<td colspan="2"><a href="${pageContext.request.contextPath}/enseigneUpdate/${enseigne.id}">Modifier</a>
				</td>

				<td colspan="2"><a href="${pageContext.request.contextPath}/enseigneDelete/${enseigne.id}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="3"><a href="${pageContext.request.contextPath}/eneigneAdd">Ajouter</a></td>
		</tr>
	</table>
	<hr />
	
	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>