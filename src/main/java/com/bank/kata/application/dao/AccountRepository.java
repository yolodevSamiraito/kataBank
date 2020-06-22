package com.bank.kata.application.dao;

import com.bank.kata.application.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    Account findByLastName(String name);
}
