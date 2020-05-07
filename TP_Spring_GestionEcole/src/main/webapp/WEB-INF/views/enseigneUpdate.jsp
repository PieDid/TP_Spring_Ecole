<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'un assignement</title>

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
		<h2>Modification d'un assignement</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="enseigneUpdateCommand" method="POST"
			action="${pageContext.request.contextPath}/enseigneUpdate-meth">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table cellspacing="5px">
				<tr>
					<td><form:hidden path="id_enseigne" /></td>
				</tr>
				<tr>
					<td><form:label class="flabel" path="enseignant.identifiant">Enseignant ID : </form:label></td>
					<td><form:input class="finput" path="enseignant.identifiant" /></td>
					<td><form:errors class="ferror" path="enseignant.identifiant" /></td>

<!-- 					<td><label for="ens">Liste des Enseignants : </label></td> -->
<!-- 					<td> -->
						<%-- 						<form:select items="${listeEnseignants}" path="enseignant" > --%>
						<%-- 							<form:option value="ens.identifiant" label="Choisissez un enseignant" /> --%>
						<%-- 							<form:options  items="${enseignant}" /> --%> <%-- 						</form:select> --%>



<!-- 					</td> -->
				</tr>

				<tr>
					<td><form:label class="flabel" path="matiere.libelle">Matiere : </form:label></td>
					<td><form:input class="finput" path="matiere.libelle" /></td>
					<td><form:errors class="ferror" path="matiere.libelle" /></td>
				</tr>

				<tr>
					<td><form:label class="flabel" path="promotion.libelle">Promotion : </form:label></td>
					<td><form:input class="finput" path="promotion.libelle" /></td>
					<td><form:errors class="ferror" path="promotion.libelle" /></td>
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

	<script type="text/javascript">
		//#Bouton Aide
		$(".btn-aide").click(function() {
			$(".p-aide").toggle();
		});
	</script>

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" /></body>
</html>