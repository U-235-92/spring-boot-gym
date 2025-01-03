package aq.app.main;

import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import aq.app.exceptions.AccountNotFoundException;
import aq.app.models.Account;
import aq.app.repositories.AccountRepository;
import aq.app.services.TransferService;

@ExtendWith(MockitoExtension.class)
public class TransferServiceUnitTestUsingMockAnnotations {

	@Mock
	private AccountRepository accountRepository;
	@InjectMocks
	private TransferService transferService;
	
	@Test
	public void transferMoneyHappyFlow() {
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
	
	@Test
	public void transferMoneyToDestinationAccountNotFoundFlow() {
		Account sender = new Account();
		sender.setId(1);
		sender.setAmount(new BigDecimal(1000));
		BDDMockito.given(accountRepository.findById(sender.getId())).willReturn(Optional.of(sender));
		BDDMockito.given(accountRepository.findById(2)).willReturn(Optional.empty());
		assertThrows(AccountNotFoundException.class, () -> transferService.transferMoney(1, 2, new BigDecimal(100)));
		Mockito.verify(accountRepository, Mockito.never()).changeAmount(Mockito.anyInt(), Mockito.any());
	}
}
