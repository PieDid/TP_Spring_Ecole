package com.intiformation.gestionecole.dao;

import com.intiformation.gestionecole.domain.Promotion;

public interface IPromotionDao extends IGeneralDao<Promotion> {

	public void addPromotion(Promotion promotion);
	
	public void updatePromotion(Promotion promotion);
	
	public Promotion getByLibelle(String libelle);
	
	public void deletePromotion(String libelle);
	
}//end interface