package com.mariaa.app.domain;

import javax.persistence.Entity;

import com.mariaa.app.domain.enums.PaymentStatus;

@Entity
public class PaymentByCard extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Integer numberOfInstallments;
	
	public PaymentByCard() { }

	public PaymentByCard(Integer id, PaymentStatus paymentStatus, Order order, Integer numberOfInstallments) {
		super(id, paymentStatus, order);
		
		this.numberOfInstallments = numberOfInstallments;
	}

	public Integer getNumberOfInstallments() {
		return numberOfInstallments;
	}

	public void setNumberOfInstallments(Integer numberOfInstallments) {
		this.numberOfInstallments = numberOfInstallments;
	}
	
	
	
	
	
}
