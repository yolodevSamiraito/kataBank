package com.bank.kata.application.service.dto;

import com.bank.kata.application.entities.Account;
import java.util.Date;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AccountDTO {

    private int id;
    private String firstName;
    private String lastName;
    private Long balance;
    private Date dateCreated;
    private Date lastUpdated;
    private List<HistoryDTO> histories;

    public AccountDTO(){

    }

    public AccountDTO(Account account){
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.lastName = account.getLastName();
        this.balance = account.getBalance();
        this.dateCreated = account.getDateCreated();
        this.lastUpdated = account.getLastUpdated();
        this.histories = account.getHistories().stream().map(HistoryDTO::new).collect(Collectors.toList());

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public Date getLastUpdated() {
        return lastUpdated;
    }

    public void setLastUpdated(Date lastUpdated) {
        this.lastUpdated = lastUpdated;
    }

    public List<HistoryDTO> getHistories() {
        return histories;
    }

    public void setHistories(List<HistoryDTO> histories) {
        this.histories = histories;
    }

    @Override
    public String toString() {
        return "AccountDTO{" +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", createdDate=" + dateCreated +
                ", lastModifiedDate=" + lastUpdated +
                ", histories=" + histories.stream().collect(Collectors.toMap(HistoryDTO::getId, Function.identity())).toString() +
                "}";
    }
}
