package aq.app.repositories;

import aq.app.models.TacoOrder;

public interface OrderRepository {

	TacoOrder save(TacoOrder tacoOrder);
}
