<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- taglib de spring --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- taglib de spring security --%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>


<!-- ================================================================ -->
<!-- ================ Contenu par défaut du header ================== -->
<!-- ================================================================ -->
<header style="width:100%;">
	
	<a href="javascript:history.go(-1)">Retour</a>
	<a href="javascript:history.go(+1)">Suivant</a>
	<form:form  method="POST" action="javascript:history.go(+0)"> <a href="javascript:history.go(+0)">Actualiser</a></form:form>
	
	<!-- ================================================================ -->
	<!-- ========== affichage du bouton connexion/déconnexion =========== -->
	<!-- ================================================================ -->
	<div align="center">
		<!-- access = condition sur le rôle de l'utilisateur -->
		<s:authorize access="hasAnyRole('ROLE_USER', 'ROLE_ADMIN')">
			<!-- bouton se déconnecter -->
			<a href="<c:url value='/logout'/>" >Se Déconnecter</a>
		</s:authorize>
	
		<s:authorize access="hasRole('ROLE_ANONYMOUS')">
			<!-- bouton se Connecter -->
			<a href="<c:url value='/login.jsp'/>" >Se Connecter</a>
		</s:authorize>
	</div>

	<br/><br/>
	<!-- ================================================================ -->
	<!-- =========== affichage de l'identifiant et des rôles ============ -->
	<!-- ================================================================ -->
	<div style="border: 1px dotted red; width: 400px;">
	
		<!-- affichage de l'identifiant de l'utilisateur -->
		<h3 align="center">
			Bienvenue, <s:authentication property="name"/>
		</h3>	
		
		<!-- affichage des rôles de l'utilisateur -->
		<s:authentication property="authorities" var="authorites"/>
		
		<ul>
			<c:forEach items="${authorites}" var="auth">
				<li>${auth.authority}</li>
			</c:forEach>
		</ul>
	
	</div>
	
	<br/><br/>
	
	<hr/>

	
</header>