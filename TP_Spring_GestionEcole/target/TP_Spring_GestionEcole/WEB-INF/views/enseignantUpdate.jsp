<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'un enseignant</title>


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
		<h1>Modification des informations d'un enseignant</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="ensUpdateCommand" method="POST" action="${pageContext.request.contextPath}/ensUpdate-meth">
		
			<table>
			
				<tr>
					<td>
						<form:hidden path="identifiant"/>
					</td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="nom">Nom : </form:label> </td>
					<td> <form:input class="finput" path="nom"/> </td>
					<td><form:errors class="ferror" path="nom" /></td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="prenom">Pr�nom : </form:label> </td>
					<td> <form:input class="finput" path="prenom"/> </td>
					<td><form:errors class="ferror" path="prenom" /></td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="adresse.idAdresse">ID de l'adresse : </form:label> </td>
					<td> <form:input class="finput" path="adresse.idAdresse"/> </td>
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
					<td> <form:input class="finput" path="email"/> </td>
					<td><form:errors class="ferror" path="email" /></td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="role">R�le : </form:label> </td>
<%-- 					<td> <form:input path="role"/> </td> --%>
					<td>
						<form:select class="finput" path="role">
							<form:option value="ROLE_ADMIN" label="Administrateur"/>
							<form:option value="ROLE_ENS" label="Enseignant" selected="selected"/>
							<form:option value="ROLE_ETU" label="Etudiant"/>
							
							<form:options items="${ role }" />
						</form:select>
					</td>
					<td><form:errors class="ferror" path="role" /></td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="motDePasse">Mot De Passe : </form:label> </td>
					<td> <form:input class="finput" path="motDePasse"/> </td>
					<td><form:errors class="ferror" path="motDePasse" /></td>
				</tr>
				
				<tr>
					<td colspan="2"><button type="submit" class="btn btn-success">Modifier
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