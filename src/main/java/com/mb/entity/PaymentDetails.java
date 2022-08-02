package com.mb.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "payment")
public class PaymentDetails
{
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private long id;

	@Column(name = "payment_id")
	private String paymentId;

	@Column(name = "payment_method")
	private String paymentMethod;

	@Column(name = "customer_id")
	private String customerId;

	@Column(name = "customer_name")
	private String customerName;

	@Column(name = "customer_email")
	private String customerEmail;

	@Column(name = "customer_country")
	private String customerCountry;

	@Column(name = "amount")
	private Long amount;

	@Column(name = "currency")
	private String currency;

	@Column(name = "status")
	private String status;

	public long getId()
	{
		return id;
	}

	public String getPaymentId()
	{
		return paymentId;
	}

	public String getPaymentMethod()
	{
		return paymentMethod;
	}

	public String getCustomerId()
	{
		return customerId;
	}

	public String getCustomerName()
	{
		return customerName;
	}

	public String getCustomerEmail()
	{
		return customerEmail;
	}

	public String getCustomerCountry()
	{
		return customerCountry;
	}

	public Long getAmount()
	{
		return amount;
	}

	public String getCurrency()
	{
		return currency;
	}

	public void setId(long id)
	{
		this.id = id;
	}

	public void setPaymentId(String paymentId)
	{
		this.paymentId = paymentId;
	}

	public void setPaymentMethod(String paymentMethod)
	{
		this.paymentMethod = paymentMethod;
	}

	public void setCustomerId(String customerId)
	{
		this.customerId = customerId;
	}

	public void setCustomerName(String customerName)
	{
		this.customerName = customerName;
	}

	public void setCustomerEmail(String customerEmail)
	{
		this.customerEmail = customerEmail;
	}

	public void setCustomerCountry(String customerCountry)
	{
		this.customerCountry = customerCountry;
	}

	public void setAmount(Long amount)
	{
		this.amount = amount;
	}

	public void setCurrency(String currency)
	{
		this.currency = currency;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

}
