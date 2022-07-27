package com.mb.dto;

public class CheckoutPayment
{

	// the product name
	private String name;
	// currency like usd, eur ...
	private String currency;
	// our success and cancel url stripe will redirect to this links
	private String successUrl;
	private String cancelUrl;
	private long amount;
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
