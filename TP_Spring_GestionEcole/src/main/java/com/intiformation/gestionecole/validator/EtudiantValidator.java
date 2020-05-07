package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Etudiant;

@Component
public class EtudiantValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Etudiant.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object etuValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "required.nom", "Veuillez entrer votre nom.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "required.prenom", "Veuillez entrer votre prénom.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adresse", "required.adresse", "Veuillez entrer votre adresse.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Veuillez entrer votre adresse mail.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "photo", "required.photo", "Veuillez entrer une photo.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "dateDeNaissance", "required.dateDeNaissance", "Veuillez entrer votre date de naissance.");
		
		Etudiant etudiant = (Etudiant) etuValid;
		
	}//end validate


	
	
}//end class
