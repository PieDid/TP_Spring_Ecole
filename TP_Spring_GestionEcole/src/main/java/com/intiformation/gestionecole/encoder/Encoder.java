package com.intiformation.gestionecole.encoder;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class Encoder {
	
	public static String crypt(String mdp) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		
		// cryptage du mot de passe avec la méthode encode()
		String hashedMotDePasse = passwordEncoder.encode(mdp);
		
		return hashedMotDePasse;
	}

}
