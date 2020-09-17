package sopra.formation.api;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

import sopra.formation.model.Salle;
import sopra.formation.model.Views;
import sopra.formation.repository.ISalleRepository;

@RestController
@RequestMapping("/api/salle")
public class SalleRestController {

	@Autowired
	private ISalleRepository salleRepo;

	@GetMapping("")
	@JsonView(Views.ViewSalle.class)
	public List<Salle> findAll() {
		return salleRepo.findAll();
	}
	
	@GetMapping("/by-filiere/{idFiliere}")
	@JsonView(Views.ViewSalle.class)
	public List<Salle> findAllByFiliere(@PathVariable Long idFiliere) {
		return salleRepo.findAllByFiliere(idFiliere);
	}
	
	@GetMapping("/by-ville/{ville}")
	@JsonView(Views.ViewSalle.class)
	public List<Salle> findAllByVille(@PathVariable String ville) {
		return salleRepo.findAllByVille(ville);
	}

	@GetMapping("/{id}")
	@JsonView(Views.ViewSalle.class)
	public Salle find(@PathVariable Long id) {

		Optional<Salle> optSalle = salleRepo.findById(id);

		if (optSalle.isPresent()) {
			return optSalle.get();
		} else {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}
	}

	@PostMapping("")
	public Salle create(@RequestBody Salle salle) {
		salle = salleRepo.save(salle);

		return salle;
	}

	@PutMapping("/{id}")
	public Salle update(@RequestBody Salle salle, @PathVariable Long id) {
		if (!salleRepo.existsById(id)) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Unable to find resource");
		}

		salle = salleRepo.save(salle);

		return salle;
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id) {
		salleRepo.deleteById(id);
	}
}
