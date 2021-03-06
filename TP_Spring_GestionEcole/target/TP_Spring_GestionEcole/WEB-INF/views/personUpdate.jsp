<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de modification d'une personne</title>
</head>
<body>

	<div align="center">
		<h1>Modification des informations d'une personne</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="personUpdateCommand" method="POST" action="${pageContext.request.contextPath}/personUpdate-meth">
		
			<table>
			
				<tr>
					<td>
						<form:hidden path="identifiant"/>
					</td>
				</tr>
				
				<tr>
					<td> <form:label path="nom">Nom : </form:label> </td>
					<td> <form:input path="nom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="prenom">Pr�nom : </form:label> </td>
					<td> <form:input path="prenom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="adresse">Adresse : </form:label> </td>
					<td> <form:input path="adresse"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="email">Adresse Mail : </form:label> </td>
					<td> <form:input path="email"/> </td>
				</tr>
				
				<tr>
					<td colspan="2">
						<input type="submit" value="Modifier">
					</td>
				</tr>
								
			</table>
		</form:form>
	</div>

</body>
</html>