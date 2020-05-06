<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
     <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'un étudiant</title>


<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />

<spring:url value="/assets/styles/FormStyle.css" var="FormCss" />
<link rel="stylesheet" href="${FormCss}" />


<spring:url value="/assets/scripts/jquery-3.4.1.js" var="JQuery" />
<script type="text/javascript" src="${JQuery}"></script>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />
	
	<div align="center">
		<h2>Ajout d'un Etudiant</h2>
	</div>
	
		<div align="center">
		
		<form:form modelAttribute="etuAddCommand" method="POST" action="${pageContext.request.contextPath}/etuAdd-meth">
		
		<form:errors path="*" cssClass="error_validation" element="div" />
		
			<table>
				<tr>
					<td> <form:label class="flabel" path="nom">Nom : </form:label> </td>
					<td> <form:input class="finput" path="nom" placeholder="Saqcuet"/> </td>
					<td> <form:errors class="ferror" path="nom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="prenom">Prénom : </form:label> </td>
					<td> <form:input class="finput" path="prenom" placeholder="Bilbon"/> </td>
					<td> <form:errors class="ferror" path="prenom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="adresse.idAdresse">ID de l'adresse : </form:label> </td>
					<td> <form:input class="finput" path="adresse.idAdresse" placeholder="Veuillez entrer l'id de l'adresse"/> </td>
					<td>
						<form:select items="${listeAdresses}" path="" >
						<c:forEach items="${listeAdresses}" var="adresse2">
                  			<form:option value="${adresse2}">${adresse2}</form:option>
    					</c:forEach>
    					</form:select>
					</td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="email">Adresse Mail : </form:label> </td>
					<td> <form:input class="finput" path="email" placeholder="bilbo@precious.tdm"/> </td>
					<td> <form:errors class="ferror" path="email"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="dateDeNaissance">Date de naissance : </form:label> </td>
					<td> <form:input class="finput" path="dateDeNaissance" placeholder="02/05/1985"/> </td>
					<td> <form:errors class="ferror" path="dateDeNaissance"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="photo">Photo : </form:label> </td>
					<td> <form:input class="finput" path="photo" placeholder="bilbon.png"/> </td>
					<td> <form:errors class="ferror" path="photo"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="promotion.libelle">Promotion : </form:label> </td>
					<td> <form:input class="finput" path="promotion.libelle" placeholder="Entrez l'année"/> </td>
					<td> <form:errors class="ferror" path="promotion.libelle"/> </td>
				</tr>
				
				<tr>
					<td> <form:label  class="flabel" path="role">Rôle : </form:label> </td>
<%-- 					<td> <form:input path="role"/> </td> --%>
					<td>
						<form:select class="finput" path="role">
							<form:option value="ROLE_ADMIN" label="Administrateur"/>
							<form:option value="ROLE_ENS" label="Enseignant"/>
							<form:option value="ROLE_ETU" label="Etudiant" selected="selected"/>
							
							<form:options items="${ role }" />
						</form:select>
					</td>
					<td> <form:errors class="ferror" path="role"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="motDePasse">Mot De Passe : </form:label> </td>
					<td> <form:input class="finput" path="motDePasse" placeholder = "Veuillez entrer votre mot de passe"/> </td>
					<td> <form:errors class="ferror" path="motDePasse"/> </td>
				</tr>
				
				
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-success">Ajouter
						</button></td>
				</tr>
								
			</table>
		</form:form>
	</div>
	
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