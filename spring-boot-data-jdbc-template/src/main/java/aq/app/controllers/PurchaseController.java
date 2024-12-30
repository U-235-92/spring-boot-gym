package aq.app.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import aq.app.models.Purchase;
import aq.app.repositories.PurchaseRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/purchase")
@RequiredArgsConstructor
public class PurchaseController {

	private final PurchaseRepository purchaseRepository;
	
	@PostMapping
	public void storePurchase(@RequestBody Purchase purchase) {
		purchaseRepository.storePurchase(purchase);
	}
	
	@GetMapping
	public List<Purchase> findPurchases() {
		return purchaseRepository.findAllPurchases();
	}
}
