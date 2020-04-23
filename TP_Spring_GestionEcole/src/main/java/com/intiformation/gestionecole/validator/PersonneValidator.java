package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Personne;

@Component
public class PersonneValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Personne.class.isAssignableFrom(clazz);
	
}//end supports

	@Override
	public void validate(Object objetValid, Errors errors) {

		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nom", "required.nom", "Veuillez entrer votre nom.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "prenom", "required.prenom", "Veuillez entrer votre prénom.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "adresse", "required.nom", "Veuillez entrer votre adresse.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "email", "required.email", "Veuillez entrer votre adresse mail.");
	
		Personne personne = (Personne) objetValid;
		
		}//end validate
		
	}//end class
