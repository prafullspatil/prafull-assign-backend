package com.mb.controller;

import static com.mb.constant.StripKey.STRIPE_HEADER;
import static com.mb.constant.StripKey.STRIPE_KEY;
import static com.mb.constant.UrlMapping.BASE_URL;
import static com.mb.constant.UrlMapping.CHECKOUT;
import static com.mb.constant.UrlMapping.WEBHOOK;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.mb.dto.CheckoutPayment;
import com.mb.entity.PaymentDetails;
import com.mb.exception.CustomException;
import com.mb.repository.PaymentDetailsRepository;
import com.mb.response.SuccResponse;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

@RestController
@RequestMapping(BASE_URL)
public class StripeController
{
	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;

	private static final String WEBHOOK_SECRET = "whsec_d881ff823bc3b4ca956673470879e9db280aea7ba51ee78ae1234766298cfee1";

	private static Gson gson = new Gson();

	private static void init()
	{
		Stripe.apiKey = STRIPE_KEY;
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(CHECKOUT)
	public String paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException
	{

		init();

		SessionCreateParams params = SessionCreateParams.builder()

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
														.builder().setName(payment.getName())
														.addImage("https://raw.githubusercontent.com/prafullspatil/e-commerce-angular-assignment/master/src/assets/image/img4.jpg")
														.build())
												.build())
								.build())
				.build();

		Session session = Session.create(params);
		Map<String, String> responseData = new HashMap<>();
		responseData.put("id", session.getId());
		return gson.toJson(responseData);
	}

	@PostMapping(WEBHOOK)
	private ResponseEntity<SuccResponse> savePaymentDetails(HttpServletRequest request, PaymentDetails paymentDetails)
	{
		String sigHeader = request.getHeader(STRIPE_HEADER);
		Event event = null;
		try
		{
			String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);
		}
		catch (Exception e)
		{

		}

		if ("charge.succeeded".equals(event.getType()))
		{
			System.out.println("In Checkout Session completed");
			PaymentDetails details = new PaymentDetails();
			EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
			StripeObject stripeObject = null;
			if (dataObjectDeserializer.getObject().isPresent())
			{
				stripeObject = dataObjectDeserializer.getObject().get();
			}
			Charge charge = (Charge) stripeObject;
			details.setPaymentId(charge.getPaymentIntent());
			details.setPaymentMethod(charge.getPaymentMethodDetails().getType());
			details.setCustomerId(charge.getCustomer());
			details.setCustomerEmail(charge.getBillingDetails().getEmail());
			details.setCustomerName(charge.getBillingDetails().getName());
			details.setCustomerCountry(charge.getBillingDetails().getAddress().getCountry());
			details.setAmount(charge.getAmount());
			details.setCurrency(charge.getCurrency());
			paymentDetailsRepository.save(details);

			SuccResponse response = SuccResponse.getInstance();
			response.setData(paymentDetailsRepository.save(details));
			response.setMessage("Success");
			response.setStatusCode(HttpStatus.OK.value());
			return new ResponseEntity<SuccResponse>(response, HttpStatus.OK);
		}
		else
		{
			throw new CustomException("Error");
		}

	}

}
