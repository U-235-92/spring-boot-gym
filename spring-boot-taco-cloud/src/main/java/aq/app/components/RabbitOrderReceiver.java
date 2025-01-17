package aq.app.components;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;

import aq.app.models.TacoOrder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class RabbitOrderReceiver {

	private final RabbitTemplate rabbitTemplate;
	private final MessageConverter messageConverter;
	
//	Active receiving of messages
	public TacoOrder receiveOrder() {
		Message message = rabbitTemplate.receive("tacocloud.order");
		System.out.println(message);
//		return (TacoOrder) messageConverter.fromMessage(message);
		return rabbitTemplate.receiveAndConvert(new ParameterizedTypeReference<TacoOrder>() {});
	}
	
//	Passive receiving of messages
//	@RabbitListener(queues = "tacocloud.order")
//	public void receiveOrder(TacoOrder order) {
//		System.out.println(order);
//	}
}
