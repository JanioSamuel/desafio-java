package br.com.biblioteca.model.project.repository;

import java.util.List;

import br.com.biblioteca.model.project.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findAll();
}
