package com.intiformation.gestionecole.apptest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.intiformation.gestionecole.dao.AideDao;
import com.intiformation.gestionecole.dao.IAideDao;
import com.intiformation.gestionecole.domain.Aide;

/**
 * App Test de la classe Aide
 * @author IN-DF-028
 *
 */
public class AppTestAide {

	public static void main(String[] args) {
		
		// 3. création d'entité Aide
		Aide aide1 = new Aide("accueil", "Cette page affiche l'accueil. Vous pouvez effectuer les actions suivantes :\n"
										+"														- agir sur la liste des élèves\n"
										+"														- afficher la liste de enseignants");
		
		Aide aide2 = new Aide("liste_eleves", "Cette page affiche la liste des élèves. Vous pouvez ajouter, modifier ou supprimer des élèves.");
		
		
		System.out.println("aide1 : " + aide1);
		System.out.println("aide2 : " + aide2);
		
	} // end main
	
} // end class
