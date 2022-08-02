package com.mb.service;

import static com.mb.constant.StripKey.STRIPE_HEADER;
import static com.mb.constant.StripKey.STRIPE_KEY;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.google.gson.Gson;
import com.mb.dto.CheckoutPayment;
import com.mb.entity.PaymentDetails;
import com.mb.exception.CustomException;
import com.mb.repository.PaymentDetailsRepository;
import com.stripe.Stripe;
import com.stripe.exception.StripeException;
import com.stripe.model.Charge;
import com.stripe.model.Event;
import com.stripe.model.EventDataObjectDeserializer;
import com.stripe.model.StripeObject;
import com.stripe.model.checkout.Session;
import com.stripe.net.Webhook;
import com.stripe.param.checkout.SessionCreateParams;

@Service
public class PaymentDetailServiceImpl implements PaymentDetailService
{
	@Autowired
	private PaymentDetailsRepository paymentDetailsRepository;

	private static final String WEBHOOK_SECRET = "whsec_d881ff823bc3b4ca956673470879e9db280aea7ba51ee78ae1234766298cfee1";

	private static Gson gson = new Gson();

	private static void init()
	{
		Stripe.apiKey = STRIPE_KEY;
	}

	@Override
	public String paymentWithCheckoutPage(CheckoutPayment payment) throws StripeException
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

	@Override
	public String savePaymentDetails(HttpServletRequest request)
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
			throw new CustomException("Something Went wrong");
		}

		if ("charge.succeeded".equals(event.getType()))
		{
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
			details.setAmount(charge.getAmount() / 100);
			details.setCurrency(charge.getCurrency());
			details.setStatus(charge.getStatus());
			paymentDetailsRepository.save(details);

		}
		return "Payment Details Save Successfully";

	}
}
