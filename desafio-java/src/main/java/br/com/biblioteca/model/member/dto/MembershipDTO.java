package br.com.biblioteca.model.member.dto;

import br.com.biblioteca.model.person.entity.Person;
import br.com.biblioteca.model.project.dto.ProjectDTO;

public class MembershipDTO {

	private long id;
	private ProjectDTO project;
	private Person person;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public ProjectDTO getProject() {
		return project;
	}

	public void setProject(ProjectDTO project) {
		this.project = project;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}
}
