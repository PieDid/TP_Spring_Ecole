<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<div align="center">
		<h1>Modification des informations d'une Promotion</h1>
	</div>

	<div align="center">

		<form:form modelAttribute="promotionUpdateCommand" method="POST"
			action="${pageContext.request.contextPath}/promotionUpdate-meth">

			<table>

				<tr>
					<td><form:label path="libelle">Libellé : </form:label></td>
					<td><form:input path="libelle" /></td>
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