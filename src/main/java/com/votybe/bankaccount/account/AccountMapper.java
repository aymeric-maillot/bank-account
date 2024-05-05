package com.votybe.bankaccount.account;

import com.votybe.bankaccount.users.User;
import org.springframework.stereotype.Service;

@Service
public class AccountMapper {

    public Account toAccount(AccountRequest request, User user) {
        return Account.builder()
                .user(user)
                .accountName(request.accountName())
                .currency(request.currency())
                .balance(request.balance())
                .build();
    }
}

