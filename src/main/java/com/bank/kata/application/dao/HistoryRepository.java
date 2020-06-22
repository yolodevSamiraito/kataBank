package com.bank.kata.application.dao;

import com.bank.kata.application.entities.History;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HistoryRepository extends JpaRepository<History, Integer> {
    List<History> getHistoriesByAccount_Id(int id);
}
