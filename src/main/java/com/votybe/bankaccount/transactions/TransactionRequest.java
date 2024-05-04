package com.votybe.bankaccount.transactions;

public record TransactionRequest(Integer fromAccountId, Integer toAccountId, Integer userId, double amount) {
}
