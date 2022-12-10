package br.com.biblioteca.model.person.dto;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

public class PersonDTO {

	private long id;
	private String name;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate birthDate;
	private String taxpayerId;
	private Boolean isEmployee;

	public PersonDTO() {

	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public String getTaxpayerId() {
		return taxpayerId;
	}

	public void setTaxpayerId(String taxpayerId) {
		this.taxpayerId = taxpayerId;
	}

	public Boolean getEmployee() {
		return isEmployee;
	}

	public void setEmployee(Boolean employee) {
		isEmployee = employee;
	}
}
