<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de gestion de l'aide</title>
</head>
<body>
	<%-- inclusion dynamique du fragment entete.jsp --%>
<%-- 	<jsp:include page="/WEB-INF/fragments/entete.jsp" /> --%>

	<!-- =========================================================== -->
	<!-- ======== affichage de l'identifiant et des rôles ========== -->
	<!-- =========================================================== -->
	<div style="border: 1px dotted red; width: 400px;">
			<!-- affichage de l'identifiant de l'utilisateur -->
			<h3>
				Bienvenue, <s:authentication property="name"/>
			</h3>
			
			<!-- affichage des rôle de l'utilisateur -->
			<s:authentication property="authorities" var="authorites"/>
			
			<ul>
				<c:forEach items="${authorites}" var="auth">
					<li>${auth.authority}</li>	
				</c:forEach>
			</ul>
	</div>

	<!-- récupérée grace à la méthode generateCoursList du controlleur AideController-->
	<h2>Liste des aides</h2>

	<table>
		<tr>
			<th>Identifiant</th>
			<th>Page</th>
			<th>Contenu</th>


		</tr>
		<c:forEach items="${attribut_listeAide}" var="aide">

			<tr>
				<td>${aide.idAide}</td>
				<td>${aide.page}</td>
				<td>${aide.contenu}</td>

				<!-- appelle vers la méthode afficherFormulaireUpdateCours du AideController, 
					redirigant vers le formulaire pour udpater l'aide -->
				<td colspan="2"><a href="${pageContext.request.contextPath}/aideUpdate/${aide.idAide}">Modifier</a>
				</td>

				<!-- appelle vers la méthode supprimerAide du AideController -->
				<td colspan="2"><a href="${pageContext.request.contextPath}/aideDelete/${aide.idAide}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddCours du AideController, 
					redirigant vers le formulaire pour ajouter l'aide -->
		<tr>
			<td colspan="5"><a href="${pageContext.request.contextPath}/aideAdd">Ajouter</a></td>
		</tr>
	</table>
	<hr />

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
<%-- 	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" /> --%>

</body>
</html>