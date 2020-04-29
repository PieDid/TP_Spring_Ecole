package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Adresse;

@Component
public class AdresseValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Adresse.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object adresseValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "rue", "required.rue", "Veuillez entrer le nom de la rue.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "codePostal", "required.codePostal", "Veuillez entrer le code postal.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "ville", "required.ville", "Veuillez entrer la ville.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "personne", "required.personne", "Veuillez entrer le nom de la personne habitant à cette adresse.");
		
		Adresse adresse = (Adresse) adresseValid;
		
	}//end validate

}//end class
