package com.bank.kata.application.entities;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
public class History {

    public enum OperationType{
        Deposit, Withdraw
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "date_created")
    @CreationTimestamp
    private Date dateCreated;
    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private Account account;
    @Enumerated
    private OperationType operationType;
    private Long oldBalance;
    private Long newBalance;
    private Long amount;

}
