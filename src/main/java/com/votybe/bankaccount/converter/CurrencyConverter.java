package com.votybe.bankaccount.converter;

public interface CurrencyConverter {
    double convert(String fromCurrency, String toCurrency, double amount);
}

