package br.com.management.model.project.exception;


public class ProjectNotFoundException extends RuntimeException {

	private final long id;

	public ProjectNotFoundException(final long id) {
		this.id = id;
	}

	public long getId() {
		return id;
	}
}
