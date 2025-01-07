package aq.app.repositories.jdbc_template;

import aq.app.models.TacoOrder;

public interface OrderRepository {

	TacoOrder save(TacoOrder tacoOrder);
}
