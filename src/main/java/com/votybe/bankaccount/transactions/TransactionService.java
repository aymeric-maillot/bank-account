package com.votybe.bankaccount.transactions;

import com.votybe.bankaccount.account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TransactionService {

    private final AccountService accountService;

    @Autowired
    public TransactionService(AccountService accountService) {
        this.accountService = accountService;
    }

    @Transactional
    public void transferMoney(Integer fromAccountId, Integer toAccountId, Integer userId, double amount) {
        accountService.withdrawFromSpecificAccount(fromAccountId, userId, amount);
        accountService.depositToSpecificAccount(toAccountId, userId, amount);
    }
}
