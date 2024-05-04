package com.votybe.bankaccount.account;

import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void depositToSpecificAccount(Integer accountId, Integer userId, double amount) {
        Account account = accountRepository.findSpecificAccountByUserId(userId, accountId);

        if (account == null) {
            throw new RuntimeException("Compte non trouvé avec l'ID: " + accountId);
        }

        if (amount <= 0) {
            throw new RuntimeException("Le montant doit être supérieur à 0.");
        }

        account.setBalance(account.getBalance() + amount);

        accountRepository.save(account);
    }

    public void withdrawFromSpecificAccount(Integer accountId, Integer userId, double amount) {
        Account account = accountRepository.findSpecificAccountByUserId(userId, accountId);

        if (account == null) {
            throw new RuntimeException("Compte non trouvé avec l'ID: " + accountId);
        }

        if (account.getBalance() < amount) {
            throw new RuntimeException("Solde insuffisant pour retirer " + amount + "€.");
        }

        account.setBalance(account.getBalance() - amount);

        accountRepository.save(account);
    }
}
