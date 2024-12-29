package aq.app.controllers;

import java.util.logging.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aq.app.exceptions.NotEnoughMoneyException;
import aq.app.models.ErrorDetails;
import aq.app.models.PaymentDetails;
import aq.app.services.PaymentService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class PaymentController {

	private static Logger logger = Logger.getLogger(PaymentController.class.getName());
	private final PaymentService paymentService;
	
	@PostMapping("/payment-by-request-entity")
	public ResponseEntity<PaymentDetails> makePaymentByRequesBody(RequestEntity<PaymentDetails> requestEntity) {
		logger.info("Received payment: " + requestEntity.getBody().getAmount());
		logger.info("Custom header value: " + requestEntity.getHeaders().getFirst("hello"));
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.header("response_by", "RequestEntity")
				.body(requestEntity.getBody());
	}
	
	@PostMapping("/payment-by-request-body")
	public ResponseEntity<PaymentDetails> makePaymentByRequesBody(@RequestBody PaymentDetails paymentDetails) {
		logger.info("Received payment: " + paymentDetails.getAmount());
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.header("response_by", "RequestBody")
				.body(paymentDetails);
	}
	
	@GetMapping("/payment1")
	public ResponseEntity<?> makePaymentByHandleExceptionByController() {
		try {
			PaymentDetails paymentDetails = paymentService.processPayment();
			return ResponseEntity
					.status(HttpStatus.ACCEPTED)
					.body(paymentDetails);
		} catch (NotEnoughMoneyException e) {
			ErrorDetails errorDetails = new ErrorDetails("Not enough money to make the payment.");
			return ResponseEntity
					.badRequest()
					.body(errorDetails);
		}
	}
	
	@GetMapping("/payment2")
	public ResponseEntity<?> makePaymentByHandleExceptionByRestControllerAdvice() {
		PaymentDetails paymentDetails = paymentService.processPayment();
		return ResponseEntity
				.status(HttpStatus.ACCEPTED)
				.body(paymentDetails);
	}
}
