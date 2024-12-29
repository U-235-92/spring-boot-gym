package aq.app.services;

import org.springframework.stereotype.Service;

import aq.app.exceptions.NotEnoughMoneyException;
import aq.app.models.PaymentDetails;

@Service
public class PaymentService {

	public PaymentDetails processPayment() {
		throw new NotEnoughMoneyException();
	}
}
