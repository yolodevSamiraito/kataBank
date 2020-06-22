package com.bank.kata.application.restApi;

import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.AccountService;
import com.bank.kata.application.service.HistoryService;
import com.bank.kata.application.service.dto.HistoryDTO;
import com.bank.kata.application.service.error.AccountNotFoundException;
import com.bank.kata.application.service.error.InsufficientBalanceException;
import com.bank.kata.application.service.error.NegativeAmountException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api")
public class AccountOperationController {

    private AccountService accountService;
    private HistoryService historyService;

    @Autowired
    public AccountOperationController(AccountService accountService, HistoryService historyService){
        this.accountService = accountService;
        this.historyService = historyService;
    }



    @GetMapping("/account/{id}/check")
    public Long check(@PathVariable int id) throws AccountNotFoundException {
        return accountService.findBalanceById(id);
    }

    @PostMapping("/account/{id}/deposit")
    public Long deposit(@PathVariable int id,@RequestBody Long newValue) throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        return accountService.operation(id, History.OperationType.Deposit, newValue);
    }

    @PostMapping("/account/{id}/withdraw")
    public Long withdraw(@PathVariable int id,@RequestBody Long newValue) throws AccountNotFoundException, NegativeAmountException, InsufficientBalanceException {
        return accountService.operation(id, History.OperationType.Withdraw, newValue);
    }


    @GetMapping("/account/{id}/print")
    public List<HistoryDTO> print(@PathVariable int id) throws AccountNotFoundException {
        return historyService.getHistoriesByAccountId(id).stream().map(HistoryDTO::new).collect(Collectors.toList());
    }
}
