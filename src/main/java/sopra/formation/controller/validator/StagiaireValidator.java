package sopra.formation.controller.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import sopra.formation.model.Stagiaire;

public class StagiaireValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		return Stagiaire.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Stagiaire stagiaire = (Stagiaire) target;

		if (stagiaire.getAdresse() != null) {
			if (!stagiaire.getAdresse().getRue().isEmpty()) {
				if (stagiaire.getAdresse().getCodePostal().isEmpty()) {
					errors.rejectValue("adresse.codePostal", "adresseCodePostal", "Le code postal doit être renseigné");
				}

				if (stagiaire.getAdresse().getVille().isEmpty()) {
					errors.rejectValue("adresse.ville", "adresseVille", "La ville doit être renseignée");
				}
			}
		}
	}

}
