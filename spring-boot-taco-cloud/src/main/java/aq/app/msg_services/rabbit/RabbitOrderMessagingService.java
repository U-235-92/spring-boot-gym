package aq.app.msg_services.rabbit;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import aq.app.contfigs.RabbitSenderConfiguration;
import aq.app.models.TacoOrder;
import aq.app.msg_services.OrderMessagingService;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("rabbitOrderMsgService")
@RequiredArgsConstructor
public class RabbitOrderMessagingService implements OrderMessagingService {

	private final RabbitTemplate rabbitTemplate;

	@Override
	public void sendOrder(TacoOrder order) {
//		MessageConverter converter = rabbitTemplate.getMessageConverter();
//		MessageProperties properties = new MessageProperties();
//		properties.setHeader("X_ORDER_SOURCE", "WEB");
//		Message message = converter.toMessage(order, properties);
//		rabbitTemplate.send(message);
//		rabbitTemplate.send("tacocoloud.order", message);
//		rabbitTemplate.send(RabbitSenderConfiguration.EXCHANGE_NAME, RabbitSenderConfiguration.ROUTING_KEY, message);
//		rabbitTemplate.convertAndSend(RabbitSenderConfiguration.EXCHANGE_NAME, RabbitSenderConfiguration.ROUTING_KEY, message);
		rabbitTemplate.convertAndSend(RabbitSenderConfiguration.ROUTING_KEY, order, msg -> {
			MessageProperties props = msg.getMessageProperties();
			props.setHeader("X_ORDER_SOURCE", "WEB");
			return msg;
		});
	}
}
