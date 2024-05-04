package com.votybe.bankaccount.transactions;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "from_account_id", nullable = false)
    private Integer fromAccountId;

    @Column(name = "to_account_id", nullable = false)
    private Integer toAccountId;

    @Column(nullable = false)
    private double amount;

    @Column(nullable = false)
    private LocalDateTime timestamp;
}
