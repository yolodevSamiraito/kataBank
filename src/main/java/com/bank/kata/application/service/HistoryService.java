package com.bank.kata.application.service;

import com.bank.kata.application.dao.HistoryRepository;
import com.bank.kata.application.entities.History;
import com.bank.kata.application.service.error.AccountNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HistoryService {

    private HistoryRepository historyRepository;

    @Autowired
    public HistoryService(HistoryRepository theHistoryRepository){
        historyRepository = theHistoryRepository;
    }

    @Transactional
    public List<History> getHistoriesByAccountId(int id) throws AccountNotFoundException {
        List<History> histories = historyRepository.getHistoriesByAccount_Id(id);
        if (histories.isEmpty()){
            throw new AccountNotFoundException("Account not Found");
        }
        return histories;
    }
}
