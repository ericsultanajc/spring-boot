package sopra.formation;

import static org.junit.jupiter.api.Assertions.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import sopra.formation.model.Adresse;
import sopra.formation.model.Civilite;
import sopra.formation.model.Dispositif;
import sopra.formation.model.Evaluation;
import sopra.formation.model.Filiere;
import sopra.formation.model.Formateur;
import sopra.formation.model.NiveauEtude;
import sopra.formation.model.Stagiaire;
import sopra.formation.repository.IEvaluationRepository;
import sopra.formation.repository.IFiliereRepository;
import sopra.formation.repository.IFormateurRepository;
import sopra.formation.repository.IStagiaireRepository;

@SpringBootTest
class SpringFormationBootApplicationTests {
	@Autowired
	private IEvaluationRepository evaluationDao;

	@Autowired
	private IFiliereRepository filiereDao;

	@Autowired
	private IFormateurRepository formateurDao;

	@Autowired
	private IStagiaireRepository stagiaireDao;

	@Test
	public void evaluation() {
		int startSize = evaluationDao.findAll().size();

		Evaluation evaluation = new Evaluation(12, 15, "Bonne évolution");

		evaluation = evaluationDao.save(evaluation);

		Optional<Evaluation> optEvaluation = evaluationDao.findById(evaluation.getId());

		if (optEvaluation.isPresent()) {
			fail("Erreur sur findById");
		}

		Evaluation evaluationFind = optEvaluation.get();

		assertEquals((Integer) 12, evaluationFind.getComportemental());
		assertEquals((Integer) 15, evaluationFind.getTechnique());
		assertEquals("Bonne évolution", evaluationFind.getCommentaires());

		evaluation.setComportemental(16);
		evaluation.setTechnique(13);
		evaluation.setCommentaires("Baisse de régime");

		evaluation = evaluationDao.save(evaluation);

		optEvaluation = evaluationDao.findById(evaluation.getId());

		if (!optEvaluation.isPresent()) {
			fail("Erreur sur findById");
		}

		evaluationFind = optEvaluation.get();

		assertEquals((Integer) 16, evaluationFind.getComportemental());
		assertEquals((Integer) 13, evaluationFind.getTechnique());
		assertEquals("Baisse de régime", evaluationFind.getCommentaires());

		int testSize = evaluationDao.findAll().size();

		if ((testSize - startSize) != 1) {
			fail("FindAll size en erreur");
		}

		evaluationDao.delete(evaluation);

		optEvaluation = evaluationDao.findById(evaluation.getId());

		if (!optEvaluation.isPresent()) {
			fail("Erreur sur delete");
		}

		int endSize = evaluationDao.findAll().size();

		assertEquals(0, (endSize - startSize));
	}

//	@Test
	public void stagiaire() throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

		Formateur eric = new Formateur("e.sultan@ajc-ingenierie.fr");
		eric.setNom("SULTAN");

		eric = formateurDao.save(eric);

