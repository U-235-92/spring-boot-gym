package aq.app.proxies;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import aq.app.models.Payment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class PaymentProxy {

	private final RestTemplate template;
	@Value("${name.service.url}")
	private String paymentServiceURL;
	
	public Payment createPayment(Payment payment) {
		String uri = paymentServiceURL + "/payment";
		HttpHeaders headers = new HttpHeaders();
		headers.add("requestId", UUID.randomUUID().toString());
		HttpEntity<Payment> entity = new HttpEntity<Payment>(payment, headers);
		ResponseEntity<Payment> responseEntity = template.exchange(uri, HttpMethod.POST, entity, Payment.class);
		return responseEntity.getBody();
	}
}
