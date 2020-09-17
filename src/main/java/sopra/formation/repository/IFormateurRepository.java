package sopra.formation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;

public interface IFormateurRepository extends JpaRepository<Formateur, Long> {
	@Query("select form from Filiere f join f.referent form where f = ?1")
	Formateur findByFiliere(Filiere filiere);
	Formateur findByEmail(String email);
}
