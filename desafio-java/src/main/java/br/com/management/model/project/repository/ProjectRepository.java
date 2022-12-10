package br.com.management.model.project.repository;

import java.util.List;

import br.com.management.model.project.entity.Project;
import org.springframework.data.repository.CrudRepository;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findAll();
}
