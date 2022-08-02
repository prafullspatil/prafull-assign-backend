package com.mb.controller;

import static com.mb.constant.UrlMapping.BASE_URL;
import static com.mb.constant.UrlMapping.CHECKOUT;
import static com.mb.constant.UrlMapping.WEBHOOK;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.dto.CheckoutPayment;
import com.mb.response.SuccResponse;
import com.mb.service.PaymentDetailService;
import com.stripe.exception.StripeException;

@RestController
@RequestMapping(BASE_URL)
public class StripeController
{
	@Autowired
	private PaymentDetailService paymentDetailService;

	@CrossOrigin(origins = "http://localhost:4200")
	@PostMapping(CHECKOUT)
	public ResponseEntity<String> paymentWithCheckoutPage(@RequestBody CheckoutPayment payment) throws StripeException
	{
		String response = paymentDetailService.paymentWithCheckoutPage(payment);
		return new ResponseEntity<>(response, HttpStatus.OK);

	}

	@PostMapping(WEBHOOK)
	private ResponseEntity<SuccResponse> savePaymentDetails(HttpServletRequest request)
	{
		SuccResponse response = SuccResponse.getInstance();
		response.setData(paymentDetailService.savePaymentDetails(request));
		response.setMessage("Success");
		response.setStatusCode(HttpStatus.OK.value());

		return new ResponseEntity<>(response, HttpStatus.OK);
	}

}
