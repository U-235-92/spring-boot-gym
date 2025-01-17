package aq.app.msg_services.artemis;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

import aq.app.models.TacoOrder;
import aq.app.msg_services.OrderMessagingService;
import lombok.RequiredArgsConstructor;

@Service
@Qualifier("jmsOrderMsgService")
@RequiredArgsConstructor
public class JmsOrderMessagingService implements OrderMessagingService {

	private final JmsTemplate jmsTemplate;
	@Override
	public void sendOrder(TacoOrder order) {
		jmsTemplate.send(session -> {
			return session.createObjectMessage(order);
		});
	}
}
