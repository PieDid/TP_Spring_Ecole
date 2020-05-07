<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion des promotions</title>

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

	<!-- récupérée grace à la méthode generateCoursList du controlleur PromotionController-->
	<h2>Liste des promotions</h2>

	<table class="stable table-striped">
		<tr>
			<th>Libellé de la promotion</th>
			<th colspan="2">Actions</th>
		</tr>
		<c:forEach items="${attribut_listePromotion}" var="promotion">

			<tr>
				<td>${promotion.libelle}</td>

				<!-- liste des etudiant / enseignant / cours / matiere de la promotion ? -->

				<!-- appelle vers la méthode afficherFormulaireUpdatePromotion du PromotionController, 
					redirigant vers le formulaire pour udpater la promotion -->
					<!-- !!! NE MARCHE PAS POUR LINSTANT CAR LIBELLE EST LA PK !!!! -->
<%-- 				<td colspan="2"><a href="${pageContext.request.contextPath}/promotionUpdate/${promotion.libelle}">Modifier</a></td> --%>

				<!-- appelle vers la méthode supprimerPromotion du PromotionController -->
				<td colspan="2"><a href="${pageContext.request.contextPath}/promotionDelete/${promotion.libelle}">Supprimer</a></td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddPromotion du PromotionController, 
					redirigant vers le formulaire pour ajouter la promotion -->
		<tr>
			<td colspan="3" class="AjoutRow"><a href="${pageContext.request.contextPath}/promotionAdd">Ajouter</a></td>
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