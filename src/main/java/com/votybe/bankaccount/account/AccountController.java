package com.votybe.bankaccount.account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/{accountId}/deposit")
    public ResponseEntity<String> depositToSpecificAccount(
            @PathVariable Integer accountId,
            @RequestBody AccountUpdateRequest accountUpdateRequest
    ) {
        try {
            accountService.depositToSpecificAccount(accountId, accountUpdateRequest.userId(), accountUpdateRequest.amount());
            return ResponseEntity.ok("Dépôt d'argent effectué avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PostMapping("/{accountId}/withdraw")
    public ResponseEntity<String> withdrawFromSpecificAccount(
            @PathVariable Integer accountId,
            @RequestBody AccountUpdateRequest accountUpdateRequest
    ) {
        try {
            accountService.withdrawFromSpecificAccount(accountId, accountUpdateRequest.userId(), accountUpdateRequest.amount());
            return ResponseEntity.ok("Retrait d'argent effectué avec succès.");
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }
}
