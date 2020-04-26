<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de Liste des promotions</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

<!-- récupérée grace à la méthode generateCoursList du controlleur PromotionController-->
	<h2>Liste des promotions</h2>

	<table>
		<tr>
			<th>Libellé de la promotion</th>
		</tr>
		<c:forEach items="${attribut_listePromotion}" var="promotion">

			<tr>
				<td>${promotion.libelle}</td>

				<!-- liste des etudiant / enseignant / cours / matiere de la promotion ? -->

				<!-- appelle vers la méthode afficherFormulaireUpdatePromotion du PromotionController, 
					redirigant vers le formulaire pour udpater l'aide -->
				<td colspan="2"><a href="promotionUpdate?promotionLibelle=${promotion.libelle}">Modifier</a>
				</td>

				<!-- appelle vers la méthode supprimerAide du AideController -->
				<td colspan="2"><a href="delete?promotionLibelle=${promotion.libelle}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddPromotion du PromotionController, 
					redirigant vers le formulaire pour ajouter l'aide -->
		<tr>
			<td colspan="3"><a href="promotionAdd-form">Ajouter</a></td>
		</tr>
	</table>
	<hr />
	
	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />
	

</body>
</html>