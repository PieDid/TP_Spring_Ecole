package com.intiformation.gestionecole.validator;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.intiformation.gestionecole.domain.Promotion;

@Component
public class PromotionValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Promotion.class.isAssignableFrom(clazz);
	}//end supports

	@Override
	public void validate(Object promoValid, Errors errors) {
		
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "libelle", "required.libelle", "Veuillez entrer un libellé.");
	
		Promotion promo = (Promotion) promoValid;
		
	}//end validate

}//end class
