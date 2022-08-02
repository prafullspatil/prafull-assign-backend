package com.mb.controller;

import static com.mb.constant.StripKey.STRIPE_KEY;
import static com.mb.constant.StripKey.WEBHOOK_SECRET;
import static com.mb.constant.UrlMapping.BASE_URL;
import static com.mb.constant.UrlMapping.CHECKOUT;
import static com.mb.constant.UrlMapping.WEBHOOK;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.modelmapper.ModelMapper;
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
import com.mb.repository.PaymentDetailsRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Customer;
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

	private static final StripeObject Customer = null;

	private static void init()
	{
		Stripe.apiKey = STRIPE_KEY;
	}

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;

	// create a Gson object
	private static Gson gson = new Gson();

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
		// // We can return only the sessionId as a String
		// Map<String, Object> customerinfo = new HashMap<>();
		// customerinfo.put(
		// "description",
		// "My First Test Customer (created for API docs at https://www.stripe.com/docs/api)");
		//
		// Customer customer = ((com.stripe.model.Customer) Customer).create(customerinfo);
		return gson.toJson(responseData);
	}

	@PostMapping(WEBHOOK)
	private ResponseEntity<?> extractEventFromSignature(HttpServletRequest request, PaymentDetails paymentDetails)
	{
		String sigHeader = request.getHeader("Stripe-Signature");
		Event event = null;

		try
		{
			String payload = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
			event = Webhook.constructEvent(payload, sigHeader, WEBHOOK_SECRET);
		}
		catch (Exception e)
		{

		}

		if ("checkout.session.completed".equals(event.getType()))
		{
			System.out.println("In Checkout Session completed");
			PaymentDetails details = new PaymentDetails();
			// details.setType(event.getType());
			// System.out.println("getRawJsonObject" + event.getData().getObject().getRawJsonObject());
			// // String json = event.getData().toJson();
			// // System.out.println("json:" + json);
			// event.getId();
			// System.out.println("event:" + event);
			EventDataObjectDeserializer dataObjectDeserializer = event.getDataObjectDeserializer();
			StripeObject stripeObject = null;
			if (dataObjectDeserializer.getObject().isPresent())
			{
				stripeObject = dataObjectDeserializer.getObject().get();
			}
			Customer customer = modelMapper.map(stripeObject, Customer.class);

			// System.out.println("Strip Object" + stripeObject);
			// details.setEmail(((Customer) stripeObject).getEmail());
			details.setEmail(customer.getEmail());
			// details.setName(((Customer) customer).getName());
			details.setName(customer.getName());
			details.setType(event.getType());
			paymentDetailsRepository.save(details);
		}
		// try
		// {
		// System.out.println("event JSON : => " + mapper.writeValueAsString(event));
		// String type = event.getType();
		//
		//
		//
		// }
		// catch (JsonProcessingException e)
		// {
		// e.printStackTrace();
		// }

		return new ResponseEntity<>(event, HttpStatus.OK);
	}

}
