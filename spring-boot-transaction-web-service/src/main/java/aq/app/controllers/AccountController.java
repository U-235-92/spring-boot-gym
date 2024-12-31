package aq.app.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import aq.app.dto.TransferRequest;
import aq.app.models.Account;
import aq.app.services.TransferService;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class AccountController {

	private final TransferService transferService;
	
	@PostMapping("/transfer")
	public void transferMoney(@RequestBody TransferRequest transferRequest) {
		transferService.transferMoney(transferRequest.getSenderAccountId(), 
				transferRequest.getReceiverAccountId(), 
				transferRequest.getAmount());
	}
	
	@GetMapping("/accounts")
	public List<Account> getAllAccounts() {
		return transferService.getAllAccounts();
	}
}
