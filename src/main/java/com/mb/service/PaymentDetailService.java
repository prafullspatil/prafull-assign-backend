package com.mb.service;

import javax.servlet.http.HttpServletRequest;
import com.mb.dto.CheckoutPayment;
import com.stripe.exception.StripeException;

public interface PaymentDetailService
{

	String paymentWithCheckoutPage(CheckoutPayment checkoutPayment) throws StripeException;

	String savePaymentDetails(HttpServletRequest request);
}
