package aq.app.controllers;

import java.util.UUID;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Payment;
import aq.app.proxies.PaymentProxy;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private final PaymentProxy paymentProxy;
	
	@PostMapping("/payment")
	public Mono<Payment> createPayment(@RequestBody Payment payment) {
		String requestId = UUID.randomUUID().toString();
		return paymentProxy.createPayment(requestId, payment);
	}
}
