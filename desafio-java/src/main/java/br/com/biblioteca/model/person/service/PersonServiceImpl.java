package br.com.biblioteca.model.person.service;

import java.util.Arrays;
import java.util.List;

import br.com.biblioteca.model.person.dto.PersonDTO;
import br.com.biblioteca.model.person.entity.Person;
import br.com.biblioteca.model.person.repository.PersonRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class PersonServiceImpl implements PersonService {

	private final PersonRepository personRepository;

	public PersonServiceImpl(final PersonRepository personRepository) {
		this.personRepository = personRepository;
	}

	/**
	 * Search for all people who has isEmployee = true
	 * @return {@link Person}
	 */
	@Override
	public List<PersonDTO> findEmployees() {
		final ModelMapper modelMapper = new ModelMapper();
		final List<Person> person = personRepository.findEmployees();
		return Arrays.asList(modelMapper.map(person, PersonDTO[].class));
	}

	/**
	 * Create a employee
	 *
	 * @param personDTO dto for employee
	 * @return {@link PersonDTO}
	 */
	@Override
	public PersonDTO create(PersonDTO personDTO) {
		final ModelMapper modelMapper = new ModelMapper();
		Person entity = modelMapper.map(personDTO, Person.class);
		Person response = this.personRepository.save(entity);
		return modelMapper.map(response, PersonDTO.class);
	}

	/**
	 * Find employees without membership
	 *
	 * @param id project's id
	 * @return {@link PersonDTO}
	 */
	@Override
	public List<PersonDTO> findEmployeesWithoutMembership(final long id) {
		final ModelMapper modelMapper = new ModelMapper();
		final List<Person> person = personRepository.findEmployeesWithoutMembership(id);
		return Arrays.asList(modelMapper.map(person, PersonDTO[].class));
	}
}
