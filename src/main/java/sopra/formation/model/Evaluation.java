package sopra.formation.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonView;

@Entity // obligatoire
@Table(name = "rating") // optionnel
public class Evaluation {
	@Id // obligatoire
	@GeneratedValue // optionnelle
	@JsonView(Views.ViewCommon.class)
	private Long id;
	@Version
	@JsonView(Views.ViewCommon.class)
	private int version;
	@Column(name = "behaviour", nullable = false)
	@NotNull
	@JsonView(Views.ViewCommon.class)
	private Integer comportemental;
	@Column(name = "technical")
	@JsonView(Views.ViewCommon.class)
	private Integer technique;
	@Column(name = "comments", length = 4000)
	@JsonView(Views.ViewCommon.class)
	private String commentaires;

	public Evaluation() {
		super();
	}

	public Evaluation(Integer comportemental, Integer technique, String commentaires) {
		super();
		this.comportemental = comportemental;
		this.technique = technique;
		this.commentaires = commentaires;
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

	public Integer getComportemental() {
		return comportemental;
	}

	public void setComportemental(Integer comportemental) {
		this.comportemental = comportemental;
	}

	public Integer getTechnique() {
		return technique;
	}

	public void setTechnique(Integer technique) {
		this.technique = technique;
	}

	public String getCommentaires() {
		return commentaires;
	}

	public void setCommentaires(String commentaires) {
		this.commentaires = commentaires;
	}

	@Override
	public String toString() {
		return "Evaluation [id=" + id + ", comportemental=" + comportemental + ", technique=" + technique
				+ ", commentaires=" + commentaires + "]";
	}

}
