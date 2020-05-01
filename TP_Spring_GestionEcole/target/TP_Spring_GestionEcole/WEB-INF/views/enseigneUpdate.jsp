<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'un assignement</title>
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

			<table>
				<tr>
					<td>
						<form:hidden path="id_enseigne"/>
					</td>
				</tr>
				<tr>
					<td><form:label path="enseignant.identifiant">Enseignant : </form:label></td>
					<td><form:input path="enseignant.identifiant" /></td>
					<td><form:errors path="enseignant.identifiant" /></td>
				</tr>
				
				<tr>
					<td><form:label path="matiere.libelle">Matiere : </form:label></td>
					<td><form:input path="matiere.libelle" /></td>
					<td><form:errors path="matiere.libelle" /></td>
				</tr>
				
				<tr>
					<td><form:label path="promotion.libelle">Promotion : </form:label></td>
					<td><form:input path="promotion.libelle" /></td>
					<td><form:errors path="promotion.libelle" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Modifier">
					</td>
				</tr>

			</table>
		</form:form>
	</div>

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>