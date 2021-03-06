<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page d'ajout d'une personne</title>
</head>
<body>

	<div align="center">
		<h1>Ajout d'une nouvelle personne</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="personAddCommand" method="POST" action="${pageContext.request.contextPath}/personAdd-meth">
		
		<form:errors path="*" cssClass="error_validation" element="div" />
		
			<table>
				<tr>
					<td> <form:label path="nom">Nom : </form:label> </td>
					<td> <form:input path="nom"/> </td>
					<td> <form:errors path="nom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="prenom">Pr�nom : </form:label> </td>
					<td> <form:input path="prenom"/> </td>
					<td> <form:errors path="prenom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="adresse">Adresse : </form:label> </td>
					<td> <form:input path="adresse"/> </td>
					<td> <form:errors path="adresse"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="email">Adresse Mail : </form:label> </td>
					<td> <form:input path="email"/> </td>
					<td> <form:errors path="email"/> </td>
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