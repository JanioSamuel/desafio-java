package br.com.biblioteca.controller;

import br.com.biblioteca.model.person.dto.PersonDTO;
import br.com.biblioteca.model.person.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/person")
public class PersonController {

	private final PersonService personService;

	@Autowired
	public PersonController(final PersonService personService) {
		this.personService = personService;
	}

	@PostMapping("/create")
	public PersonDTO create(@RequestBody PersonDTO personDTO) {
		return this.personService.create(personDTO);
	}
}
