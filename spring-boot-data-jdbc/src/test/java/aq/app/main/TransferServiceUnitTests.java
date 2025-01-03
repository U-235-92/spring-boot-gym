package aq.app.main;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

import aq.app.models.Account;
import aq.app.repositories.AccountRepository;
import aq.app.services.TransferService;

public class TransferServiceUnitTests {

	@Test
	@DisplayName("Test the amount is transferred from one account to another if no exception occurs")
	public void transferMoneyHappyFlow() {
		AccountRepository accountRepository = Mockito.mock(AccountRepository.class);
		TransferService transferService = new TransferService(accountRepository);
		Account sender = new Account();
		sender.setId(1);
		sender.setAmount(new BigDecimal(1000));
		Account destination = new Account();
		destination.setId(2);
		destination.setAmount(new BigDecimal(1000));
		BDDMockito.given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
		BDDMockito.given(accountRepository.findById(destination.getId())).willReturn(Optional.of(destination));
		transferService.transferMoney(sender.getId(), destination.getId(), new BigDecimal(100));
		Mockito.verify(accountRepository).changeAmount(1, new BigDecimal(900));
		Mockito.verify(accountRepository).changeAmount(2, new BigDecimal(1100));
	}
}
