package com.votybe.bankaccount.users;

import com.votybe.bankaccount.account.Account;
import com.votybe.bankaccount.account.AccountMapper;
import com.votybe.bankaccount.account.AccountRepository;
import com.votybe.bankaccount.account.AccountRequest;
import com.votybe.bankaccount.transactions.Transaction;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    private final AccountRepository accountRepository;

    private final AccountMapper accountMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper, AccountRepository accountRepository, AccountMapper accountMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public User createUser(String username, String password) {
        if (userRepository.findByUsername(username) != null) {
            throw new RuntimeException("L'utilisateur existe déjà");
        }

        User user = userMapper.toUser(new UserRegistrationRequest(username, password));
        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    public Account createAccountForUser(String username, AccountRequest accountRequest) {
        User user = userRepository.findByUsername(username);

        if (user == null) {
            throw new RuntimeException("Utilisateur non trouvé avec le nom d'utilisateur: " + username);
        }

        Account account = accountMapper.toAccount(accountRequest, user);
        return accountRepository.save(account);
    }

    public List<Account> getAllAccountsForUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return accountRepository.findAllByUserId(user.getId());
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }
    }

    public Account getAccountForUser(String username, Integer accountId) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return accountRepository.findSpecificAccountByUserId(user.getId(), accountId);
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }
    }

    public List<Transaction> getTransactionHistoryForUser(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return user.getTransactionHistory();
        } else {
            throw new EntityNotFoundException("Utilisateur non trouvé avec le nom d'utilisateur : " + username);
        }
    }
}
