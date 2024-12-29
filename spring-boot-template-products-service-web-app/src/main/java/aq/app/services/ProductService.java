package aq.app.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import aq.app.models.Product;

@Service
public class ProductService {

	private List<Product> products = new ArrayList<Product>();
	
	public void addProduct(Product product) {
		products.add(product);
	}
	
	public List<Product> findAll() {
		return List.copyOf(products);
	}
}
