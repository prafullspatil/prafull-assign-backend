package com.mb.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class CheckoutPayment
{
	@NotEmpty
	private String name;

	@NotEmpty
	private String currency;

	@NotEmpty
	private String successUrl;

	@NotEmpty
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
