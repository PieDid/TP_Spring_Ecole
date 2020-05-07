<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des Adresses</title>


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

	<!-- récupérée grace à la méthode generateCoursList du controlleur AideController-->
	<h2>Liste des adresses</h2>

	<table class="stable table-striped">
		<tr>
			<th>Rue</th>
			<th>Code Postal</th>
			<th>Ville</th>
			<th colspan="4">Actions</th>


		</tr>
		<c:forEach items="${attribut_listeAdresse}" var="adresse">

			<tr>
				<td>${adresse.rue}</td>
				<td>${adresse.codePostal}</td>
				<td>${adresse.ville}</td>

				<td colspan="2"><a
					href="${pageContext.request.contextPath}/adresseUpdate/${adresse.idAdresse}">Modifier</a>
				</td>

				<td colspan="2"><a
					href="${pageContext.request.contextPath}/adresseDelete/${adresse.idAdresse}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<tr>
			<td colspan="7" class="AjoutRow"><a
				href="${pageContext.request.contextPath}/adresseAdd">Ajouter</a></td>

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