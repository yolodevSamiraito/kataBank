package com.bank.kata.application.service.mapper;

import com.bank.kata.application.entities.Account;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.dto.AccountDTO;
import com.bank.kata.application.service.dto.HistoryDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AccountMapper {

    public AccountDTO accountToDTO(Account account){
        return new AccountDTO(account);
    }

    public Account accountDTOToAccount(AccountDTO accountDTO){
        if (accountDTO==null){
            return null;
        }
        Account account = new Account();
        account.setId(accountDTO.getId());
        account.setBalance(accountDTO.getBalance());
        account.setDateCreated(accountDTO.getDateCreated());
        account.setLastUpdated(accountDTO.getLastUpdated());
        account.setFirstName(accountDTO.getFirstName());
        account.setLastName(accountDTO.getLastName());
        List<History> histories = accountDTO.getHistories().stream().map(this::historyDTOToHistory).collect(Collectors.toList());
        histories.forEach(history -> history.setAccount(account));
        account.setHistories(histories);
        return account;
    }

    public HistoryDTO historyToDTO(History history){
        return new HistoryDTO(history);
    }

    public History historyDTOToHistory(HistoryDTO historyDTO){
        if(historyDTO==null){
            return null;
        }
        History history = new History();
        history.setId(historyDTO.getId());
        history.setOldBalance(historyDTO.getOldBalance());
        history.setAmount(historyDTO.getAmount());
        history.setNewBalance(historyDTO.getNewBalance());
        history.setDateCreated(historyDTO.getDateCreated());
        history.setOperationType(historyDTO.getOperationType());
        return history;
    }
}
