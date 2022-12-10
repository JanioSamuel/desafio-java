package br.com.biblioteca.model.person.service;

import java.util.List;

import br.com.biblioteca.model.person.dto.PersonDTO;

public interface PersonService {

	/**
	 * Search for all people who has isEmployee = true
	 * @return {@link PersonDTO}
	 */
	List<PersonDTO> findEmployees();

	/**
	 * Create a employee
	 * @param personDTO dto for employee
	 * @return {@link PersonDTO}
	 */
	PersonDTO create(final PersonDTO personDTO);

	/**
	 * Find employees without membership
	 * @param id project's id
	 * @return {@link PersonDTO}
	 */
	List<PersonDTO> findEmployeesWithoutMembership(long id);
}
