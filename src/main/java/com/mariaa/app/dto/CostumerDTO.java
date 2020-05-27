package com.mariaa.app.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.mariaa.app.services.validation.ClientInsert;

@ClientInsert
public class CostumerDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="Preenchimento obrigatório")
	@Length(min=5, max=80, message="O tamanho deve ser entre 5 e 120 caracteres!")
	private String name;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	@Email(message="E-mail inválido!")
	private String email;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String document;
	
	private Integer customerType;
	
	private String street;
	
	private Integer number;
	private String complement;
	private String neighborhood;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String zipCode;
	
	@NotEmpty(message="Preenchimento obrigatório!")
	private String mainphone;
	private String telephone;
	private String cellphone;

	private Integer city;

	public CostumerDTO() { }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	public Integer getCustomerType() {
		return customerType;
	}

	public void setCustomerType(Integer customerType) {
		this.customerType = customerType;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}

	public String getComplement() {
		return complement;
	}

	public void setComplement(String complement) {
		this.complement = complement;
	}

	public String getNeighborhood() {
		return neighborhood;
	}

	public void setNeighborhood(String neighborhood) {
		this.neighborhood = neighborhood;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getMainphone() {
		return mainphone;
	}

	public void setMainphone(String mainphone) {
		this.mainphone = mainphone;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getCellphone() {
		return cellphone;
	}

	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}

	public Integer getCity() {
		return city;
	}

	public void setCity(Integer city) {
		this.city = city;
	}

}

/**
 * DTO formado com a união das entidades de cliente e endereço.
 */