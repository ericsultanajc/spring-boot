package sopra.formation.repository;

import java.util.List;

import org.springframework.data.repository.query.Param;

import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;

public interface IStagiaireRepositoryCustom {
	List<Stagiaire> search(@Param("nom") String nom, @Param("annee") Integer annee, @Param("niveau") NiveauEtude niveau, @Param("ville") String ville);
}
