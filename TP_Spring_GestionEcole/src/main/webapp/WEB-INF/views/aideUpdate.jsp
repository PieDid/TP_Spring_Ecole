<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification de l'aide</title>
</head>
<body>

	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />

	<div align="center">
		<h1>Modification des informations d'une aide</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="aideUpdateCommand" method="POST" action="${pageContext.request.contextPath}/aideUpdate-meth">
		
			<table>
			
				<tr>
					<td>
						<form:hidden path="idAide"/>
					</td>
				</tr>
				
				<tr>
					<td> <form:label path="page">Page : </form:label> </td>
					<td> <form:input path="page"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="contenu">Contenu : </form:label> </td>
					<td> <form:input path="contenu"/> </td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="Modifier">
					</td>
				</tr>
								
			</table>
		</form:form>
	</div>
	
	<%-- inclusion dynamique du fragment piedDePage.jsp --%>
	<jsp:include page="/WEB-INF/fragments/piedDePage.jsp" />


</body>
</html>