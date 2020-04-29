package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Enseigne;

@Component
public class EnseigneValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Enseigne.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object matValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "enseignant", "required.enseignant", "Veuillez entrer un enseignant.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "matiere", "required.matiere", "Veuillez entrer une matière.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "promotion", "required.promotion", "Veuillez entrer une promotion.");
		
		Enseigne enseigne =  (Enseigne) matValid;
		
	}
}
