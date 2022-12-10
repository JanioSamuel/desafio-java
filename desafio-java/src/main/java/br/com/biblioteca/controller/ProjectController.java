package br.com.biblioteca.controller;

import br.com.biblioteca.model.member.service.MemberService;
import br.com.biblioteca.model.person.service.PersonService;
import br.com.biblioteca.model.project.dto.ProjectDTO;
import br.com.biblioteca.model.project.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@Controller
@RequestMapping("/project")
public class ProjectController {

	private final ProjectService projectService;
	private final PersonService personService;
	private final MemberService memberService;

	@Autowired
	public ProjectController(final ProjectService projectService,
		final PersonService personService,
		final MemberService memberService) {
		this.projectService = projectService;
		this.personService = personService;
		this.memberService = memberService;
	}

	@GetMapping("/view")
	public String viewProjects(Model model) {
		model.addAttribute("projects", this.projectService.findProjects());
		return "view-projects";
	}

	@GetMapping("/view-project/{id}")
	public String viewProject(Model model, final @PathVariable("id") long id) {
		model.addAttribute("project", this.projectService.findProjectById(id));
		model.addAttribute("members", this.memberService.findMemberByProjectId(id));
		return "view-project";
	}

	@GetMapping("/add")
	public String addProjectView(Model model) {
		model.addAttribute("project", new ProjectDTO());
		model.addAttribute("employees", personService.findEmployees());
		return "add-project";
	}

	@GetMapping("/edit/{id}")
	public String editProjectView(Model model, final @PathVariable("id") long id) {
		model.addAttribute("project", this.projectService.findProjectById(id));
		model.addAttribute("employees", personService.findEmployees());
		model.addAttribute("notMembers", personService.findEmployeesWithoutMembership(id));
		return "edit-project";
	}

	@GetMapping("/del")
	public String delProjectView(Model model) {
		model.addAttribute("project", new ProjectDTO());
		return "del-project";
	}

	@PostMapping("/add")
	public RedirectView createProject(final @ModelAttribute("project") ProjectDTO projectDTO, final RedirectAttributes redirectAttributes) {
		final RedirectView redirectView = new RedirectView("/project/view", true);
		ProjectDTO dto = this.projectService.create(projectDTO);
		redirectAttributes.addFlashAttribute("addedProject", dto);
		redirectAttributes.addFlashAttribute("addedProjectSuccess", true);
		return redirectView;
	}

	@PostMapping("/edit/{id}")
	public RedirectView updateProject(final @PathVariable("id") long id,
		final @ModelAttribute("project") ProjectDTO projectDTO,
		final RedirectAttributes redirectAttributes) {
		final RedirectView redirectView = new RedirectView("/project/view-project/" + id, true);
		this.memberService.insertMember(projectDTO);
		ProjectDTO dto = this.projectService.update(id, projectDTO);
		redirectAttributes.addFlashAttribute("editedProject", dto);
		redirectAttributes.addFlashAttribute("editedProjectSuccess", true);
		return redirectView;
	}

	@PostMapping("/del/{id}")
	public RedirectView deleteProject(final @PathVariable("id") long id, final RedirectAttributes redirectAttributes) {
		final RedirectView redirectView = new RedirectView("/project/view", true);
		this.projectService.delete(id);
		redirectAttributes.addFlashAttribute("deletedProjectSuccess", true);
		return redirectView;
	}
}