		Filiere covid = new Filiere("JAVA SPRING ANGULAR", "COVID", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		covid.setReferent(eric);
		covid = filiereDao.save(covid);
		Filiere elysee = new Filiere("JAVA SPRING ANGULAR", "Elysée", sdf.parse("09/03/2020"), 57, Dispositif.POEI);
		elysee.setReferent(eric);
		elysee = filiereDao.save(elysee);

		Evaluation evalCecile = new Evaluation(14, 17, "RAS");
		evalCecile = evaluationDao.save(evalCecile);
		Evaluation evalCecilia = new Evaluation(18, 19, "Au top");
		evalCecilia = evaluationDao.save(evalCecilia);

		int startSize = stagiaireDao.findAll().size();
//		int startSizeByVille = stagiaireDao.findAllByVille("Paris").size();
//		int startSizeByFormateur = stagiaireDao.findAllByFormateur("SULTAN").size();

		Stagiaire cecile = new Stagiaire("cecile.larrouy@outlook.fr");
		cecile.setCivilite(Civilite.MLLE);
		cecile.setNom("LARROUY");
		cecile.setPrenom("Cécile");
		cecile.setTelephone("0608050400");
		cecile.setDtNaissance(sdf.parse("23/04/1994"));
		cecile.setNiveauEtude(NiveauEtude.BAC_5);
		cecile.setAdresse(new Adresse("93 Boulevard Georges V", "Résidence Zola", "33400", "Talence"));
		cecile.setEvaluation(evalCecile);
		cecile.setFiliere(covid);

		cecile = stagiaireDao.save(cecile);

		Optional<Stagiaire> optStagiaire = stagiaireDao.findById(cecile.getId());

		if (!optStagiaire.isPresent()) {
			fail("Erreur sur findById");
		}

		Stagiaire cecileFind = optStagiaire.get();

		assertEquals(Civilite.MLLE, cecileFind.getCivilite());
		assertEquals("LARROUY", cecileFind.getNom());
		assertEquals("Cécile", cecileFind.getPrenom());
		assertEquals("cecile.larrouy@outlook.fr", cecileFind.getEmail());
		assertEquals("0608050400", cecileFind.getTelephone());
		assertEquals(sdf.parse("23/04/1994"), cecileFind.getDtNaissance());
		assertEquals(NiveauEtude.BAC_5, cecileFind.getNiveauEtude());
		assertEquals(new Adresse("93 Boulevard Georges V", "Résidence Zola", "33400", "Talence"),
				cecileFind.getAdresse());
		assertNotNull(cecileFind.getEvaluation());
		assertEquals(evalCecile.getId(), cecileFind.getEvaluation().getId());
		assertNotNull(cecileFind.getFiliere());
		assertEquals(covid.getId(), cecileFind.getFiliere().getId());

		cecile.setCivilite(Civilite.MME);
		cecile.setNom("SARKOZY");
		cecile.setPrenom("Cécilia");
		cecile.setEmail("cecilia.sarkory@outlook.fr");
		cecile.setTelephone("0606060606");
		cecile.setDtNaissance(sdf.parse("23/04/1954"));
		cecile.setNiveauEtude(NiveauEtude.BAC_8);
		cecile.setAdresse(new Adresse("55 Rue du Faubourg Saint-Honoré", "", "75008", "Paris"));
		cecile.setEvaluation(evalCecilia);
		cecile.setFiliere(elysee);

		cecile = stagiaireDao.save(cecile);

		optStagiaire = stagiaireDao.findById(cecile.getId());

		if (!optStagiaire.isPresent()) {
			fail("Erreur sur findById");
		}

		cecileFind = optStagiaire.get();

		assertEquals(Civilite.MME, cecileFind.getCivilite());
		assertEquals("SARKOZY", cecileFind.getNom());
		assertEquals("Cécilia", cecileFind.getPrenom());
		assertEquals("cecilia.sarkory@outlook.fr", cecileFind.getEmail());
		assertEquals("0606060606", cecileFind.getTelephone());
		assertEquals(sdf.parse("23/04/1954"), cecileFind.getDtNaissance());
		assertEquals(NiveauEtude.BAC_8, cecileFind.getNiveauEtude());
		assertNotNull(cecileFind.getAdresse());
		assertEquals("55 Rue du Faubourg Saint-Honoré", cecileFind.getAdresse().getRue());
		assertEquals("", cecileFind.getAdresse().getComplement());
		assertEquals("75008", cecileFind.getAdresse().getCodePostal());
		assertEquals("Paris", cecileFind.getAdresse().getVille());
		assertNotNull(cecileFind.getEvaluation());
		assertEquals(evalCecilia.getId(), cecileFind.getEvaluation().getId());
		assertNotNull(cecileFind.getFiliere());
		assertEquals(elysee.getId(), cecileFind.getFiliere().getId());

		int testSize = stagiaireDao.findAll().size();

		if ((testSize - startSize) != 1) {
			fail("FindAll size en erreur");
		}

//		int testSizeByVille = stagiaireDao.findAllByVille("Paris").size();
//
//		Assert.assertEquals(1, testSizeByVille - startSizeByVille);
//
//		int testSizeByFormateur = stagiaireDao.findAllByFormateur("SULTAN").size();
//
//		Assert.assertEquals("Erreur dans la requête findAllByFormateur", 1, testSizeByFormateur - startSizeByFormateur);

		stagiaireDao.delete(cecile);

		optStagiaire = stagiaireDao.findById(cecile.getId());

		if (optStagiaire.isPresent()) {
			fail("Erreur sur delete");
		}

		evaluationDao.delete(evalCecilia);
		evaluationDao.delete(evalCecile);

		filiereDao.delete(elysee);
		filiereDao.delete(covid);
	}

}
