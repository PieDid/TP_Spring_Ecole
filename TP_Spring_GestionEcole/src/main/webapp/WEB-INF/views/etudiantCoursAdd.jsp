<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une relation Etudiant-Cours</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<div align="center">
		<h2>Ajout d'une relation Etudiant-Cours</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="etudiantCoursAddCommand" method="POST"
			action="${pageContext.request.contextPath}/etudiantCoursAdd-meth">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table>
				<tr>
					<td><form:label path="absence">Absence : </form:label></td>
					<td><form:input path="absence" /></td>
					<td><form:errors path="absence" /></td>
				</tr>
				
				<tr>
					<td><form:label path="motif">Motif : </form:label></td>
					<td><form:input path="motif" /></td>
					<td><form:errors path="motif" /></td>
				</tr>
				
				<tr>
					<td><form:label path="etudiant.identifiant">ID de l'étudiant : </form:label></td>
					<td><form:input path="etudiant.identifiant" /></td>
					<td><form:errors path="etudiant.identifiant" /></td>
				</tr>
				
				<tr>
					<td><form:label path="cours.idCours">ID du cours : </form:label></td>
					<td><form:input path="cours.idCours" /></td>
					<td><form:errors path="cours.idCours" /></td>
				</tr>

				<tr>
					<td colspan="2"><input type="submit" value="Ajouter">
					</td>
				</tr>

			</table>
		</form:form>
	</div>

	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />

</body>
</html>