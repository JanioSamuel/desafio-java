package br.com.biblioteca.model.project.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.biblioteca.model.person.entity.Person;
import br.com.biblioteca.model.project.enumerator.RiskRating;
import br.com.biblioteca.model.project.enumerator.Status;
import br.com.biblioteca.util.EnumRiskRatingConverter;
import br.com.biblioteca.util.EnumStatusConverter;

@Entity
@Table(name = "projeto")
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NotNull
	@Column(name = "nome")
	private String name;// VARCHAR(200) NOT NULL,

	@Column(name = "data_inicio")
	private LocalDate startDate;//data_inicio DATE ,

	@Column(name = "data_previsao_fim")
	private LocalDate expectedEndDate;//data_previsao_fim DATE ,

	@Column(name = "data_fim")
	private LocalDate endDate;//data_fim DATE ,

	@Column(name = "descricao")
	private String description;//descricao VARCHAR(5000) ,

	@Column(name = "status")
	@Convert(converter = EnumStatusConverter.class)
	private Status status;// VARCHAR(45) ,

	@Column(name = "orcamento")
	private float budget;//orcamento FLOAT ,

	@Column(name = "risco")
	@Convert(converter = EnumRiskRatingConverter.class)
	private RiskRating risk;// VARCHAR(45)

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idgerente", referencedColumnName = "id")
	private Person manager;//idgerente bigserial NOT NULL,

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getExpectedEndDate() {
		return expectedEndDate;
	}

	public void setExpectedEndDate(LocalDate expectedEndDate) {
		this.expectedEndDate = expectedEndDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public float getBudget() {
		return budget;
	}

	public void setBudget(float budget) {
		this.budget = budget;
	}

	public RiskRating getRisk() {
		return risk;
	}

	public void setRisk(RiskRating risk) {
		this.risk = risk;
	}

	public Person getManager() {
		return manager;
	}

	public void setManager(Person manager) {
		this.manager = manager;
	}
}
