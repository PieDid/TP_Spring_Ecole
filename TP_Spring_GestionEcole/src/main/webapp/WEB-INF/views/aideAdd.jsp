<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une Aide</title>
</head>
<body>

<div align="center">
		<h2>Ajout d'une aide</h2>
	</div>

	<div align="center">
		
		<form:form modelAttribute="aideAddCommand" method="POST" action="${pageContext.request.contextPath}/aide/add-meth">
		
		<form:errors path="*" cssClass="error_validation" element="div" />
		
			<table>
				<tr>
					<td> <form:label path="page">Page : </form:label> </td>
					<td> <form:input path="page"/> </td>
					<td> <form:errors path="page"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="contenu">Contenu : </form:label> </td>
					<td> <form:input path="contenu"/> </td> 
					<td> <form:errors path="contenu"/> </td>
				</tr>

				
				<tr>
					<td colspan="2">
						<input type="submit" value="Ajouter">
					</td>
				</tr>
								
			</table>
		</form:form>
	</div>


</body>
</html>