package com.bank.kata.application.service;

import com.bank.kata.application.dao.AccountRepository;
import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.error.AccountNotFoundException;
import com.bank.kata.application.service.error.InsufficientBalanceException;
import com.bank.kata.application.service.error.NegativeAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService {

    private AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository theAccountRepository){
        accountRepository = theAccountRepository;
    }

    public Long findBalanceById(int id) throws AccountNotFoundException {
        Account account = accountRepository.findById(id).orElse(null);

        if(account == null){
            throw new AccountNotFoundException("Account not Found");
        }

        return account.getBalance();
    }

    public void save(Account account) {
        accountRepository.save(account);
    }

    public  Long operation(int id, History.OperationType operationType, Long newValue) throws NegativeAmountException, AccountNotFoundException, InsufficientBalanceException {
        if(newValue<0){
            throw new NegativeAmountException("Balance can't be negative");
        }
        Account account = accountRepository.findById(id).orElse(null);
        if(account == null){
            throw new AccountNotFoundException("Account not Found");
        }
        long newBalance;
        if(operationType.equals(History.OperationType.Deposit)){
            newBalance = account.getBalance() + newValue;
        } else{
            newBalance = account.getBalance() - newValue;
        }
        if(newBalance<0){
            throw new InsufficientBalanceException("Insufficient Balance to process this operation");
        }
        History op = new History();
        op.setAccount(account);
        op.setOperationType(History.OperationType.Deposit);
        op.setOldBalance(account.getBalance());
        op.setAmount(newValue);
        op.setNewBalance(newBalance);
        account.setBalance(newBalance);
        account.getHistories().add(op);
        accountRepository.save(account);
        return account.getBalance();
    }
}
