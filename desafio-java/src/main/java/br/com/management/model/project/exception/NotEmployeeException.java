package br.com.management.model.project.exception;

import br.com.management.model.project.dto.ProjectDTO;

public class NotEmployeeException extends RuntimeException {

	private final ProjectDTO projectDTO;

	public NotEmployeeException(final ProjectDTO projectDTO) {
		this.projectDTO = projectDTO;
	}

	public ProjectDTO getProjectDTO() {
		return projectDTO;
	}
}
