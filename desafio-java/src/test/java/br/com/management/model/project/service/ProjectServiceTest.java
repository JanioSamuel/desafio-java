package br.com.management.model.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.management.model.member.entity.Membership;
import br.com.management.model.member.repository.MemberRepository;
import br.com.management.model.person.entity.Person;
import br.com.management.model.project.dto.ProjectDTO;
import br.com.management.model.project.entity.Project;
import br.com.management.model.project.enumerator.RiskRating;
import br.com.management.model.project.enumerator.Status;
import br.com.management.model.project.exception.DifferentIdException;
import br.com.management.model.project.exception.NotEmployeeException;
import br.com.management.model.project.exception.ProjectNotFoundException;
import br.com.management.model.project.exception.StatusProhibitedToDeleteException;
import br.com.management.model.project.repository.ProjectRepository;
import br.com.management.model.project.service.ProjectService;
import br.com.management.model.project.service.ProjectServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProjectServiceTest {

	private ProjectService projectService;
	private ProjectRepository projectRepository;
	private MemberRepository memberRepository;

	@BeforeEach
	void prepareTests() {
		this.projectRepository = Mockito.mock(ProjectRepository.class);
		this.memberRepository = Mockito.mock(MemberRepository.class);
		this.projectService = new ProjectServiceImpl(projectRepository, memberRepository);
	}

	@Test
	void verifyCreateWithEmployee() {
		final ProjectDTO input = new ProjectDTO();
		final Project expected = new Project();
		expected.setName("Junit");
		final Person manager = new Person();
		manager.setId(99);
		manager.setEmployee(true);
		input.setName("Junit");
		input.setManager(manager);
		Mockito.when(this.projectRepository.save(Mockito.any(Project.class))).thenReturn(expected);
		final ProjectDTO response = this.projectService.create(input);
		assertEquals(input.getName(), response.getName());
	}

	@Test
	void verifyCreateWithoutEmployee() {
		final ProjectDTO input = new ProjectDTO();
		final Project expected = new Project();
		expected.setName("Junit");
		input.setName("Junit");
		assertThrows(NotEmployeeException.class, () -> this.projectService.create(input));
	}

	@Test
	void verifyCreateNotEmployee() {
		final ProjectDTO input = new ProjectDTO();
		final Project expected = new Project();
		final Person manager = new Person();
		expected.setName("Junit");
		manager.setId(99);
		manager.setEmployee(false);
		input.setName("Junit");
		input.setManager(manager);
		assertThrows(NotEmployeeException.class, () -> this.projectService.create(input));
	}

	@Test
	void verifyFindProjects() {
		final Project project = new Project();
		project.setId(1);
		final List<Project> projectList = new ArrayList<>();
		projectList.add(project);
		Mockito.when(this.projectRepository.findAll()).thenReturn(projectList);
		List<ProjectDTO> response = this.projectService.findProjects();
		assertEquals(projectList.get(0).getId(), response.get(0).getId());
	}

	@Test
	void verifyDeleteEligible() {
		final long id = 1;
		final Project project = new Project();
		final List<Membership> membershipList = new ArrayList<>();
		project.setId(1);
		project.setStatus(Status.APPROVED);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.of(project));
		Mockito.when(this.memberRepository.findByProjectId(id)).thenReturn(membershipList);
		Mockito.doNothing().when(this.memberRepository).deleteAll(Mockito.any(ArrayList.class));
		Mockito.doNothing().when(this.projectRepository).deleteById(id);
		this.projectService.delete(id);
		Mockito.verify(this.projectRepository).deleteById(id);
	}

	@Test
	void verifyDeleteNullEntity() {
		final long id = 1;
		final Project project = new Project();
		project.setId(88);
		project.setStatus(Status.APPROVED);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ProjectNotFoundException.class, () -> this.projectService.delete(id));
	}

	@Test
	void verifyDeleteNotEligible() {
		final long id = 1;
		final Project project = new Project();
		project.setId(1);
		project.setStatus(Status.RUNNING);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.of(project));
		assertThrows(StatusProhibitedToDeleteException.class, () -> this.projectService.delete(id));
	}

	@Test
	void verifyUpdate() {
		final long id = 1;
		final Project project = new Project();
		final ProjectDTO projectDTO = new ProjectDTO();
		final Person manager = new Person();
		project.setId(1);
		projectDTO.setId(1);
		manager.setId(99);
		manager.setEmployee(true);
		projectDTO.setManager(manager);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.of(project));
		Mockito.when(this.projectRepository.save(Mockito.any(Project.class))).thenReturn(project);
		final ProjectDTO response = this.projectService.update(id, projectDTO);
		Mockito.verify(this.projectRepository).save(Mockito.any(Project.class));
		assertEquals(project.getId(), response.getId());
	}

	@Test
	void verifyUpdateIdDifferentOfDTO() {
		final long id = 1;
		final Project project = new Project();
		final ProjectDTO projectDTO = new ProjectDTO();
		final Person manager = new Person();
		project.setId(90);
		projectDTO.setId(1);
		manager.setId(99);
		manager.setEmployee(true);
		projectDTO.setManager(manager);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.of(project));
		assertThrows(DifferentIdException.class, () -> this.projectService.update(id, projectDTO));
	}

	@Test
	void verifyUpdateProjectDoesNotExists() {
		final long id = 1;
		final Project project = new Project();
		final ProjectDTO projectDTO = new ProjectDTO();
		final Person manager = new Person();
		project.setId(90);
		projectDTO.setId(1);
		manager.setId(99);
		manager.setEmployee(true);
		projectDTO.setManager(manager);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.empty());
		assertThrows(ProjectNotFoundException.class, () -> this.projectService.update(id, projectDTO));
	}

	@Test
	void verifyFindProjectById() {
		final long id = 1;
		final Project project = new Project();
		project.setName("Project1");
		project.setRisk(RiskRating.LOW);
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.of(project));
		final ProjectDTO response = this.projectService.findProjectById(id);
		Mockito.verify(this.projectRepository).findById(id);
		assertEquals(project.getId(), response.getId());
		assertEquals(project.getRisk(), response.getRisk());
	}

	@Test
	void verifyFindNoProjectById() {
		final long id = 1;
		Mockito.when(this.projectRepository.findById(id)).thenReturn(Optional.empty());
		final ProjectDTO response = this.projectService.findProjectById(id);
		Mockito.verify(this.projectRepository).findById(id);
		assertNull(response.getName());
	}
}