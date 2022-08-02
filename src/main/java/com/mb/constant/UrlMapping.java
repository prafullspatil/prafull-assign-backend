package com.mb.constant;

public class UrlMapping
{

	private UrlMapping()
	{
		throw new IllegalStateException("Constants class.can't instatiate");
	}

	public static final String BASE_URL = "/api";

	public static final String CHECKOUT = "/checkout-payment";
	public static final String WEBHOOK = "/webhook";

	public static final String PRODUCT = "/save";
	public static final String PRODUCTS = "/get-product";
	public static final String SINGLE_PRODUCT = "/get-product/{id}";
}
