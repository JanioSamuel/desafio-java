package br.com.biblioteca.model.project.service;

import java.util.Arrays;
import java.util.List;

import br.com.biblioteca.model.member.entity.Membership;
import br.com.biblioteca.model.member.repository.MemberRepository;
import br.com.biblioteca.model.project.dto.ProjectDTO;
import br.com.biblioteca.model.project.entity.Project;
import br.com.biblioteca.model.project.enumerator.Status;
import br.com.biblioteca.model.project.exception.DifferentIdException;
import br.com.biblioteca.model.project.exception.NotEmployeeException;
import br.com.biblioteca.model.project.exception.ProjectNotFoundException;
import br.com.biblioteca.model.project.exception.StatusProhibitedToDeleteException;
import br.com.biblioteca.model.project.repository.ProjectRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectServiceImpl implements ProjectService {

	private static final List<Status> PROHIBITED_TO_DELETE = Arrays.asList(Status.ANALYSIS_STARTED, Status.RUNNING, Status.CANCELED);
	private final ProjectRepository projectRepository;
	private final MemberRepository memberRepository;

	@Autowired
	public ProjectServiceImpl(final ProjectRepository projectRepository, final MemberRepository memberRepository) {
		this.projectRepository = projectRepository;
		this.memberRepository = memberRepository;
	}

	/**
	 * Create a project.
	 * @param model project dto
	 * @return {@link ProjectDTO}
	 */
	@Override
	public ProjectDTO create(ProjectDTO model) {
		if (model.getManager() != null && model.getManager().getEmployee()) {
			final ModelMapper modelMapper = new ModelMapper();
			final Project project = modelMapper.map(model, Project.class);
			final Project entity = projectRepository.save(project);
			return modelMapper.map(entity, ProjectDTO.class);
		}
		throw new NotEmployeeException(model);
	}

	/**
	 * Find an list of projects
	 *
	 * @return {@link ProjectDTO}
	 */
	@Override
	public List<ProjectDTO> findProjects() {
		final ModelMapper modelMapper = new ModelMapper();
		final List<Project> projectList = this.projectRepository.findAll();
		return Arrays.asList(modelMapper.map(projectList, ProjectDTO[].class));
	}

	/**
	 * Delete a specific project by id
	 *
	 * @param id project's id
	 */
	@Override
	public void delete(final long id) {
		final Project project = this.projectRepository.findById(id).orElse(null);
		if (projectIsNotNull(project, id) && this.isEligibleToDelete(project.getStatus())) {
			List<Membership> membershipList = this.memberRepository.findByProjectId(id);
			this.memberRepository.deleteAll(membershipList);
			this.projectRepository.deleteById(id);
		} else {
			throw new StatusProhibitedToDeleteException(project);
		}
	}

	/**
	 * Update a specific project
	 *
	 * @param id         project's id
	 * @param projectDTO project's dto
	 * @return {@link ProjectDTO}
	 */
	@Override
	public ProjectDTO update(final long id, final ProjectDTO projectDTO) {
		final Project project = this.projectRepository.findById(id).orElse(null);
		if (projectIsNotNull(project, id) && project.getId() == id) {
			projectDTO.setId(id);
			return this.create(projectDTO);
		}
		throw new DifferentIdException(projectDTO, id);
	}

	/**
	 * Find a specific project
	 *
	 * @return {@link ProjectDTO}
	 * @param id
	 */
	@Override
	public ProjectDTO findProjectById(final long id) {
		final ModelMapper modelMapper = new ModelMapper();
		Project project = this.projectRepository.findById(id).orElse(null);
		return project != null ? modelMapper.map(project, ProjectDTO.class) : new ProjectDTO();
	}

	private boolean isEligibleToDelete(Status status) {
		return !PROHIBITED_TO_DELETE.contains(status);
	}

	private boolean projectIsNotNull(Project project, final long id) {
		if (project != null) {
			return true;
		}
		throw new ProjectNotFoundException(id);
	}
}
