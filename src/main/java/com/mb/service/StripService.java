package com.mb.service;

import com.mb.dto.CheckoutPayment;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;

public interface StripService
{
	public Session paymentWithCheckoutPage(CheckoutPayment payment) throws StripeException;
}
