package aq.app.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
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
	public void transfermoney(@RequestBody TransferRequest transferRequest) {
		transferService.transferMoney(transferRequest.getSenderAccountId(), 
				transferRequest.getReceiverAccountId(), 
				transferRequest.getAmount());
	}
	
	@GetMapping("/accounts")
	public Iterable<Account> getAccounts(@RequestParam(required = false) String name) {
		if(name == null)
			return transferService.getAllAccounts();
		return transferService.getAccountsByName(name);
	}
}
