package aq.app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aq.app.exceptions.AccountNotFoundException;
import aq.app.models.Account;
import aq.app.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

	private final AccountRepository accountRepository;
	
	@Transactional
	public void transferMoney(int idSender, int idReceiver, BigDecimal amount) {
		Account sender = accountRepository.findById(idSender).orElseThrow(AccountNotFoundException::new);
		Account receiver = accountRepository.findById(idReceiver).orElseThrow(AccountNotFoundException::new);
		BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
		BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
		accountRepository.changeAmount(idSender, senderNewAmount);
		accountRepository.changeAmount(idReceiver, receiverNewAmount);
	}
	
	public Iterable<Account> getAllAccounts() {
		return accountRepository.findAll();
	}
	
	public List<Account> getAccountsByName(String name) {
		return accountRepository.findAccountsByName(name);
	}
}
