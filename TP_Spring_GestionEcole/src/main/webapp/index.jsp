<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%-- Redirection vers l'URL '' (appel du Controller MessageListController) --%>
	<%
		//response.sendRedirect("");
	%>
	
	<!-- ======================================================== -->
	<!-- ========= Affichage du message de déconnexion ========== -->
	<!-- ======================================================== -->
	
	<c:if test="${not empty param.logout_message}">
		<font color="green;">
			Déconnexion avec succès
		</font>
	
	</c:if>
	
	<!-- ============================================================= -->
	<!-- ======== affichage du bouton connexion/deconnexion ========== -->
	<!-- ============================================================= -->
	<div align="center">
		<!-- condition sur le rôle de l'utilisateur -->
		<s:authorize access="hasAnyRole('ROLE_ENS,ROLE_ETU,ROLE_ADMIN')">
				<!-- bouton se déconnecter -->
				<a href="<c:url value='/logout'/>">Se Déconnecter</a>
		</s:authorize>
		
		<s:authorize access="hasRole('ROLE_ANONYMOUS')">
				<!-- bouton se Connecter -->
				<a href="<c:url value='/login.jsp'/>">Se Connecter</a>
		
		</s:authorize>
		
	<!-- =========================================================== -->
	<!-- ======== affichage de l'identifiant et des rôles ========== -->
	<!-- =========================================================== -->
	<div style="border: 1px dotted red; width: 400px;">
			<!-- affichage de l'identifiant de l'utilisateur -->
			<h3>
				Bienvenue, <s:authentication property="name"/>
			</h3>
			
			<!-- affichage des rôle de l'utilisateur -->
			<s:authentication property="authorities" var="authorites"/>
			
			<ul>
				<c:forEach items="${authorites}" var="auth">
					<li>${auth.authority}</li>	
				</c:forEach>
			</ul>
	</div>
	
	<div style="text-align:center">
		<h1>Page d'accueil</h1>
	
	<a href="${pageContext.request.contextPath}/adminList">Liste des Admins</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/aideList">Liste des aides</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/coursList">Liste des cours</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/enseignantList">Liste des enseignants</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/etudiantList">Liste des étudiants</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/matiereList">Liste des matières</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/personList">Liste des personnes</a>
	<br><br>
	<a href="${pageContext.request.contextPath}/promotionList">Liste des promotions</a>
	</div>
	
	

</body>
</html>