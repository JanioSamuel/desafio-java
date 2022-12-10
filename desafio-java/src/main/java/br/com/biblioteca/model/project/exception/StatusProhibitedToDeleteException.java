package br.com.biblioteca.model.project.exception;

import br.com.biblioteca.model.project.entity.Project;

public class StatusProhibitedToDeleteException extends RuntimeException {

	private final Project project;

	public StatusProhibitedToDeleteException(final Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
}
