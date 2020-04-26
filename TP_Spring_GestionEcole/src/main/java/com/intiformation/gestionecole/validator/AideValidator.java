package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Aide;

@Component
public class AideValidator implements Validator{

	@Override
	public boolean supports(Class<?> clazz) {
		return Aide.class.isAssignableFrom(clazz);
	} // end supports()

	@Override
	public void validate(Object aideValid, Errors errors) {
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "page", "required.page", "Veuillez entrer une page.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "contenu", "required.contenu", "Veuillez entrer un contenu.");
		
		
		Aide aide = (Aide) aideValid;
	} // end validate()

} // end class
