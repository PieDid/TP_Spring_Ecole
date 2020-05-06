<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
 <%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'un cours</title>

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
		<h1>Modification des informations d'un cours</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="coursUpdateCommand" method="POST" action="${pageContext.request.contextPath}/coursUpdate-meth">
		
			<table cellspacing="5px">
			
				<tr>
					<td>
						<form:hidden path="idCours"/>
					</td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="libelle">Libellé : </form:label> </td>
					<td> <form:input class="finput" path="libelle" placeholder="Entrez le libellé du cours"/> </td>
					<td> <form:errors class="ferror" path="libelle"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="matiere.libelle">Matière : </form:label> </td>
					<td> <form:input class="finput" path="matiere.libelle" placeholder="Entrez le libellé de la matière"/> </td>
					<td> <form:errors class="ferror" path="matiere.libelle"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="date">Date : </form:label> </td>
					<td> <form:input class="finput" path="date" placeholder="Entrez la date"/> </td>
					<td> <form:errors class="ferror" path="date"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="duree">Durée : </form:label> </td>
					<td> <form:input class="finput" path="duree" placeholder="Entrez la durée du cours"/> </td>
					<td> <form:errors class="ferror" path="duree"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="description">Description : </form:label> </td>
					<td> <form:input class="finput" path="description" placeholder="Infos relatives au cours (facultatif)"/> </td>
					<td> <form:errors class="ferror" path="description"/> </td>
				</tr>
				
				<tr>
					<td> <form:label class="flabel" path="promotion.libelle">Promotion : </form:label> </td>
					<td> <form:input class="finput" path="promotion.libelle" placeholder="Entrez l'année" pattern="[0-9]{4}"/> </td>
					<td> <form:errors class="ferror" path="promotion.libelle"/> </td>
				</tr>

				
				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-success" >Modifier </button>
					</td>
				</tr>
								
			</table>
		</form:form>
	</div>
	
	<div align="center">
		<button type="button" class="btn btn-lg btn-info btn-aide">Aide</button>
		<p class="alert alert-info p-aide">${attribut_aide}<p>
	</div>
	
		<script type="text/javascript">
	//#Bouton Aide
		$(".btn-aide").click(function() {
			$(".p-aide").toggle();
		});
	</script>
	
	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>