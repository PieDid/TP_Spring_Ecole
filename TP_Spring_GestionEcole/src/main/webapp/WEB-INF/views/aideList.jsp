<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Page de Liste de l'aide</title>
</head>
<body>
<!-- récupérée grace à la méthode generateCoursList du controlleur CoursController-->
	<h2>Liste des cours</h2>

	<table>
		<tr>
			<th>Identifiant</th>
			<th>Page</th>
			<th>Contenu</th>


		</tr>
		<c:forEach items="${attribut_listeAide}" var="aide">

			<tr>
				<td>${aide.idAide}</td>
				<td>${aide.page}</td>
				<td>${aide.contenu}</td>

				<!-- appelle vers la méthode afficherFormulaireUpdateCours du AideController, 
					redirigant vers le formulaire pour udpater l'aide -->
				<td colspan="2"><a href="aideUpdate?aideId=${aide.idAide}">Modifier</a>
				</td>

				<!-- appelle vers la méthode supprimerAide du AideController -->
				<td colspan="2"><a href="delete?aideId=${aide.idAide}">Supprimer</a>
				</td>
			</tr>
		</c:forEach>
		<!-- appelle vers la méthode afficherFormulaireAddCours du AideController, 
					redirigant vers le formulaire pour ajouter l'aide -->
		<tr>
			<td colspan="5"><a href="aideAdd-form">Ajouter</a></td>
		</tr>
	</table>
	<hr />

</body>
</html>