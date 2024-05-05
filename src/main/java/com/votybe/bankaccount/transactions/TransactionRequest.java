package com.votybe.bankaccount.transactions;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record TransactionRequest(
        @NotNull(message = "Le compte source ne doit pas être nul")
        Integer fromAccountId,

        @NotNull(message = "Le compte cible ne doit pas être nul")
        Integer toAccountId,

        @NotNull(message = "L'identifiant de l'utilisateur ne doit pas être nul")
        Integer userId,

        @Min(value = 0, message = "Le montant doit être supérieur ou égal à zéro")
        double amount
) {
}
