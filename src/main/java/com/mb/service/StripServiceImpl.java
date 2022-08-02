package com.mb.service;

import static com.mb.constant.StripKey.stripkey;
import java.util.HashMap;
import java.util.Map;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.mb.dto.CheckoutPayment;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.checkout.Session;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class StripServiceImpl implements StripService
{
	private static void init()
	{
		Stripe.apiKey = stripkey;
	}

	// create a Gson object
	private static Gson gson = new Gson();

	@Override
	public Session paymentWithCheckoutPage(CheckoutPayment payment) throws StripeException
	{
		// We initilize stripe object with the api key
		init();
		// We create a stripe session parameters
		SessionCreateParams params = SessionCreateParams.builder()
				// We will use the credit card payment method
				.addPaymentMethodType(SessionCreateParams.PaymentMethodType.CARD)
				.setMode(SessionCreateParams.Mode.PAYMENT).setSuccessUrl(payment.getSuccessUrl())
				.setCancelUrl(
						payment.getCancelUrl())
				.addLineItem(
						SessionCreateParams.LineItem.builder().setQuantity(payment.getQuantity())
								.setPriceData(
										SessionCreateParams.LineItem.PriceData.builder()
												.setCurrency(payment.getCurrency()).setUnitAmount(payment.getAmount())
												.setProductData(SessionCreateParams.LineItem.PriceData.ProductData
														.builder().setName(payment.getName()).build())
												.build())
								.build())
				.build();
		// create a stripe session
		Session session = Session.create(params);

		Map<String, String> responseData = new HashMap<>();
		// We get the sessionId and we putted inside the response data you can get more info from the session object
		responseData.put("id", session.getId());
		// We can return only the sessionId as a String
		return (Session) responseData;

	}

}
