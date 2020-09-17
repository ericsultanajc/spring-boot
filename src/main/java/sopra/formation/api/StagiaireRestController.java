package sopra.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import com.fasterxml.jackson.annotation.JsonView;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.model.Views;
import sopra.formation.repository.IEvaluationRepository;
import sopra.formation.repository.IStagiaireRepository;

@RestController
@RequestMapping("/api/stagiaire")
@CrossOrigin("*")
public class StagiaireRestController {

	@Autowired
	private IStagiaireRepository stagiaireRepo;

	@Autowired
	private IEvaluationRepository evaluationRepo;

	@GetMapping("")
	@JsonView(Views.ViewStagiaire.class)
	public List<Stagiaire> findAll() {
		return stagiaireRepo.findAll();
	}

	@GetMapping("/by-niveau/{niveau}/evaluation")
	@JsonView(Views.ViewEvaluation.class)
	public List<Evaluation> findAllEvaluationByNiveau(@PathVariable NiveauEtude niveau) {
		return evaluationRepo.findAllByStagiaireNiveau(niveau);
	}

	@GetMapping("/{id}/orphans")
	@JsonView(Views.ViewStagiaire.class)
	public List<Evaluation> findAllOrphanAndCurrentStagiaire(@PathVariable Long id) {
		return evaluationRepo.findAllOrphanAndCurrentStagiaire(id);
	}

	@GetMapping("/by-ville/{ville}")
	@JsonView(Views.ViewStagiaire.class)
	public List<Stagiaire> findAllByVille(@PathVariable String ville) {
		return stagiaireRepo.findAllByVille(ville);
	}

	@GetMapping("/by-formateur/{nom}")
	@JsonView(Views.ViewStagiaire.class)
	public List<Stagiaire> findAllByFormateur(@PathVariable String nom) {
		return stagiaireRepo.findAllByFormateur(nom);
	}

	@GetMapping("/by-nom/{nom}")
	@JsonView(Views.ViewStagiaire.class)
	public List<Stagiaire> findAllByNom(@PathVariable String nom) {
		return stagiaireRepo.findAllByNom(nom + "%");
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire find(@PathVariable Long id) {

		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		if (optStagiaire.isPresent()) {
			return optStagiaire.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@GetMapping("/{id}/detail")
	@JsonView(Views.ViewStagiaireDetail.class)
	public Stagiaire findDetail(@PathVariable Long id) {

		Optional<Stagiaire> optStagiaire = stagiaireRepo.findById(id);

		if (optStagiaire.isPresent()) {
			return optStagiaire.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@PostMapping("")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire create(@RequestBody Stagiaire stagiaire) {
		if (stagiaire.getEvaluation() != null && stagiaire.getEvaluation().getId() == null) {
			stagiaire.setEvaluation(null);
		}
		stagiaire = stagiaireRepo.save(stagiaire);

		return stagiaire;
	}

	@PutMapping("/{id}")
	@JsonView(Views.ViewStagiaire.class)
	public Stagiaire update(@RequestBody Stagiaire stagiaire, @PathVariable Long id) {
		if (!stagiaireRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		if (stagiaire.getEvaluation() != null && stagiaire.getEvaluation().getId() == null) {
			stagiaire.setEvaluation(null);
		}
		
		stagiaire = stagiaireRepo.save(stagiaire);

		return stagiaire;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		stagiaireRepo.deleteById(id);
	}

//	public List<Stagiaire> search(@RequestBody StagiaireFilter filter) {
//
//		
//		List<Stagiaire> stagiaires = stagiaireRepo.search(filter.getNom(), filter.getAnnee(), filter.getNiveauEtude(), filter.getVille());
//		
//		return stagiaires;
//	}
}
