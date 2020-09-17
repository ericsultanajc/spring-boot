package sopra.formation.api;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.api.exception.EvaluationValidationException;
import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Views;
import sopra.formation.repository.IEvaluationRepository;

@RestController
@RequestMapping ("/api/evaluation")
@CrossOrigin("*")
public class EvaluationRestController {

	@Autowired
	private IEvaluationRepository evaluationRepo;

	@GetMapping("")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAll() {
		return evaluationRepo.findAll();
	}

	@GetMapping("/orphans/{stagiaireId}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllOrphanAndCurrentStagiaire(@PathVariable Long stagiaireId) {
		return evaluationRepo.findAllOrphanAndCurrentStagiaire(stagiaireId);
	}

	@GetMapping("/by-technique/{technique}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllByTechnique(@PathVariable Integer technique) {
		return evaluationRepo.findByTechnique(technique);
	}

	@GetMapping("/by-gt-comp-and-technique/{comp}:{technique}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllByTechnique(@PathVariable("comp") Integer comportemental,
			@PathVariable Integer technique) {
		return evaluationRepo.findByComportementalGreaterThanAndTechniqueGreaterThan(comportemental, technique);
	}

	@GetMapping("/by-technique-and-comportemental/{technique}|{comportemental}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findByComportementalGreaterThanAndTechniqueGreaterThan(@PathVariable Integer technique, @PathVariable Integer comportemental) {
		return evaluationRepo.findByComportementalGreaterThanAndTechniqueGreaterThan(comportemental,technique);
	}
	
	@GetMapping("/by-niveau/{niveauEtude}")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllByNiveau(@PathVariable NiveauEtude niveauEtude) {
		return evaluationRepo.findAllByStagiaireNiveau(niveauEtude);
	}
	
	@GetMapping("/orphans")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllOrphan() {
		return evaluationRepo.findAllOrphan();
	}
	
	@GetMapping("/{id}")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation find(@PathVariable Long id) {

		Optional<Evaluation> optEvaluation = evaluationRepo.findById(id);

		if (optEvaluation.isPresent()) {
			return optEvaluation.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@PostMapping("")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation create(@Valid @RequestBody Evaluation evaluation, BindingResult result) {
		if (result.hasErrors()) {
			throw new EvaluationValidationException();
		}

		evaluation = evaluationRepo.save(evaluation);

		return evaluation;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation update(@RequestBody Evaluation evaluation, @PathVariable Long id) {
		if (!evaluationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		evaluation = evaluationRepo.save(evaluation);

		return evaluation;
	}

	@PatchMapping("/{id}")
	@JsonView(Views.ViewEvaluation.class)
	public Evaluation partialUpdate(@RequestBody Map<String, Object> updates, @PathVariable Long id) {
		if (!evaluationRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		Evaluation evaluationFind = evaluationRepo.findById(id).get();

		if (updates.containsKey("comportemental")) {
			evaluationFind.setComportemental((Integer) updates.get("comportemental"));
		}
		if (updates.containsKey("technique")) {
			evaluationFind.setTechnique((Integer) updates.get("technique"));
		}
		if (updates.containsKey("commentaires")) {
			evaluationFind.setCommentaires((String) updates.get("commentaires"));
		}

		evaluationFind = evaluationRepo.save(evaluationFind);

		return evaluationFind;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		evaluationRepo.deleteById(id);
	}
}
