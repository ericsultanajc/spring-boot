package sopra.formation.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonView;

@Entity
@Table(name = "classroom")
public class Salle {
	@Id
	@GeneratedValue
	@JsonView(Views.ViewCommon.class)
	private Long id;
	@Version
	@JsonView(Views.ViewCommon.class)
	private int version;
	@Column(name = "name", nullable = false)
	@JsonView(Views.ViewCommon.class)
	private String nom;
	@Column(name = "capacity")
	@JsonView(Views.ViewCommon.class)
	private Integer capacite;
	@Column(name = "video_projector")
	@JsonView(Views.ViewCommon.class)
	private Boolean videoProjecteur;
	@Embedded
	@JsonView(Views.ViewCommon.class)
	private Adresse adr;
	@OneToMany(mappedBy = "salle")
	@JsonView(Views.ViewSalleDetail.class)
	private List<UE> ues = new ArrayList<UE>();

	public Salle() {
		super();
	}

	public Salle(String nom) {
		super();
		this.nom = nom;
	}

	public Salle(String nom, Integer capacite, Boolean videoProjecteur) {
		super();
		this.nom = nom;
		this.capacite = capacite;
		this.videoProjecteur = videoProjecteur;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Integer getCapacite() {
		return capacite;
	}

	public void setCapacite(Integer capacite) {
		this.capacite = capacite;
	}

	public Boolean getVideoProjecteur() {
		return videoProjecteur;
	}

	public void setVideoProjecteur(Boolean videoProjecteur) {
		this.videoProjecteur = videoProjecteur;
	}

	public Adresse getAdr() {
		return adr;
	}

	public void setAdr(Adresse adr) {
		this.adr = adr;
	}

	public List<UE> getUes() {
		return ues;
	}

	public void setUes(List<UE> ues) {
		this.ues = ues;
	}

	public void addUe(UE ue) {
		this.ues.add(ue);
	}

	@Override
	public String toString() {
		return "Salle [id=" + id + ", nom=" + nom + ", capacite=" + capacite + ", videoProjecteur=" + videoProjecteur
				+ ", adr=" + adr + "]";
	}

}
