package aq.app.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Payment;
import aq.app.proxies.PaymentProxy;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentProxy paymentProxy;
	
	@PostMapping("/payment")
	public Payment createPayment(@RequestBody Payment payment) {
		return paymentProxy.createPayment(payment);
	}
}
