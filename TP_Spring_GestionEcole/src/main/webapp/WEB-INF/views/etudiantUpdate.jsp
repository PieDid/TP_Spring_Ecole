<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>


	<div align="center">
		<h1>Modification des informations d'un étudiant</h1>
	</div>	

	<div align="center">
		
		<form:form modelAttribute="etuUpdateCommand" method="POST" action="${pageContext.request.contextPath}/etuUpdate-meth">
		
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
					<td> <form:label path="prenom">Prénom : </form:label> </td>
					<td> <form:input path="prenom"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="adresse.idAdresse">Adresse : </form:label> </td>
					<td> <form:input path="adresse.idAdresse"/> </td>
					<td>
						<form:select items="${listeAdresses}" path="" >
						<c:forEach items="${listeAdresses}" var="adresse2">
                  			<form:option value="${adresse2}">${adresse2}</form:option>
    					</c:forEach>
    					</form:select>
					</td>
				</tr>
				
				<tr>
					<td> <form:label path="email">Adresse Mail : </form:label> </td>
					<td> <form:input path="email"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="dateDeNaissance">Date de naissance : </form:label> </td>
					<td> <form:input path="dateDeNaissance"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="photo">Photo : </form:label> </td>
					<td> <form:input path="photo"/> </td>
				</tr>
				
				<tr>
					<td> <form:label path="role">Rôle : </form:label> </td>
<%-- 					<td> <form:input path="role"/> </td> --%>
					<td>
						<form:select path="role">
							<form:option value="ADM" label="Administrateur"/>
							<form:option value="ENS" label="Enseignant"/>
							<form:option value="ETU" label="Etudiant"/>
							
							<form:options items="${ role }" />
						</form:select>
					</td>
				</tr>
				
				<tr>
					<td> <form:label path="motDePasse">Mot De Passe : </form:label> </td>
					<td> <form:input path="motDePasse"/> </td>
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