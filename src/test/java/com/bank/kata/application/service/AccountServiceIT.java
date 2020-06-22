package com.bank.kata.application.service;

import com.bank.kata.application.KataBankAccountApplication;
import com.bank.kata.application.dao.AccountRepository;
import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.error.AccountNotFoundException;
import com.bank.kata.application.service.error.InsufficientBalanceException;
import com.bank.kata.application.service.error.NegativeAmountException;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = KataBankAccountApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Transactional
public class AccountServiceIT {
    @Autowired
    private AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Test
    @Transactional
    public void findById_success() throws AccountNotFoundException {
        Account account = new Account();
        account.setLastName("AccountTestIT");
        account.setBalance(5500L);
        accountRepository.saveAndFlush(account);
        int id = accountRepository.findByLastName("AccountTestIT").getId();
        Long v = accountService.findBalanceById(id);
        Assert.assertEquals(5500L, v.longValue());
    }

    @Test(expected = AccountNotFoundException.class)
    @Transactional
    public void findById_error() throws AccountNotFoundException {
        Long v = accountService.findBalanceById(999999);
        Assert.assertEquals(5500L, v.longValue());
    }

    @Test
    @Transactional
    public void operation_deposit_success() throws NegativeAmountException, AccountNotFoundException, InsufficientBalanceException {
        Account account = new Account();
        account.setLastName("AccountTestIT");
        account.setBalance(5500L);
        account.setHistories(new ArrayList<>());
        accountRepository.saveAndFlush(account);
        Account acc = accountRepository.findByLastName("AccountTestIT");
        accountService.operation(acc.getId(), History.OperationType.Deposit, 3000L);
        accountRepository.flush();
        Account accountAfterOp = accountRepository.findByLastName("AccountTestIT");
        Assert.assertEquals(1, accountAfterOp.getHistories().size());
        Assert.assertEquals(8500L, accountAfterOp.getBalance().longValue());
    }

    @Test
    @Transactional
    public void operation_withdraw_success() throws NegativeAmountException, AccountNotFoundException, InsufficientBalanceException {
        Account account = new Account();
        account.setLastName("AccountTestIT");
        account.setBalance(5500L);
        account.setHistories(new ArrayList<>());
        accountRepository.saveAndFlush(account);
        Account acc = accountRepository.findByLastName("AccountTestIT");
        accountService.operation(acc.getId(), History.OperationType.Withdraw, 3000L);
        accountRepository.flush();
        Account accountAfterOp = accountRepository.findByLastName("AccountTestIT");
        Assert.assertEquals(1, accountAfterOp.getHistories().size());
        Assert.assertEquals(2500L, accountAfterOp.getBalance().longValue());
    }

    @Test(expected = InsufficientBalanceException.class)
    @Transactional
    public void operation_error_Insufficient() throws NegativeAmountException, AccountNotFoundException, InsufficientBalanceException {
        Account account = new Account();
        account.setLastName("AccountTestIT");
        account.setBalance(5500L);
        account.setHistories(new ArrayList<>());
        accountRepository.saveAndFlush(account);
        Account acc = accountRepository.findByLastName("AccountTestIT");
        accountService.operation(acc.getId(), History.OperationType.Withdraw, 8000L);
    }

    @Test(expected = NegativeAmountException.class)
    @Transactional
    public void operation_error_Negative() throws NegativeAmountException, AccountNotFoundException, InsufficientBalanceException {
        Account account = new Account();
        account.setLastName("AccountTestIT");
        account.setBalance(5500L);
        account.setHistories(new ArrayList<>());
        accountRepository.saveAndFlush(account);
        Account acc = accountRepository.findByLastName("AccountTestIT");
        accountService.operation(acc.getId(), History.OperationType.Withdraw, -8000L);
    }
}
