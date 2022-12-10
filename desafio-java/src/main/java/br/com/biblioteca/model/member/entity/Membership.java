package br.com.biblioteca.model.member.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.biblioteca.model.person.entity.Person;
import br.com.biblioteca.model.project.entity.Project;

@Entity
@Table(name = "membros")
public class Membership {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "idmembro")
	private long id;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idprojeto", referencedColumnName = "id")
	private Project project;

	@NotNull
	@ManyToOne
	@JoinColumn(name = "idpessoa", referencedColumnName = "id")
	private Person person;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
