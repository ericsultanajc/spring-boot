package sopra.formation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import sopra.formation.model.Filiere;

public interface IFiliereRepository extends JpaRepository<Filiere, Long>{
	@Query("select f from Filiere f where f.promotion like :promotion")
	List<Filiere> findAllByPromotion(@Param ("promotion") String promotion);
	
	@Query("from Filiere f left join fetch f.referent r where f.id = :id")
	Optional<Filiere> findWithReferent(@Param("id") Long id);
	
	@Query("select f from Filiere f join f.ues ue join ue.salle s where s.adr.ville = :ville")
	List<Filiere> findAllByVille(@Param("ville") String ville);
}
