package sopra.formation.api.dto;

public class FiliereForm {
	private String intitule; // JAVA / SPRING / ANGULAR
	private String promotion; // Sopra Covid
	private String dtDebut; // 2020-03-09
	private Integer duree; // 57
	private String dispositif; // POEI
	private Long idFormateur; // 5

	public FiliereForm() {
		super();
	}

	public String getIntitule() {
		return intitule;
	}

	public void setIntitule(String intitule) {
		this.intitule = intitule;
	}

	public String getPromotion() {
		return promotion;
	}

	public void setPromotion(String promotion) {
		this.promotion = promotion;
	}

	public String getDtDebut() {
		return dtDebut;
	}

	public void setDtDebut(String dtDebut) {
		this.dtDebut = dtDebut;
	}

	public Integer getDuree() {
		return duree;
	}

	public void setDuree(Integer duree) {
		this.duree = duree;
	}

	public String getDispositif() {
		return dispositif;
	}

	public void setDispositif(String dispositif) {
		this.dispositif = dispositif;
	}

	public Long getIdFormateur() {
		return idFormateur;
	}

	public void setIdFormateur(Long idFormateur) {
		this.idFormateur = idFormateur;
	}

	

}
