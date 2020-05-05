<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une adresse</title>


<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />

<spring:url value="/assets/styles/FormStyle.css" var="FormCss" />
<link rel="stylesheet" href="${FormCss}" />


<spring:url value="/assets/scripts/jquery-3.4.1.js" var="JQuery" />
<script type="text/javascript" src="${JQuery}"></script>

<style>

</style>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" /> 

	<div align="center">
		<h2>Ajout d'une adresse</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="adresseAddCommand" method="POST"
			action="${pageContext.request.contextPath}/adresseAdd-meth">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table cellspacing="5px">
				
				<tr>
					<td><form:label class="flabel" path="rue" for="rue">Rue : </form:label></td>
					<td><form:input class="finput" path="rue" id="rue"  placeholder="22 place Charles de Gaulle"/></td>
					<td><form:errors class="ferror" path="rue" for="rue"/></td>
				</tr>
				

				<tr >
					<td><form:label class="flabel" path="codePostal" for="codePostal">Code Postal : </form:label></td>
					<td><form:input class="finput" path="codePostal" id="codePostal" placeholder="75000" pattern="[0-9]{5}" /></td>
					<td><form:errors class="ferror" path="codePostal" for="codePostal"/></td>
				</tr>

				<tr >
					<td><form:label class="flabel" path="ville" for="ville">Ville : </form:label></td>
					<td><form:input class="finput" path="ville" id="ville" placeholder="Paris"/></td>
					<td><form:errors class="ferror"  path="ville" for="ville"/></td>
				</tr>

				<tr>
					<td colspan="2"><button type="submit" class="btn btn-success" >Ajouter </button></td>
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