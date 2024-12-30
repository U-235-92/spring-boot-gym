package aq.app.proxies;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import aq.app.models.Payment;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class PaymentProxy {
	
	private final WebClient webClient;
	@Value("${name.service.url}")
	private String url;
	
	public Mono<Payment> createPayment(String requestId, Payment payment) {
		return webClient
				.post()
				.uri(url + "/payment")
				.header("requestId", requestId)
				.body(Mono.just(payment), Payment.class)
				.retrieve()
				.bodyToMono(Payment.class);
	}
}
