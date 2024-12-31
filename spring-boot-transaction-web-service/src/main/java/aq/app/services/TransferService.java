package aq.app.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import aq.app.models.Account;
import aq.app.repositories.AccountRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TransferService {

	private final AccountRepository accountRepository;
	
	@Transactional
	public void transferMoney(int idSender, int idReceiver, BigDecimal amount) {
		Account sender = accountRepository.findAccountById(idSender);
		Account receiver = accountRepository.findAccountById(idReceiver);
		BigDecimal senderNewAmount = sender.getAmount().subtract(amount);
		BigDecimal receiverNewAmount = receiver.getAmount().add(amount);
		accountRepository.changeAmount(idSender, senderNewAmount);
		accountRepository.changeAmount(idReceiver, receiverNewAmount);
//		throw new RuntimeException("Oh no! Something went wrong!"); //Test how transaction works
	}
	
	public List<Account> getAllAccounts() {
		return accountRepository.findAllAccounts();
	}
}
