<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'un cours</title>
</head>
<body>
	<%-- inclusion dynamique du fragment entete.jsp --%>
	<jsp:include page="/WEB-INF/fragments/entete.jsp" />
	
	<div align="center">
		<h1>Modification des informations d'un cours</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="coursUpdateCommand" method="POST" action="${pageContext.request.contextPath}/coursUpdate-meth">
		
			<table>
			
				<tr>
					<td>
						<form:hidden path="idCours"/>
					</td>
				</tr>
				
				<tr>
					<td> <form:label path="libelle">Libellé : </form:label> </td>
					<td> <form:input path="libelle"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="matiere">Matière : </form:label> </td>
					<td> <form:input path="matiere"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="date">Date : </form:label> </td>
					<td> <form:input path="date"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="duree">Durée : </form:label> </td>
					<td> <form:input path="duree"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="description">Description : </form:label> </td>
					<td> <form:input path="description"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="promotion">Promotion : </form:label> </td>
					<td> <form:input path="promotion"/> </td>
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