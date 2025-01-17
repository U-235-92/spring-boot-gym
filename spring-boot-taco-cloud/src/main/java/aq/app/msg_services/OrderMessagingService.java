package aq.app.msg_services;

import aq.app.models.TacoOrder;

public interface OrderMessagingService {

	void sendOrder(TacoOrder order);
}
