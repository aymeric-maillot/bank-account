package com.votybe.bankaccount.transactions;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    void depositToSpecificAccount(Integer accountId, Integer userId, double amount);
}
