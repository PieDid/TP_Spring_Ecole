<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de liste des cours</title>

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
	
	<!-- récupérée grace à la méthode generateCoursList du controlleur CoursController-->
	<h2>Liste des cours</h2>

	<table class="stable table-striped">
		<tr>
			<th>Identifiant</th>
			<th>Libellé Cours</th>
			<th>Matière</th>
			<th>Date</th>
			<th>Durée</th>
			<th>Description</th>
			<th>Promotion</th>
			<th colspan="4">Actions</th>

		</tr>
		<c:forEach items="${attribut_listeCours}" var="cours">

			<tr>
				<td>${cours.idCours}</td>
				<td>${cours.libelle}</td>
				<td>${cours.matiere.libelle}</td>
				<td>${cours.date}</td>
				<td>${cours.duree}</td>
				<td>${cours.description}</td>
				<td>${cours.promotion.libelle}</td>
				<!-- Cette méthode peut etre dans personne controller. 
				
					Elle s'appelle etudiantByIdCours, prend en argument 
					un int idCours qui corresponds à l'id du cours, et renvoie la liste des étudiants qui sont associé a ce cours. 
					
					L'affichage de la liste se ferait dans etudiantList.jsp, ou qqch comme ca. 
					
					La requete SQL ressemblerait à :
					
					SELECT * FROM etudiant e 
						 INNER JOIN etudiantcours ec
						 ON e.id_etudiant = ec.etudiant_id
						 WHERE ec.cours_id = :pIdCours
							 
					avec pIdCours qui correspond à l'id du cours en question.
				 -->

<!-- 				<td colspan="2"><a -->
<%-- 					href="etudiantByIdCours?coursId=${cours.idCours}">Liste des --%>
<!-- 						étudiants du cours (redirection)</a></td> -->

				<!-- appelle vers la méthode afficherFormulaireUpdateCours du CoursController, 
					redirigant vers le formulaire pour udpater le cours -->
				<td colspan="2"><a href="${pageContext.request.contextPath}/coursUpdate/${cours.idCours}">Modifier</a>
				</td>

				<!-- appelle vers la méthode supprimerCours du CoursController -->
				<td colspan="2"><a href="${pageContext.request.contextPath}/coursDelete/${cours.idCours}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddCours du CoursController, 
					redirigant vers le formulaire pour ajouter le cours le cours -->
		<tr>
			<td colspan="22" class="AjoutRow"><a href="${pageContext.request.contextPath}/coursAdd">Ajouter</a></td>
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