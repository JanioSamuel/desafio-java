package br.com.management.model.project.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class LibraryControllerAdvice {

	@ExceptionHandler(value = NotEmployeeException.class)
	public ModelAndView notEmployeeException(NotEmployeeException e) {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ref", e.getProjectDTO().getName());
		modelAndView.addObject("object", e.getProjectDTO());
		modelAndView.addObject("message", "Cannot add a project without employee");
		modelAndView.setViewName("error-employee");
		return modelAndView;
	}

	@ExceptionHandler(value = StatusProhibitedToDeleteException.class)
	public ModelAndView statusProhibitedToDeleteException(StatusProhibitedToDeleteException e) {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ref", e.getProject().getName());
		modelAndView.addObject("object", e.getProject());
		modelAndView.addObject("message", "Cannot delete the project");
		modelAndView.setViewName("error-status");
		return modelAndView;
	}

	@ExceptionHandler(value = ProjectNotFoundException.class)
	public ModelAndView projectNotFoundException(ProjectNotFoundException e) {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ref", e.getId());
		modelAndView.addObject("object", e.getId());
		modelAndView.addObject("message", "Project with this ID not found");
		modelAndView.setViewName("error-notfound");
		return modelAndView;
	}

	@ExceptionHandler(value = DifferentIdException.class)
	public ModelAndView differentIdException(DifferentIdException e) {
		final ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("ref", e.getId());
		modelAndView.addObject("object", e.getProjectDTO());
		modelAndView.addObject("message", "ID sent is different of object sent");
		modelAndView.setViewName("error-id");
		return modelAndView;
	}
}
