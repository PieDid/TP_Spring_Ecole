<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une Aide</title>

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
		<h2>Ajout d'une aide</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="aideAddCommand" method="POST"
			action="${pageContext.request.contextPath}/aideAdd-meth">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table cellspacing="5px">
				<tr>
					<td><form:label class="flabel" path="page">Page : </form:label></td>
					<td><form:input class="finput" path="page"
							placeholder="aideList" /></td>
					<td><form:errors class="ferror" path="page" /></td>
				</tr>

				<tr>
					<td><form:label class="flabel" path="contenu">Contenu : </form:label></td>
					<td><form:input class="finput" path="contenu"
							placeholder="Contenu de l'aide a afficher" /></td>
					<td><form:errors class="ferror" path="contenu" /></td>
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