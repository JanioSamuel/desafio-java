package br.com.management.model.person.service;

import java.util.ArrayList;
import java.util.List;

import br.com.management.model.person.dto.PersonDTO;
import br.com.management.model.person.entity.Person;
import br.com.management.model.person.repository.PersonRepository;
import br.com.management.model.person.service.PersonService;
import br.com.management.model.person.service.PersonServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

	private PersonService personService;
	private PersonRepository personRepository;
	@BeforeEach
	void prepareTests() {
		this.personRepository = Mockito.mock(PersonRepository.class);
		this.personService = new PersonServiceImpl(personRepository);
	}

	@Test
	void verifyFindEmployees() {
		final Person person = new Person();
		final List<Person> personList = new ArrayList<>();
		person.setId(1);
		personList.add(person);
		Mockito.when(this.personRepository.findEmployees()).thenReturn(personList);
		final List<PersonDTO> response = this.personService.findEmployees();
		Mockito.verify(this.personRepository).findEmployees();
		assertFalse(response.isEmpty());
		assertEquals(response.size(), personList.size());
	}

	@Test
	void verifyFindNoEmployees() {
		Mockito.when(this.personRepository.findEmployees()).thenReturn(new ArrayList<>());
		final List<PersonDTO> response = this.personService.findEmployees();
		Mockito.verify(this.personRepository).findEmployees();
		assertTrue(response.isEmpty());
	}

	@Test
	void verifyCreate() {
		final PersonDTO personDTO = new PersonDTO();
		final Person person = new Person();
		person.setName("Unit");
		personDTO.setName("Unit");
		Mockito.when(this.personRepository.save(Mockito.any(Person.class))).thenReturn(person);
		final PersonDTO response = this.personService.create(personDTO);
		Mockito.verify(this.personRepository).save(Mockito.any(Person.class));
		assertEquals(person.getName(), response.getName());
	}

	@Test
	void verifyFindEmployeeWithoutMembership() {
		final long id = 1;
		final Person person = new Person();
		final List<Person> personList = new ArrayList<>();
		person.setName("P1");
		personList.add(person);
		Mockito.when(this.personRepository.findEmployeesWithoutMembership(id)).thenReturn(personList);
		final List<PersonDTO> response = this.personService.findEmployeesWithoutMembership(id);
		Mockito.verify(this.personRepository).findEmployeesWithoutMembership(id);
		assertEquals(personList.get(0).getName(), response.get(0).getName());
	}
}
