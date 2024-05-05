package com.votybe.bankaccount.account;

import jakarta.validation.constraints.NotNull;

public record AccountUpdateRequest(
        @NotNull(message = "L'identifiant du compte ne doit pas être nul")
        Integer userId,
        @NotNull(message = "Le montant doit être supérieur ou égal à zéro")
        double amount) {
}
