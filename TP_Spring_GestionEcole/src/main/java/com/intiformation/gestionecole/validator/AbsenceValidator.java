package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.EtudiantCours;

@Component
public class AbsenceValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return EtudiantCours.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object absValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "absence", "required.absence", "Veuillez entrer noter l'absence.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "motif", "required.motif", "Veuillez entrer le motif de l'absence.");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "etudiant", "required.etudiant", "Veuillez entrer le numéro de l'étudiant absent.");	
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "cours", "required.cours", "Veuillez entrer le cours où l'étudiant était absent.");
		
		EtudiantCours absence = (EtudiantCours) absValid;
		
	}//end validate

}//end class
