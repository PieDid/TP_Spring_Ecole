<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



</head>




<body >

		<div style="text-align: center">
		<img src="assets/images/forbidden.jpg" height="300px" width="300px"/>
		<br/>
		<span  style=" font-size: 48px">
			Statut http 403: accès refusé
		</span>

		<br/><br/>
	
		<span  style=" font-size: 24px">
			Vous n'avez pas les authorisations requises pour cette action
		</span>
	
		<h3> <a href="<c:url value='/login.jsp'/>">Se connecter</a> || <a href="<c:url value='/index.jsp'/>">Page d'accueil</a></h3>
		</div>
	

	
	

</body>
</html>