<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une promotion</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<div align="center">
		<h2>Ajout d'une Promotion</h2>
	</div>

	<div align="center">

		<form:form modelAttribute="promotionAddCommand" method="POST"
			action="${pageContext.request.contextPath}/promotion/add">

			<form:errors path="*" cssClass="error_validation" element="div" />

			<table>
				<tr>
					<td><form:label path="libelle">Libellé : </form:label></td>
					<td><form:input path="libelle" /></td>
					<td><form:errors path="libelle" /></td>
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