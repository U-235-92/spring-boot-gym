package aq.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.TacoOrder;
import aq.app.msg_services.OrderMessagingService;

@RestController
@RequestMapping(path = "/api/msg/jms/orders", produces = "application/json")
@CrossOrigin(origins = "http://localhost:5082", methods = {RequestMethod.POST, RequestMethod.GET})
public class JmsOrderController {

	@Autowired
	@Qualifier("jmsOrderMsgService")
	private OrderMessagingService orderMessagingService;
	
	@Transactional
	@PostMapping(consumes = "application/json")
	@ResponseStatus(code = HttpStatus.CREATED)
	public TacoOrder postOrder(@RequestBody TacoOrder tacoOrder) {
		orderMessagingService.sendOrder(tacoOrder);
		return tacoOrder;
	}
}
