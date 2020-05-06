<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une matière</title>

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
		<h2>Ajout d'une Matiere</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="matiereAddCommand" method="POST"
			action="${pageContext.request.contextPath}/matiereAdd-meth">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table cellspacing="5px">
				<tr>
					<td><form:label class="flabel" path="libelle">Libellé : </form:label></td>
					<td><form:input class="finput" path="libelle" placeholder="Entrez le libellé de la matière" /></td>
					<td><form:errors class="ferror" path="libelle" /></td>
				</tr>

				<tr>
					<td colspan="2">
						<button type="submit" class="btn btn-success" >Ajouter </button>
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