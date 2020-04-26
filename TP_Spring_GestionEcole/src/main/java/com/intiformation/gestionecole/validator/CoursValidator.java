package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Cours;

@Component
public class CoursValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Cours.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate( Object coursValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Veuillez entrer un libellé.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "date", "required.date", "Veuillez entrer une date.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "duree", "required.duree", "Veuillez entrer une durée.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description", "required.description", "Veuillez entrer une description.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matiere", "required.matiere", "Veuillez entrer une matiere.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotion", "required.promotion", "Veuillez entrer une promotion.");
		
		Cours cours = (Cours) coursValid;
		
	}//end validate

	
	
}//end class
