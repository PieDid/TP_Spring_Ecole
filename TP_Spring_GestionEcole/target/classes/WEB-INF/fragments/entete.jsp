<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<%-- taglib de spring --%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<%-- taglib de spring security --%>
<%@taglib prefix="s" uri="http://www.springframework.org/security/tags" %>

<%-- taglib de spring --%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>

<%-- ajout de bootstrap --%>
<spring:url value="/assets/styles/bootstrap.min.css" var="bootstrapCss" />
<link rel="stylesheet" href="${bootstrapCss}" />


<!-- ================================================================ -->
<!-- ================ Contenu par défaut du header ================== -->
<!-- ================================================================ -->
<header style="width:100%;">
	
		
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand" href="<c:url value='/index.jsp'/>">Accueil</a>
		<div class="collapse navbar-collapse" id="navbarNav">
		    <ul class="navbar-nav">	   
		      <s:authorize access="hasAnyRole('ROLE_ADMIN')"> 
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/etuList'/>">Liste étudiants</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/ensList'/>">Liste enseignants</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/adminList'/>">Liste administrateurs</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/matiereList'/>">Liste matières</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/coursList'/>">Liste cours</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/promotionList'/>">Liste promotions</a>
			      </li>
		      </s:authorize>
		      
		      <s:authorize access="hasAnyRole('ROLE_ENS')"> 
			   
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/matiereList'/>">Liste matières</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/coursList'/>">Liste cours</a>
			      </li>
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/promotionList'/>">Liste promotions</a>
			      </li>
		      </s:authorize>
		      
		      <s:authorize access="hasAnyRole('ROLE_ETU')"> 
			      <li class="nav-item">
			        <a class="nav-link" href="<c:url value='/coursList'/>">Liste cours</a>
			      </li>
		      </s:authorize>
		    </ul>
  		</div>
  		<form class="form-inline">
		    <!-- access = condition sur le rôle de l'utilisateur -->
			<s:authorize access="hasAnyRole('ROLE_ENS,ROLE_ETU,ROLE_ADMIN')">
			
			<!-- bouton se déconnecter -->
			<a href="<c:url value='/logout'/>" >Se Déconnecter</a>
			</s:authorize>
	
			<s:authorize access="hasRole('ROLE_ANONYMOUS')">
			<!-- bouton se Connecter -->
			<a href="<c:url value='/login.jsp'/>" >Se Connecter</a>
		</s:authorize>
		 </form>
	</nav>
	
	
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	  
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	  <div class="collapse navbar-collapse" id="navbarText">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item ">
	        <a class="nav-item nav-link" href="javascript:history.go(-1)">Retour</a>
	      </li>
	      <li class="nav-item">
	        <a class="nav-item nav-link" href="javascript:history.go(+1)">Suivant</a>
	      </li>
	      <li class="nav-item">
	        <form:form  method="POST" action="javascript:history.go(+0)" class="form-inline"> 
		      	<a class="nav-item nav-link" href="javascript:history.go(+0)">Actualiser</a>
		      </form:form>
	      </li>
	    </ul>
	    <span class="navbar-text">
	      Vous êtes connecté en tant qu'
					<s:authentication property="authorities" var="authorites"/>
					<c:forEach items="${authorites}" var="auth">
						<c:if test="${auth.authority == 'ROLE_ADMIN'}">administrateur</c:if>
						<c:if test="${auth.authority == 'ROLE_ENS'}">enseignant</c:if>
						<c:if test="${auth.authority == 'ROLE_ETU'}">étudiant</c:if>
						<c:if test="${auth.authority == 'ROLE_ANONYMOUS'}">anonyme</c:if>
					</c:forEach>
	    </span>
	  </div>
	</nav>
	

	
	<br/><br/>
	
	<hr/>

	
</header>