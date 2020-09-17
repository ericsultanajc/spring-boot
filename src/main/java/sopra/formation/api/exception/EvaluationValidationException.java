package sopra.formation.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Evaluation invalide")
public class EvaluationValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
}
