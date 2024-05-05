package com.votybe.bankaccount.account;

public record AccountRequest(String accountName, double balance, String currency) {
}
