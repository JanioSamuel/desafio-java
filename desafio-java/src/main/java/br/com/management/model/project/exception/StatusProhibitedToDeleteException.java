package br.com.management.model.project.exception;

import br.com.management.model.project.entity.Project;

public class StatusProhibitedToDeleteException extends RuntimeException {

	private final Project project;

	public StatusProhibitedToDeleteException(final Project project) {
		this.project = project;
	}

	public Project getProject() {
		return project;
	}
}
