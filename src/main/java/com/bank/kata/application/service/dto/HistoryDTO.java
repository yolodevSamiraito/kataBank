package com.bank.kata.application.service.dto;

import com.bank.kata.application.entities.History;
import java.util.Date;

public class HistoryDTO {

    private int id;
    private Date dateCreated;
    private int account;
    private History.OperationType operationType;
    private Long oldBalance;
    private Long newBalance;
    private Long amount;

    public HistoryDTO(){

    }

    public HistoryDTO(History history){
        this.id = history.getId();
        this.dateCreated = history.getDateCreated();
        this.account = history.getAccount().getId();
        this.operationType = history.getOperationType();
        this.oldBalance = history.getOldBalance();
        this.newBalance = history.getNewBalance();
        this.amount = history.getAmount();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public History.OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(History.OperationType operationType) {
        this.operationType = operationType;
    }

    public Long getOldBalance() {
        return oldBalance;
    }

    public void setOldBalance(Long oldBalance) {
        this.oldBalance = oldBalance;
    }

    public Long getNewBalance() {
        return newBalance;
    }

    public void setNewBalance(Long newBalance) {
        this.newBalance = newBalance;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "HistoryDTO{" +
                ", account='" + account + '\'' +
                ", operationType='" + operationType + '\'' +
                ", createdDate=" + dateCreated +
                ", oldBalance=" + oldBalance +
                ", newBalance=" + newBalance +
                ", amount=" + amount +
                "}";
    }
}
