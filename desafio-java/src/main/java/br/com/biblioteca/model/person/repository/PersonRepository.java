package br.com.biblioteca.model.person.repository;

import java.util.List;

import br.com.biblioteca.model.person.entity.Person;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

	@Query("select p from Person p where p.isEmployee = true")
	List<Person> findEmployees();

	@Query("select p from Person p where p.isEmployee = true and not exists (select m.id from Membership m where m.project.id = :projectId and p.id = m.person.id)")
	List<Person> findEmployeesWithoutMembership(final long projectId);
}
