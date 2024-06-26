package com.votybe.bankaccount.users;

import com.votybe.bankaccount.account.Account;
import com.votybe.bankaccount.account.AccountRequest;
import com.votybe.bankaccount.transactions.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody UserRegistrationRequest request) {
        try {
            User createdUser = userService.createUser(request.username(), request.password());
            return ResponseEntity.status(HttpStatus.CREATED).body(createdUser);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{username}")
    public ResponseEntity<User> getUserByUsername(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/{username}/accounts")
    public ResponseEntity<Account> createAccountForUser(@PathVariable String username, @RequestBody AccountRequest accountRequest) {
        try {
            Account createdAccount = userService.createAccountForUser(username, accountRequest);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @GetMapping("/{username}/accounts")
    public ResponseEntity<List<Account>> getAllAccountsForUser(@PathVariable String username) {
        List<Account> userAccounts = userService.getAllAccountsForUser(username);
        if (userAccounts != null && !userAccounts.isEmpty()) {
            return ResponseEntity.ok(userAccounts);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/accounts/{accountId}")
    public ResponseEntity<Account> getAccountForUser(@PathVariable String username, @PathVariable Integer accountId) {
        Account account = userService.getAccountForUser(username, accountId);
        if (account != null) {
            return ResponseEntity.ok(account);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{username}/transactions")
    public ResponseEntity<List<Transaction>> getTransactionHistoryForUser(@PathVariable String username) {
        List<Transaction> transactionHistory = userService.getTransactionHistoryForUser(username);
        if (transactionHistory != null && !transactionHistory.isEmpty()) {
            return ResponseEntity.ok(transactionHistory);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

