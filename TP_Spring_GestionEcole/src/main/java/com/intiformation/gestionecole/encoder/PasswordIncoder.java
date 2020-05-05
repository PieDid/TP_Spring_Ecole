package com.intiformation.gestionecole.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordIncoder {

		// Déclaration du mot de passe à crypter
		private static final String MOT_DE_PASSE = "123";
		private static final String MDP_ADMIN = "456";
		
		/**
		 * Main method
		 * @param args
		 */
		public static void main(String[] args) {
			
			// Objet pour le cryptage
			BCryptPasswordEncoder pe = new BCryptPasswordEncoder();
			
			// Cryptage du mot de passe avec la méthode encode()
			String hashedMdp = pe.encode(MOT_DE_PASSE);
			String hashedAdmin = pe.encode(MDP_ADMIN);
			
			// Affichage du mot de passe cryptée
			System.out.println(hashedMdp);
			System.out.println(hashedAdmin);
			
		}

		
	}//end class
