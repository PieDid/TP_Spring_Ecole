<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion de l'aide</title>


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
	<h2>Liste des aides</h2>

	<table class="stable table-striped">
		<tr>
			<th>Identifiant</th>
			<th>Page</th>
			<th>Contenu</th>
			<th colspan="4">Actions</th>


		</tr>
		<c:forEach items="${attribut_listeAide}" var="aide">

			<tr>
				<td>${aide.idAide}</td>
				<td>${aide.page}</td>
				<td>${aide.contenu}</td>

				<!-- appelle vers la méthode afficherFormulaireUpdateCours du AideController, 
					redirigant vers le formulaire pour udpater l'aide -->
				<td colspan="2"><a
					href="${pageContext.request.contextPath}/aideUpdate/${aide.idAide}">Modifier</a>
				</td>

				<!-- appelle vers la méthode supprimerAide du AideController -->
				<td colspan="2"><a
					href="${pageContext.request.contextPath}/aideDelete/${aide.idAide}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddCours du AideController, 
					redirigant vers le formulaire pour ajouter l'aide -->
		<tr>
			<td colspan="7" class="AjoutRow"><a
				href="${pageContext.request.contextPath}/aideAdd">Ajouter</a></td>
		</tr>
	</table>
	<hr />
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