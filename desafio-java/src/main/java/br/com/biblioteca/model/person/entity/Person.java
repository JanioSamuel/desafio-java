package br.com.biblioteca.model.person.entity;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "pessoa")
public class Person {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;

	@NotNull
	@Column(name = "nome")
	private String name;//nome character varying(100) NOT NULL,

	@Column(name = "datanascimento")
	private LocalDate birthDate;//datanascimento date,

	@Column(name = "cpf")
	private String taxpayerId; //cpf character varying(14),

	@Column(name = "funcionario")
	private Boolean isEmployee;//funcionario boolean,

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
