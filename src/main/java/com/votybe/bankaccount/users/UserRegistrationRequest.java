package com.votybe.bankaccount.users;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record UserRegistrationRequest(
        @NotNull(message = "Le nom d'utilisateur ne doit pas être nul")
        @NotEmpty(message = "Le nom d'utilisateur ne doit pas être vide")
        String username,

        @NotNull(message = "Le mot de passe ne doit pas être nul")
        @NotEmpty(message = "Le mot de passe ne doit pas être vide")
        String password
) {
}
