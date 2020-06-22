package com.bank.kata.application.service;

import com.bank.kata.application.dao.AccountRepository;
import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.error.AccountNotFoundException;
import com.bank.kata.application.service.error.InsufficientBalanceException;
import com.bank.kata.application.service.error.NegativeAmountException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

public class AccountServiceTest {

    private AccountService accountService;
    @Mock
    private AccountRepository accountRepository;
    Account account ;
    List<History> histories = new ArrayList<>();

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
        account = new Account();
        account.setId(1);
        account.setBalance(5500L);
        History h1 = new History();
        h1.setNewBalance(2000L);
        h1.setId(1);
        h1.setAmount(1000L);
        h1.setOldBalance(1000L);
        h1.setOperationType(History.OperationType.Deposit);
        h1.setAccount(account);
        histories.add(h1);
        History h2 = new History();
        h2.setNewBalance(4000L);
        h2.setId(2);
        h2.setAmount(2000L);
        h2.setOldBalance(2000L);
        h2.setOperationType(History.OperationType.Deposit);
        h2.setAccount(account);
        histories.add(h2);
        History h3 = new History();
        h3.setNewBalance(500L);
        h3.setId(3);
        h3.setAmount(3500L);
        h3.setOldBalance(4000L);
        h3.setOperationType(History.OperationType.Withdraw);
        h3.setAccount(account);
        histories.add(h3);
        History h4 = new History();
        h4.setNewBalance(5500L);
        h4.setId(4);
        h4.setAmount(5000L);
        h4.setOldBalance(500L);
        h4.setOperationType(History.OperationType.Deposit);
        h4.setAccount(account);
        histories.add(h4);
        account.setHistories(histories);
        accountService = new AccountService(accountRepository);
    }

    @Test
    public void findBalanceById_success() throws AccountNotFoundException {
        // Given:
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.findBalanceById(1);
        // Then:
        Assert.assertEquals(5500L, v.longValue());
    }

    @Test(expected = AccountNotFoundException.class)
    public void findBalanceById_error() throws AccountNotFoundException {
        // Given:
        // When:
        Long v = accountService.findBalanceById(1);
        // Then:
    }

    @Test
    public void operation_deposit_success() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToAdd = 5000L;
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.operation(1, History.OperationType.Deposit, vToAdd);
        // Then:
        Assert.assertEquals(10500L, v.longValue());
    }

    @Test(expected = AccountNotFoundException.class)
    public void operation_deposit_error_Account() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToAdd = 5000L;
        // When:
        Long v = accountService.operation(1, History.OperationType.Deposit, vToAdd);
        // Then:
    }

    @Test(expected = NegativeAmountException.class)
    public void operation_deposit_error_Negative() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToAdd = -15000L;
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.operation(1, History.OperationType.Deposit, vToAdd);
        // Then:
    }

    @Test
    public void operation_withdraw_success() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToWithdraw = 3000L;
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.operation(1, History.OperationType.Withdraw, vToWithdraw);
        // Then:
        Assert.assertEquals(2500L, v.longValue());
    }

    @Test(expected = AccountNotFoundException.class)
    public void operation_withdraw_error_Account() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToWithdraw = 3000L;
        // When:
        Long v = accountService.operation(1, History.OperationType.Withdraw, vToWithdraw);
        // Then:
    }

    @Test(expected = InsufficientBalanceException.class)
    public void operation_withdraw_error_Insufficient() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToWithdraw = 8000L;
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.operation(1, History.OperationType.Withdraw, vToWithdraw);
        // Then:
    }

    @Test(expected = NegativeAmountException.class)
    public void operation_withdraw_error_Negative() throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        // Given:
        Long vToWithdraw = -8000L;
        // When:
        when(accountRepository.findById(1)).thenReturn(java.util.Optional.ofNullable(account));
        Long v = accountService.operation(1, History.OperationType.Withdraw, vToWithdraw);
        // Then:
    }
}
