package com.votybe.bankaccount.transactions;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping("/transfer")
    public void transferMoney(@RequestBody TransactionRequest transactionRequest) {
        transactionService.transferMoney(
                transactionRequest.fromAccountId(),
                transactionRequest.toAccountId(),
                transactionRequest.userId(),
                transactionRequest.amount()
        );
    }
}
