package aq.app.components;

import jakarta.jms.JMSException;
import jakarta.jms.Message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessageConversionException;
import org.springframework.stereotype.Component;

import aq.app.models.TacoOrder;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class JmsOrderListener {

	private JmsTemplate jmsTemplate;
	
//	Active receiving of messages
	public TacoOrder receiveOrder() throws MessageConversionException, JMSException {
		Message message = jmsTemplate.receive("tacocloud.order");
		System.out.println(message);
		return (TacoOrder) jmsTemplate.getMessageConverter().fromMessage(message);
	}
	
//	Passive receiving of messages
	@JmsListener(destination = "")
	public void receiveOrder(TacoOrder order) {
		System.out.println(order);
	}
}
