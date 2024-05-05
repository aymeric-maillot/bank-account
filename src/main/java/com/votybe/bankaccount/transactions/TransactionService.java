package com.votybe.bankaccount.transactions;

import com.votybe.bankaccount.account.AccountService;
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

    @Autowired
    public TransactionService(AccountService accountService, TransactionRepository transactionRepository, UserRepository userRepository) {
        this.accountService = accountService;
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    public void transferMoney(Integer fromAccountId, Integer toAccountId, Integer userId, double amount) {
        // Withdraw money from the sender's account
        accountService.withdrawFromSpecificAccount(fromAccountId, userId, amount);

        // Deposit money into the recipient's account
        accountService.depositToSpecificAccount(toAccountId, userId, amount);

        // Retrieve the user from the repository
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        // Create a new Transaction object and populate its fields
        Transaction transaction = Transaction.builder()
                .fromAccountId(fromAccountId)
                .toAccountId(toAccountId)
                .amount(amount)
                .timestamp(LocalDateTime.now())
                .user(user)
                .build();

        // Save the transaction
        transactionRepository.save(transaction);
    }

}
