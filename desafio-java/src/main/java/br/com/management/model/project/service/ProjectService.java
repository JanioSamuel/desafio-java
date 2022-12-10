package br.com.management.model.project.service;

import java.util.List;

import br.com.management.model.project.dto.ProjectDTO;

public interface ProjectService {

	/**
	 * Create a project.
	 * @param model project dto
	 * @return {@link ProjectDTO}
	 */
	ProjectDTO create(ProjectDTO model);

	/**
	 * Find a list of projects
	 * @return {@link ProjectDTO}
	 */
	List<ProjectDTO> findProjects();

	/**
	 * Delete a specific project by id
	 * @param id project's id
	 */
	void delete(long id);

	/**
	 * Update a specific project
	 * @param id project's id
	 * @param projectDTO project's dto
	 * @return {@link ProjectDTO}
	 */
	ProjectDTO update(long id, ProjectDTO projectDTO);

	/**
	 * Find a specific project
	 * @return {@link ProjectDTO}
	 * @param id project's id
	 */
	ProjectDTO findProjectById(long id);
}
