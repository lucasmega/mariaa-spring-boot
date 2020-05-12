package com.mariaa.app.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.mariaa.app.domain.enums.PaymentStatus;

@Entity
public class PaymentByBillet extends Payment {
	
	private static final long serialVersionUID = 1L;
	
	private Date dueDate;
	private Date payDay;
	
	public PaymentByBillet() { }

	public PaymentByBillet(Integer id, PaymentStatus paymentStatus, Order order, Date dueDate, Date payDay) {
		super(id, paymentStatus, order);
		
		this.dueDate = dueDate;
		this.payDay = payDay;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getPayDay() {
		return payDay;
	}

	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	
	
	
	
}
