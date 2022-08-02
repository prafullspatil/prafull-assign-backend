package com.mb.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CheckoutPayment
{
	@NotBlank
	private String name;

	@NotBlank
	private String currency;

	@NotBlank
	private String successUrl;

	@NotBlank
	private String cancelUrl;

	@NotNull
	private long amount;

	@NotNull
	private long quantity;

	public String getName()
	{
		return name;
	}

	public String getCurrency()
	{
		return currency;
	}

	public String getSuccessUrl()
	{
		return successUrl;
	}

	public String getCancelUrl()
	{
		return cancelUrl;
	}

	public long getAmount()
	{
		return amount;
	}

	public long getQuantity()
	{
		return quantity;
	}

}
