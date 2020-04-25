package com.intiformation.gestionecole.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Matiere;

public class MatiereValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Matiere.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object matValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Veuillez entrer un libellé.");
		
		Matiere matiere = (Matiere) matValid;
		
	}

}//end class
