package br.com.biblioteca.model.project.exception;

import br.com.biblioteca.model.project.dto.ProjectDTO;

public class DifferentIdException extends RuntimeException {

	private final ProjectDTO projectDTO;
	private final long id;

	public DifferentIdException(final ProjectDTO projectDTO, final long id) {
		this.projectDTO = projectDTO;
		this.id = id;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}

	public long getId() {
		return id;
	}
}
