package sopra.formation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Evaluation;
import sopra.formation.model.NiveauEtude;

public interface IEvaluationRepository extends JpaRepository<Evaluation, Long>{
	List<Evaluation> findByTechnique(@Param("technique") Integer technique);
	
	List<Evaluation> findByComportementalGreaterThanAndTechniqueGreaterThan(@Param("comportemental") Integer comportemental,@Param("technique") Integer technique);

	@Query("select s.evaluation from Stagiaire s where s.niveauEtude = :niveau")
	List<Evaluation> findAllByStagiaireNiveau(@Param("niveau") NiveauEtude niveauEtude);
	
	@Query("select e from Personne p right outer join p.evaluation e where p.evaluation is null order by e.id")
	List<Evaluation> findAllOrphan();
	
	@Query("select e from Personne p right outer join p.evaluation e where p.evaluation is null or p.id = :id order by e.id")
	List<Evaluation> findAllOrphanAndCurrentStagiaire(@Param("id") Long stagiaireId);
}
