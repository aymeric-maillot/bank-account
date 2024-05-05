package com.votybe.bankaccount.transactions;

import com.votybe.bankaccount.account.Account;
import com.votybe.bankaccount.account.AccountRepository;
import com.votybe.bankaccount.account.AccountService;
import com.votybe.bankaccount.converter.CurrencyConverter;
import com.votybe.bankaccount.users.User;
import com.votybe.bankaccount.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@Transactional
public class TransactionService {

    private final AccountService accountService;
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final CurrencyConverter currencyConverter;

    private final AccountRepository accountRepository;

    @Autowired
    public TransactionService(AccountService accountService, TransactionRepository transactionRepository, UserRepository userRepository, CurrencyConverter currencyConverter, AccountRepository accountRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.currencyConverter = currencyConverter;
        this.accountRepository = accountRepository;
    }

    public void transferMoney(Integer fromAccountId, Integer toAccountId, Integer userId, double amount) {
        Account fromAccount = accountRepository.findById(fromAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));

        Account toAccount = accountRepository.findById(toAccountId)
                .orElseThrow(() -> new IllegalArgumentException("Account not found"));


        if (!fromAccount.getCurrency().equals(toAccount.getCurrency())) {
            amount = currencyConverter.convert(fromAccount.getCurrency(), toAccount.getCurrency(), amount);
        }

        accountService.withdrawFromSpecificAccount(fromAccountId, userId, amount);

        accountService.depositToSpecificAccount(toAccountId, userId, amount);


        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        Transaction transaction = Transaction.builder()
                .fromAccountId(fromAccountId)
                .toAccountId(toAccountId)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .user(user)
                .build();

        transactionRepository.save(transaction);
    }
}
